package hui.zhong.cf.partner.h5.controller;

import com.alibaba.fastjson.JSONObject;
import com.huizhongcf.util.CookieUtils;
import com.huizhongcf.util.RegExpUtil;
import com.huizhongcf.util.StringUtil;
import hui.zhong.cf.partner.h5.constants.MobileApiUrls;
import hui.zhong.cf.partner.h5.exception.TokenRequiredException;
import hui.zhong.cf.partner.h5.framework.BaseController;
import hui.zhong.cf.partner.h5.utils.OkHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * H5-Controller
 *
 * @author lxf
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String toLogin() {
        String targetUrl = getRequest().getHeader("Referer");
        getRequest().setAttribute("targetUrl", targetUrl);
        return "login";
    }

    @RequestMapping("/userCenter")
    public String toUserCenter(@RequestParam(required = false) String token) throws IOException {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return "login";
        }

        String url = String.format(MobileApiUrls.userInfo, MOBILE_API_SERVER_DOMAIN);
        String params = String.format(TOKEN_PARAM, token);
        JSONObject responseBody = parse(post(url, params));
        if (isUnLogin(responseBody)) {
            // token失效
            return "login";
        }
        if (OkHttpUtil.isSuccessful(responseBody)) {
            // 数据请求成功
            getRequest().setAttribute("body", responseBody.get("body"));
            return "userCenter";
        }
        // todo:lxf:数据请求失败，此时应该跳转到失败页面
        return "跳转到失败页面";
    }


    /**
     * 用户登录
     *
     * @param userName
     * @param passWord
     * @param targetUrl 登录成功后跳转到指定页面的url
     * @return
     * @throws IOException
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object doLogin(@RequestParam(required = true) String userName,
                          @RequestParam(required = true) String passWord,
                          @RequestParam(required = false) String targetUrl,
                          HttpServletResponse response) throws IOException {

        if (!RegExpUtil.isMobile(userName)) {
            return renderError("100104", "手机号码错误");
        }
        if (!RegExpUtil.isPassword(passWord)) {
            return renderError("100103", "密码格式不正确");
        }

        String url = String.format(MobileApiUrls.doLogin, MOBILE_API_SERVER_DOMAIN);
        String params = String.format("{'userName':'%s','passWord':'%s'}", userName, passWord);

        JSONObject json = parse(post(url, params));
        if (OkHttpUtil.isSuccessful(json)) {
            CookieUtils.saveCookie(response, "ACCESS_TOKEN", json.getJSONObject("body").getString("token"), 86400 * 7);
            if (StringUtils.isNotBlank(targetUrl)) {
                json.put("targetUrl", targetUrl);
            }
        }

        return json;
    }

    /**
     * 检查用户是否存在
     *
     * @param userName 用户标识
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public Object checkUserName(@RequestParam(required = true) String userName) throws IOException {

        String url = String.format(MobileApiUrls.checkUserName, MOBILE_API_SERVER_DOMAIN);
        String json = String.format("{'userName':'%s'}", userName);
        return parse(post(url, json));
    }

    /**
     * 注册
     *
     * @param userName      用户标识
     * @param passWord      密码
     * @param smsCode       短信验证码
     * @param recommendCode 推荐码601或301开头
     * @throws IOException
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Object doRegister(@RequestParam(required = true) String userName,
                             @RequestParam(required = true) String passWord,
                             @RequestParam(required = true) String smsCode,
                             @RequestParam(required = false) String recommendCode,
                             HttpServletResponse response) throws IOException {
        if (!RegExpUtil.isMobile(userName)) {
            return renderError("100104", "手机号码错误");
        }
        if (!RegExpUtil.isPassword(passWord)) {
            return renderError("100103", "密码格式不正确");
        }
        if (StringUtils.isBlank(smsCode)) {
            return renderError("100101", "短信验证码错误");
        }

        String url = String.format(MobileApiUrls.doRegister, MOBILE_API_SERVER_DOMAIN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("passWord", passWord);
        map.put("smsCode", smsCode);
        if (StringUtils.isNotBlank(recommendCode)) {
            map.put("recommendCode", recommendCode);
        }

        JSONObject json = parse(post(url, map));
        if (OkHttpUtil.isSuccessful(json)) {
            // 注册成功后，会返回token，此时不用再次登录操作。直接把token放到cookie中
            CookieUtils.saveCookie(response, "ACCESS_TOKEN", json.getJSONObject("body").getString("token"), 86400 * 7);
        }
        // todo:lxf：注册成功后跳转到哪？
        return json;
    }

    /**
     * 忘记密码
     *
     * @param userName    用户手机号
     * @param newPassWord 新密码
     * @param smsCode     短信验证码
     * @return
     * @throws IOException
     */
    @RequestMapping("/forgetPassWord")
    @ResponseBody
    public Object forgetPassWord(@RequestParam(required = true) String userName,
                                 @RequestParam(required = true) String newPassWord,
                                 @RequestParam(required = true) String smsCode) throws IOException {
        if (!RegExpUtil.isMobile(userName)) {
            return renderError("100104", "手机号码错误");
        }
        if (!RegExpUtil.isPassword(newPassWord)) {
            return renderError("100303", "新密码格式不正确");
        }
        if (StringUtils.isBlank(smsCode)) {
            return renderError("100101", "短信验证码错误");
        }

        String url = String.format(MobileApiUrls.forgetPassWord, MOBILE_API_SERVER_DOMAIN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("newPassWord", newPassWord);
        map.put("smsCode", smsCode);
        return parse(post(url, map));
    }

    /**
     * 修改密码
     * todo:lxf:/view/modifyPwd.jsp页面是根据原密码，修改新密码。接口不符合
     *
     * @param userName    用户手机号
     * @param newPassWord 新密码
     * @param smsCode     短信验证码
     * @return
     * @throws IOException
     */
    @RequestMapping("/modifyPassWord")
    @ResponseBody
    public Object modifyPassWord(@RequestParam(required = false) String token,
                                 @RequestParam(required = true) String oldPassWord,
                                 @RequestParam(required = true) String newPassWord,
                                 @RequestParam(required = true) String smsCode) throws IOException {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            throw new TokenRequiredException();
        }
        if (!RegExpUtil.isPassword(newPassWord)) {
            return renderError("100303", "新密码格式不正确");
        }
        if (StringUtils.isBlank(smsCode)) {
            return renderError("100101", "短信验证码错误");
        }

        String url = String.format(MobileApiUrls.modifyPassWord, MOBILE_API_SERVER_DOMAIN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("newPassWord", newPassWord);
        map.put("smsCode", smsCode);

        return parse(post(url, map));
    }

    /**
     * 发送验证码到手机
     *
     * @param type 10:注册;11:忘记密码;12:修改密码
     * @return
     * @throws IOException
     */
    @RequestMapping("/sendMsg")
    @ResponseBody
    public Object sendMsg(@RequestParam(required = true, value = "userName") String userName,
                          @RequestParam(required = true) String type) throws IOException {
        if (!RegExpUtil.isMobile(userName)) {
            return renderError("100401", "手机号格式错误");
        }

        String url = String.format(MobileApiUrls.sendMsg, MOBILE_API_SERVER_DOMAIN);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("type", type);
        return parse(post(url, map));
    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     * @throws IOException
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(@RequestParam(required = false) String token,
                         HttpServletResponse response) throws IOException {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            throw new TokenRequiredException();
        }

        String url = String.format(MobileApiUrls.logout, MOBILE_API_SERVER_DOMAIN);
        String params = String.format(TOKEN_PARAM, token);
        JSONObject json = parse(post(url, params));
        if (OkHttpUtil.isSuccessful(json)) {
            CookieUtils.deleteCookie(response, "ACCESS_TOKEN");
        }
        if ("9999".equals(json.getString("errorCode"))) {
            // todo:lxf-cookie没有过期，但是token在mobile-api已经过期
            CookieUtils.deleteCookie(response, "ACCESS_TOKEN");
        }
        return json;
    }

    /**
     * 用户详细信息
     *
     * @param token
     * @return
     * @throws IOException
     */
    @RequestMapping("/info")
    @ResponseBody
    public Object info(@RequestParam(required = false) String token) throws IOException {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            throw new TokenRequiredException();
        }

        String url = String.format(MobileApiUrls.userInfo, MOBILE_API_SERVER_DOMAIN);
        String params = String.format(TOKEN_PARAM, token);
        return parse(post(url, params));
    }
}
