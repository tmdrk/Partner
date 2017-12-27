package com.huizhongcf.util;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *
 * Description:获得ip地址的工具类
 *
 * @author ouyangjin
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2013-7-7      ouyangjin       1.0         1.0 Version 
 * </pre>
 */
public class IPUtil {

	/**
	 * 获得IP
	 * @param request
	 * @return
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
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

	/**
	 * 获得 本机内网或者外网IP
	 * @return
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces =
		NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP

		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress()
				&& !ip.isLoopbackAddress()
				&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP

					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress()
				&& !ip.isLoopbackAddress()
				&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
	public static void main(String[] args) throws SocketException {
		System.out.println(IPUtil.getRealIp());
	}
}
