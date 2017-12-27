package hui.zhong.cf.partner.h5.controller;


import com.huizhongcf.util.StringUtil;
import hui.zhong.cf.partner.h5.constants.MobileApiUrls;
import hui.zhong.cf.partner.h5.framework.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;

/**
 * 处理登录用户相关的操作。
 *
 * @author LiuXianfa
 * @date 2017年12月25日
 */
@Controller
@RequestMapping("/loginUser")
public class LoginUserController extends BaseController {

    /**
     * 跳转到uGeneralPartner.jsp
     *
     * @param token
     * @return
     */
    @RequestMapping("/user")
    public String toUGeneralPartner(String token) {
        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return "login";
        }
        return "uGeneralPartner";
    }


    /**
     * 用户-一般合伙人-我的客户，用户-团队管理者-我的客户。<br>
     * 我的客户：该合伙人自己发展的客户。
     *
     * @return
     */
    @RequestMapping("/myCustomer")
    @ResponseBody
    public Object myCustomer(@RequestParam(required = false) String token,
                             @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false) String queryCondition) throws IOException {

        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return renderError("9999", "用户未登录或登录已失效");
        }

        String url = String.format(MobileApiUrls.myCustomer, MOBILE_API_SERVER_DOMAIN);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("pageNo", pageNo.toString());
        map.put("pageSize", pageSize.toString());
        if (StringUtils.isNotBlank(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }

        return parse(post(url, map));
    }


    /**
     * 用户-一般合伙人-我的下级-合伙人，用户-团队管理者-我的下级-合伙人。<br>
     *
     * @return
     */
    @RequestMapping("/mySubordinate/partner")
    @ResponseBody
    public Object mySubordinatePartner(@RequestParam(required = false) String token,
                                       @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                       @RequestParam(required = false) String queryCondition) throws IOException {

        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return renderError("9999", "用户未登录或登录已失效");
        }

        String url = String.format(MobileApiUrls.mySubordinate_partner, MOBILE_API_SERVER_DOMAIN);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("pageNo", pageNo.toString());
        map.put("pageSize", pageSize.toString());
        if (StringUtils.isNotBlank(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }

        return parse(post(url, map));
    }

    /**
     * 用户-一般合伙人-我的下级-合伙人客户，用户-团队管理者-我的下级-合伙人客户。<br>
     *
     * @return
     */
    @RequestMapping("/mySubordinate/partnerCustomer")
    @ResponseBody
    public Object mySubordinatePartnerCustomer(@RequestParam(required = false) String token,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(required = false) String queryCondition) throws IOException {

        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return renderError("9999", "用户未登录或登录已失效");
        }

        String url = String.format(MobileApiUrls.mySubordinate_partnerCustomer, MOBILE_API_SERVER_DOMAIN);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("pageNo", pageNo.toString());
        map.put("pageSize", pageSize.toString());
        if (StringUtils.isNotBlank(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }

        return parse(post(url, map));
    }

    /**
     * 用户-团队管理者-其他下级-合伙人。<br>
     *
     * @return
     */
    @RequestMapping("/otherSubordinate/partner")
    @ResponseBody
    public Object otherSubordinatePartner(@RequestParam(required = false) String token,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(required = false) String queryCondition) throws IOException {

        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return renderError("9999", "用户未登录或登录已失效");
        }

        String url = String.format(MobileApiUrls.otherSubordinate_partner, MOBILE_API_SERVER_DOMAIN);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("pageNo", pageNo.toString());
        map.put("pageSize", pageSize.toString());
        if (StringUtils.isNotBlank(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }

        return parse(post(url, map));
    }

    /**
     * 用户-团队管理者-其他下级-合伙人客户。
     *
     * @return
     */
    @RequestMapping("/otherSubordinate/partnerCustomer")
    @ResponseBody
    public Object otherSubordinatePartnerCustomer(@RequestParam(required = false) String token,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false) String queryCondition) throws IOException {

        getRequest().setAttribute("targetUrl", getTargetUrl());
        if (StringUtils.isBlank(token)) {
            token = getToken();
        }
        if (StringUtil.isBlank(token)) {
            return renderError("9999", "用户未登录或登录已失效");
        }


        String url = String.format(MobileApiUrls.otherSubordinate_partnerCustomer, MOBILE_API_SERVER_DOMAIN);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("pageNo", pageNo.toString());
        map.put("pageSize", pageSize.toString());
        if (StringUtils.isNotBlank(queryCondition)) {
            map.put("queryCondition", queryCondition);
        }

        return parse(post(url, map));
    }

}
