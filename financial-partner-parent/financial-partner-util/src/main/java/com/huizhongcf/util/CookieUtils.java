package com.huizhongcf.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Cookie 工具类
 *
 * @author lijie
 * @version 1.0
 * <pre>
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2015年5月26日      lijie       1.0         1.0 Version
 * 2017年12月23日     lxf         1.1         去掉工具类中和电商业务相关的方法
 * </pre>
 */
public class CookieUtils {

    public static Cookie[] getCookies(HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("参数request不能为null");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new Cookie[0];
        }
        return cookies;
    }

    /**
     * 获得指定cookie中的值
     *
     * @param request
     * @param cookieName 要查找的cookie的名字
     * @return 返回指定Cookie中的字符串值
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        if (cookieName == null) {
            throw new NullPointerException("参数cookieName不能为null");
        }
        Cookie[] cookies = getCookies(request);
        for (Cookie cookie : cookies) {
            if (cookie != null && cookieName.equals(cookie.getName())) {
                try {
                    return URLDecoder.decode(cookie.getValue(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 保存cookie，浏览器关闭时过期
     *
     * @param response
     * @param cookieName  要保存的cookie的名字
     * @param cookieValue 保存到cookie中的值
     */
    public static void saveCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        saveCookie(response, cookieName, cookieValue, -1);
    }

    /**
     * 删除指定的cookie
     *
     * @param response
     * @param cookieName 要删除的cookie名
     */
    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        saveCookie(response, cookieName, "", 0);
    }

    /**
     * 保存cookie 并设置cookie存活的时间
     *
     * @param response
     * @param cookieName  cookie的名字
     * @param cookieValue cookie中要存放的值
     * @param maxAge      cookie存活时间(单位：秒)
     */
    public static void saveCookie(HttpServletResponse response,
                                  String cookieName, String cookieValue, int maxAge) {
        try {
            cookieValue = URLEncoder.encode(cookieValue == null ? "" : cookieValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println(setValue(null, "1", 3));
        System.out.println(setValue("1,2,3", "1", 3));
        System.out.println(setValue("2,1,3", "1", 3));
        System.out.println(setValue("2,1", "1", 3));
        System.out.println(setValue("2,3,4", "1", 3));
        System.out.println(setValue("2,4", "1", 3));
        System.out.println(setValue("4,3,1", "1", 3));
    }

    /**
     * 设置指定cookie中的值（1.得到原先cookie中的值；2.重新设置cookie的值；3.保存重置后的cookie）
     *
     * @param request
     * @param response
     * @param cookieName 要设置的cookie的名字
     * @param goodsid    要添加到浏览历史中的 goodsID号
     * @param count      总共可以显示的历史记录条数
     */
    @Deprecated
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String goodsid, int count) {
        // 得到指定的cookie
        String ids = getCookie(request, cookieName);
        // 设置cookie中格的浏览记录
        ids = setValue(ids, goodsid, count);
        // 保存cookie
        saveCookie(response, cookieName, ids);
    }

    /**
     * 设置浏览历史字符串
     *
     * @param ids
     * @param bookid 最新浏览的id号
     * @param count  总共可以显示的历史记录条数
     * @return 修改后的字符串
     */
    @Deprecated
    private static String setValue(String ids, String bookid, int count) {
        // 1、 没有任何记录--》直接添加 id
        // 2、 1,2,3 4--》 4,1,2
        // 3、 1,2,3 2 --》 2,1,3
        // 4、1,2 3--》 3,1,2
        // 如果不存在Cookie或者Cookie中没有值
        StringBuffer sb = new StringBuffer();
        if (ids == null) {
            sb.append(bookid);
        } else {
            List<String> list = Arrays.asList(ids.split("\\,"));
            LinkedList<String> idsList = new LinkedList<String>(list);
            // 未浏览过
            if (!idsList.contains(bookid)) {
                if (idsList.size() < count) {
                    idsList.addFirst(bookid);
                } else {
                    idsList.removeLast();
                    idsList.addFirst(bookid);
                }
            } else {
                // 如果包含已浏览的
                idsList.remove(bookid);
                idsList.addFirst(bookid);
            }
            for (String id : idsList) {
                sb.append(id).append(",");
            }
            if (sb != null && sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
}