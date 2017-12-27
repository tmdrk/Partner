package com.huizhongcf.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * Description: MD5 加密
 * @author baohongjian
 * 2017年2月20日
 *
 */
public class MD5Util {
	
private static final String encryModel="MD5";
	
	private static   byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
	
	/**
	 * 
	 * Description: 按字母顺序进行排序
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年7月31日 上午10:28:32
	 */
	public static String createLinkString(List<Map<String, String>> params) {
		String prestr = "";
		for(Map<String, String> param : params){
			if(param != null && param.size() > 0){
				List<String> keys = new ArrayList<String>(param.keySet());
				Collections.sort(keys);
				for (int i = 0; i < keys.size(); i++) {
					String key = keys.get(i);
					String value = param.get(key);
					if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + value;
					} else {
						prestr = prestr + key + "=" + value + "&";
					}
				}
			}
		}
		return prestr;
	}
	
	/**
	 * 
	 * Description: MD5加密
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年8月18日 下午4:10:01
	 */
	public static String getSign(List<Map<String, String>> params,String key,String timestamp){
		String str = createLinkString(params)+"&key="+key+"&timestamp="+timestamp;
		String mysign = DigestUtils.md5Hex(getContentBytes(str, "utf-8"));
		return mysign.toUpperCase();
	}
	
	/**
     * 32位md5.
     * @param str
     * @return
     */
    public  static String md5(String str) {
        return encrypt(encryModel, str);
    }

    //得到加密后的字符
    public static String encrypt(String algorithm, String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(str.getBytes());
            StringBuffer sb = new StringBuffer();
            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i] & 0xFF;
                if (b < 0x10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }


}
