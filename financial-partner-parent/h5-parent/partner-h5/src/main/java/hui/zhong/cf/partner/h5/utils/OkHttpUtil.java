package hui.zhong.cf.partner.h5.utils;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import org.apache.commons.httpclient.HttpException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 使用okhttp实现的http工具类
 *
 * @author LiuXianfa
 * @date 2017/12/21
 */
public class OkHttpUtil {
    private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient();

    static {
        OKHTTP_CLIENT.setConnectTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("===========测试POST请求===========");
        String s = post("http://localhost:8080/api/user/checkUserName", "{'userName':'13240304866'}");
        JSONObject jsonObj = parse(s);
        System.out.println(jsonObj);
        System.out.println("errorCode:\t" + jsonObj.getString("errorCode"));

        System.out.println("\n===========测试GET请求===========");
        String s1 = get("http://localhost:8080/api/user/checkUserName?userName=13240304866");
        JSONObject jsonObj1 = parse(s1);
        System.out.println(jsonObj1);
        System.out.println("errorCode:\t" + jsonObj1.getString("errorCode"));
    }

    /**
     * 无参GET请求
     *
     * @param url 请求url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * 无参GET请求
     *
     * @param url 请求url
     * @return
     * @throws IOException
     */
    public static String post(String url) throws IOException {
        return post(url, Collections.<String, String>emptyMap());
    }


    /**
     * GET请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 响应体(responseBody)
     * @throws IOException 当{@link Response#isSuccessful()}返回false;
     */
    public static String get(String url, Map<String, String> params) throws IOException {

        // 把参数拼接到url后
        ArrayList<BasicNameValuePair> nameValuePairs;
        if (params != null && !params.isEmpty()) {
            nameValuePairs = new ArrayList<>();
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                nameValuePairs.add(new BasicNameValuePair(key, params.get(key)));
            }
            url = attachHttpGetParams(url, nameValuePairs);
        }

        Request request = new Request.Builder()
                .addHeader("OS", "H5")
                .url(url)
                .build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new HttpException(response + " responseBody:" + response.body());
        }
    }

    /**
     * POST请求
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 响应体(responseBody)
     * @throws IOException 当{@link Response#isSuccessful()}返回false;
     */
    public static String post(String url, Map<String, String> params) throws IOException {

        // 构造request请求，并添加参数
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (params != null && !params.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String value = entry.getValue();
                if (value != null && !value.trim().isEmpty()) {
                    builder.add(entry.getKey(), value);
                }
            }
        }

        Request request = new Request.Builder()
                .addHeader("OS", "H5")
                .url(url)
                .post(builder.build())
                .build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new HttpException(response + " responseBody:" + response.body());
        }
    }

    /**
     * POST请求
     *
     * @param url        请求url
     * @param jsonParams 请求参数
     * @return 响应体(responseBody)
     * @throws IOException 当{@link Response#isSuccessful()}返回false;
     */
    public static String post(String url, String jsonParams) throws IOException {

        Map params = JSONObject.parseObject(jsonParams, Map.class);

        return post(url, params);
    }

    /**
     * 执行一个请求<br />
     * 注释：不会开启异步线
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return OKHTTP_CLIENT.newCall(request).execute();
    }

    /**
     * 把参数拼接成<code>key1=value1&key2=value2</code>的格式
     *
     * @param params 参数
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, "UTF-8");
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }

    /**
     * <b>把json字符串解析成JSONObject</b><br /><br />
     * 转成JSONObject而不解析成map的好处：<br />
     * 使用{@link JSONObject}以下方法，就不需要自己手动转换类型了：<br />
     * {@link JSONObject#getString(java.lang.String)}、
     * {@link JSONObject#getInteger(String)}、
     * {@link JSONObject#getBoolean(String)}
     *
     * @param json json格式字符串
     * @return
     */
    public static JSONObject parse(String json) {
        return (JSONObject) com.alibaba.fastjson.JSON.parse(json);
    }

    /**
     * 判断请求是否成功。
     *
     * @param responseBody
     * @return
     */
    public static boolean isSuccessful(JSONObject responseBody) {

        return "200".equalsIgnoreCase(responseBody.getString("errorCode"));
    }

    /**
     * 判断请求是否成功。
     *
     * @param responseBody
     * @return
     */
    public static boolean isSuccessful(String responseBody) {

        return isSuccessful(parse(responseBody));
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        OKHTTP_CLIENT.newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static void enqueue(Request request) {
        OKHTTP_CLIENT.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response arg0) throws IOException {

            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }
}
