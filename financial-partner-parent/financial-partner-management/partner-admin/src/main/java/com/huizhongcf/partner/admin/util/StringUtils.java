package com.huizhongcf.partner.admin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author zj
 * @version 2013-11-19
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
	/**
	 * 按照JAVA_请求客户端ip_当前时间的long型毫秒数_3位随机数规则生成版本号
	 * @return
	 */
	public static String getVersionNumber(HttpServletRequest request){
		String newVersion = "JAVA_" + getRemoteAddr(request).replace("\\.", "-") + "_" + System.currentTimeMillis() + "_" + RandomStringUtils.random(3, false, true);
		return newVersion;
	}
	
	/**
	 * 按照JAVA_system_cancel_当前时间的long型毫秒数_3位随机数规则生成版本号
	 * @return
	 */
	public static String getVersionNumber(){
		String newVersion = "JAVA_SYSTEM_CANCEL_" + System.currentTimeMillis() + "_" + RandomStringUtils.random(3, false, true);
		return newVersion;
	}
	
	/**
	 * 判断该字符串，是不是数字
	 * @param str
	 * @return
	 */
	public static boolean validateInt(String str){
		if(StringUtils.isNotEmpty(str)){
			for(int n=0;n<str.length();n++){
				int chr=str.charAt(n);
				if(chr<48 || chr>57)
			         return false;
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取ip
	 * @param request
	 * @return String 返回ip地址
	 */
	public static String getip(HttpServletRequest request){
	   String ip = request.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP"); 
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }
       if(StringUtils.isNotBlank(ip)) {
           ip = ip.split(",")[0];
       }
       return ip;
	}
	
	/**
	 * 获取ip对应mac地址
	 * @param ipAddress ip地址
	 * @return String 返回mac地址
	 */
	public static String getmac(String ipAddress){
		String macAddress = "";
		String str = "";
		final String LOOPBACK_ADDRESS = "127.0.0.1";
		// 获取非本地IP的MAC地址
	    try {
	    	if (LOOPBACK_ADDRESS.equals(ipAddress)) {
	    		InetAddress inetAddress = InetAddress.getLocalHost();
			    // 貌似此方法需要JDK1.6。
			    byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			    // 下面代码是把mac地址拼装成String
			    StringBuilder sb = new StringBuilder();
			    for (int i = 0; i < mac.length; i++) {
			    	if (i != 0) {
			    		sb.append("-");
			    	}
			    	// mac[i] & 0xFF 是为了把byte转化为正整数
			    	String s = Integer.toHexString(mac[i] & 0xFF);
			    	sb.append(s.length() == 1 ? 0 + s : s);
			    }
			    // 把字符串所有小写字母改为大写成为正规的mac地址并返回
			    macAddress = sb.toString().trim().toUpperCase();
	    	} else {
		        Process p = Runtime.getRuntime()
		            .exec("nbtstat -A " + ipAddress);
		        InputStreamReader ir = new InputStreamReader(p.getInputStream());
		         
		        BufferedReader br = new BufferedReader(ir);
		       
		        while ((str = br.readLine()) != null) {
		          if(str.indexOf("MAC")>1){
		            macAddress = str.substring(str.indexOf("MAC")+9, str.length());
		            macAddress = macAddress.trim();
		            break;
		          }
		        }
		        p.destroy();
		        br.close();
		        ir.close();
			}
	    } catch (IOException ex) {
			ex.getMessage();
	    }
		return macAddress;
	}
}
