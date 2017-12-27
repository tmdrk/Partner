package com.huizhongcf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
/**
 * 
* @ClassName: HttpRequestUtil 
* @Description: Http请求
* @author chengqiang
* @date 2015-5-22 下午4:51:01 
*
 */
public class HttpRequestUtil {
	
	//openAccToCrm.do?openAccToCrmParms=
	//queryCustomerInfo.do?queryCustomerParms=
	//openAccToPay.do?openAccToPayParms=
	//investHandBill.do?investHandBillParms=
//	public static final String POST_URL = "http://10.10.9.101:8080/public/webservice/p2p/services/notifyLenderReports";
	
	 public static String sendGet(String url, String param) throws Exception {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + param;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            throw new Exception("发送GET请求出现异常！" , e);
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }
	 /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数
     * @return 所代表远程资源的响应结果
	 * @throws Exception 
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setRequestProperty("Content-Type", "text/plain");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流     getTargetException()
            out = new PrintWriter(conn.getOutputStream());//getOutputStream()
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	System.out.println("-------");
        	e.printStackTrace();
        	System.out.println("-------");
            throw new Exception("发送 POST 请求出现异常！", e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close(); 
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
	
    
//    public static String ulrParam() throws Exception{
//		String str = "{'systemSourceId':'aaa','signature':'15-37-84-961576-1118365-48-115-59-74103-930','mobile':'16782312312'}";
////		String str = "{'systemSourceId':'aaa','customerId':'123456','bankAccount':'6122112344555533','signature':'26-5469-116-765734116-1019163-6710040-11172','province':'110000','cityId':'110100','bankCode':'102','accountBankName':'102','contractDate':'123'}";
////		String str = "{'systemSourceId':'aaa','signInfo':'1','list':[{'bankAccount':'111111','customerId':'22222'},{'bankAccount':'333333','customerId':'333333'}],'signature':'-9311874-2953-2110182-27-85-109-6473-104-5541'}";
//		List<insetVo> list = new ArrayList<insetVo>();
//		insetVo insetVo = new insetVo();
//		insetVo.setBankAccount("22222");
//		insetVo.setCustomerId("22222");
//		insetVo insetVo2 = new insetVo();
//		insetVo2.setBankAccount("33333");
//		insetVo2.setCustomerId("3333");
//		list.add(insetVo);list.add(insetVo2);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("systemSourceId", "aaa");
//		map.put("signature", "33-8-9450991080-653381-41-814222-46-73");
//		map.put("signInfo", "1");
//		map.put("list", list);
////		String str= JsonUtil.jsonExcDeepEncode(map);
//		
//		String key = "01b503cf15f16f5e9c95938d09ef1219";
//		System.out.println(str);
////		//加密过的二进制数组转化成16进制的字符串
//		String encrytStr = AESUtil.enCrypt(str,key);
//		System.out.println("加密后："+encrytStr);
//		String decrytStr = AESUtil.deCrypt(encrytStr,key);
//		System.out.println("解密后："+decrytStr);
//		return encrytStr;
//	}
//    
//    public static void main(String[] args) throws Exception {
//    	 String parms = "notifyLenderReportsParams="+ulrParam(); 
//    	 String string = sendPost(POST_URL,parms);
//    	 System.out.println(string);
//	}
}
