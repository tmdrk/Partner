package hui.zhong.cf.partner.h5.framework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huizhongcf.util.StringUtil;
import hui.zhong.cf.partner.h5.utils.OkHttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author LiuXianfa
 */
public class BaseController {
    public static Log logger = LogFactory.getLog(BaseController.class);

    public static final String TOKEN_PARAM = "{'token':'%s'}";

    /**
     * mobile-api服务端域名<br/>
     * 在{@link /property/config.properties}中配置<br/>
     * 默认<code>localhost:8080</code>
     */
    @Value("${mobile.api.server.domain:http://localhost:8080/financial-mobile-api}")
    public String MOBILE_API_SERVER_DOMAIN;

    /**
     * @param url
     * @return
     * @throws IOException
     * @see OkHttpUtil#get(String)
     */
    public String get(String url) throws IOException {
        return OkHttpUtil.get(url);
    }

    /**
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @see OkHttpUtil#get(String, Map)
     */
    public String get(String url, Map<String, String> params) throws IOException {
        return OkHttpUtil.get(url, params);
    }

    /**
     * @param url
     * @return
     * @throws IOException
     * @see OkHttpUtil#post(String)
     */
    public String post(String url) throws IOException {
        return OkHttpUtil.post(url);
    }

    /**
     * @param url
     * @param params
     * @return
     * @see OkHttpUtil#post(String, String)
     */
    public String post(String url, Map<String, String> params) throws IOException {
        return OkHttpUtil.post(url, params);
    }

    /**
     * @param url
     * @param jsonParams
     * @return
     * @throws IOException
     * @see OkHttpUtil#post(String, String)
     */
    public static String post(String url, String jsonParams) throws IOException {
        return OkHttpUtil.post(url, jsonParams);
    }

    /**
     * @param body
     * @return
     * @see OkHttpUtil#parse(String)
     */
    public JSONObject parse(String body) {
        return OkHttpUtil.parse(body);
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 判断用户是否未登录/登录失效
     *
     * @param responseBody
     * @return
     */
    public boolean isUnLogin(JSONObject responseBody) {
        if (responseBody == null) {
            return false;
        }
        return "9999".equals(responseBody.getString("errorCode"));
    }

    /**
     * <b>获得当前controller对应的url</b><br>
     *     因为在跳转到login页面的时候，需要记录从哪个页面跳转过来的，就用这个方法获得。
     * @return
     */
    public String getTargetUrl() {
        HttpServletRequest req = getRequest();
        return new StringBuilder()
                .append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getRequestURI())
                .toString();
    }

    /**
     * <b>获取token</b><br>
     * token都是放到cookie中的，{@link StringUtils#isBlank(String token)} == true，就说明用户未登录
     *
     * @return
     */
    public String getToken() {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("ACCESS_TOKEN".equalsIgnoreCase(cookie.getName())) {
                // 说明有token
                String token = cookie.getValue();
                if (StringUtil.isNotBlanks(token)) {
                    return token;
                }
            }
        }
        return null;
    }

    protected Map<String, Object> cookies2Map(HttpServletRequest request) {
        Map<String, Object> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie c : cookies) {
                cookieMap.put(c.getName(), c.getValue());
            }
        }

        return cookieMap;
    }

    protected Map<String, Object> getParams() {
        HttpServletRequest request = getRequest();
        Map<String, Object> param = new HashMap<String, Object>();
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            if (null != request.getParameter(name) && !"".equals(request.getParameter(name).trim())) {
                try {
                    param.put(StringUtils.trim(name), request.getParameter(name).trim());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        return param;
    }


    /**
     * 错误
     *
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public Object renderError(String errorCode, String errorMsg) {
        ResultBean bean = new ResultBean();
        bean.setErrorMsg(errorMsg);
        bean.setErrorCode(errorCode);
        bean.setBody(new HashMap<>());
        return JSON.toJSON(bean);
    }

    /**
     * @param body
     * @return
     * @description 成功响应
     * @author shiyang
     * 2016年10月20日 下午2:45:21
     */
    public Object renderSuccess(Object body) {
        ResultBean bean = new ResultBean();
        bean.setErrorCode("200");
        bean.setErrorMsg("SUCCESS");
        if (body == null) {
            body = new HashMap();
        }
        bean.setBody(body);
        return JSON.toJSON(bean);
    }


    public Map<String, Object> getRequestParam() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        Iterator<?> iter = request.getParameterMap().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = entry.getKey().toString();
            String value = request.getParameter(key);
            try {
                value = new String(value.getBytes("utf-8"), request.getCharacterEncoding());
            } catch (UnsupportedEncodingException e) {
            }
            result.put(key, value);
        }
        return result;
    }


    /**
     * @param request
     * @return
     * @description 获取客户端ip
     * @author shiyang
     * 2016年10月26日 下午2:11:17
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown host";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public boolean isH5() {
        return true;
    }
}
