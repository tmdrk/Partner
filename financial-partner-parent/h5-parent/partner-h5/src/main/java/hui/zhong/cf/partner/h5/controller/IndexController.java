package hui.zhong.cf.partner.h5.controller;

import com.alibaba.fastjson.JSONObject;
import com.huizhongcf.util.StringUtil;
import hui.zhong.cf.partner.h5.constants.MobileApiUrls;
import hui.zhong.cf.partner.h5.framework.BaseController;
import hui.zhong.cf.partner.h5.utils.OkHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author lxf
 */
@Controller
public class IndexController extends BaseController {

    /**
     * 跳转到首页
     *
     * @param token
     * @return
     * @throws IOException
     */
    @RequestMapping("/index")
    public Object index(String token) throws IOException {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return "login";
        }

        String url = String.format(MobileApiUrls.indexStat, MOBILE_API_SERVER_DOMAIN);
        String params = String.format(TOKEN_PARAM, token);

        JSONObject json = parse(post(url, params));
        if (OkHttpUtil.isSuccessful(json)) {
            getRequest().setAttribute("body", json.getJSONObject("body"));
            return "index";
        }

        // todo:lxf:数据请求失败，此时应该跳转到失败页面
        return "跳转到失败页面";
    }

    /**
     * 去邀请页面
     *
     * @param token
     * @return
     */
    @RequestMapping("/toInvite")
    public String toInvite(String token) throws IOException {
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
            return "invite";
        }
        // todo:lxf:数据请求失败，此时应该跳转到失败页面
        return "跳转到失败页面";
    }

    /**
     * 在用户点击分享按钮的时候后，跳转到这个url，<br>
     * 原生会监听这个url，如果访问的是这个url，会获取到参数，并跳转到指定的分享页面。
     *
     * @param json
     * @return
     * @throws IOException
     */
    @RequestMapping("/invite")
    public Object invite(String json) throws IOException {
        System.out.println(json);
        return null;
    }
}
