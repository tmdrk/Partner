package com.huizhongcf.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;


/**
 *<dl>
 *<dt>类名：SystemMessage.java</dt>
 *<dd>
 *		描述: 加密类，
 *		用于“CRM系统”和“综合业务理财端”的接口对接
 *</dd> 
 *<dd>创建时间： 2017年8月10日 下午2:18:36</dd>
 *<dd>创建人： peigaoxiang</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月10日 下午2:18:36    peigaoxiang       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public class MD5Utils {
	
	/**
	 * 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sourceStr 待加密数据
	* @param @return    
	* @return String    返回类型 
	* @throws
	 */
	public static String encode(String sourceStr){
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			String str = new String(md5.digest(sourceStr.getBytes("utf-8")));
			String encode = Base64Util.encode(str);
			return encode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	* @Title: getMD5 
	* @Description: java
	* @param @param val
	* @param @return
	* @param @throws NoSuchAlgorithmException    设定文件 
	* @return String    返回类型 
	* @author chengqiang
	* @date 2015-9-6 下午2:27:38 
	* @throws
	 */
	public static String getMD5(String val) throws NoSuchAlgorithmException{  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        md5.update(val.getBytes());  
        byte[] m = md5.digest();//加密  
        return getString(m);  
	}  
	
    private static String getString(byte[] b){  
        StringBuffer sb = new StringBuffer();  
         for(int i = 0; i < b.length; i ++){  
          sb.append(b[i]);  
         }  
         return sb.toString(); 
    }
    
    /**
     * key加密
     * @param key */
    public static String getMD5String(String str, String key) {
    	String result = null;
		try {
			result = getMD5(str+key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    /**
     * 
    * @Title: p2pMd5 
    * @Description: 与p2p进行的md5加密
    * @param @param systemId
    * @param @param date
    * @param @param size
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author chengqiang
    * @date 2015-5-7 上午10:19:21 
    * @throws
     */
    public static String p2pMd5(String systemId,String date,String size,String key){
    	return MD5Utils.getMD5String(StringUtils.join(new String[]{systemId,date,size}, ","),key); 
    }
    /**
     * 
    * @Title: md5 
    * @Description: 与php之间的md5
    * @param @param inputStr
    * @param @return
    * @param @throws NoSuchAlgorithmException    设定文件 
    * @return String    返回类型 
    * @author chengqiang
    * @date 2015-9-6 下午2:27:56 
    * @throws
     */
    public static String md52php(String key,String val) throws NoSuchAlgorithmException {
	    String md5Str = key+val;
	    if(md5Str != null) {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(md5Str.getBytes());
	        BigInteger hash = new BigInteger(1, md.digest());
	        md5Str = hash.toString(16);
	        if((md5Str.length() % 2) != 0) {
	            md5Str = "0" + md5Str;
	        }
	    }
	    return md5Str;
	}
}
