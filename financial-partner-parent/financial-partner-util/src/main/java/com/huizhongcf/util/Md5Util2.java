package com.huizhongcf.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

//import com.huizhong.interceptor.MD5Util;


 

/**
 *  md5加密方法
  * @ClassName: Md5Util
  * @Description: TODO
  * @author Comsys-chengqiang
  * @date 2014-12-30 上午10:47:23
  *
 */
public class Md5Util2 {
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
    	return Md5Util2.getMD5String(StringUtils.join(new String[]{systemId,date,size}, ","),key); 
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
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String md5(String string, String salt) {
        if (StringUtils.isBlank(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + salt).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    
    public static void main(String[] args) {
        try {
			System.out.println(md52php("fff2f795bc974b5f88ddf519c238ee4e","123456"));
			System.out.println(md5("hzcf1234","fff2f795bc974b5f88ddf519c238ee4e"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
