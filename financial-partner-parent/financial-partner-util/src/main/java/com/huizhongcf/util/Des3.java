package com.huizhongcf.util;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class Des3 {

    private static final String Algorithm = "DESede"; 
	
	public static final String keycode="ESJPWIgQQDgoJXlRy91VZncpdJgwQEDi";
	
	
	private Des3(){
		
	}
	
	public static byte[] encryptMode(String keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(Base64.decodeBase64(keybyte.getBytes()), Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return Base64.encodeBase64(c1.doFinal(src));
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decryptMode(String keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(Base64.decodeBase64(keybyte.getBytes()), Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}
	
	public static void main(String[] args) {
		String szSrc = "This is a 3DES test. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdThis is a 3DES test. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcdtest. 测试abcabcd";
		System.out.println("加密前的字符串:" + szSrc);
		byte[] encoded = encryptMode(Des3.keycode, szSrc.getBytes());
		System.out.println("加密后的字符串:" + new String(encoded));
		byte[] srcBytes = decryptMode(Des3.keycode, Base64.decodeBase64(encoded));
		System.out.println("解密后的字符串:" + (new String(srcBytes)));
		String str1="1KYgDEmsE7CLqktIvgZaz0NAekkGHozDYEk/n7xv+gBYG26PzGfyJG8Xht+d/E5sZuCM940j9yv6+kCVZxSCOPNljTJOAeZz2impVBQyNBA=";
		//解密后的字符串:{"transCode":"TRS001001","reTurnCode":"2000","reTurnMsg":"接收成功"}
		byte[] srcBytes1 = decryptMode(Des3.keycode, Base64.decodeBase64(str1.getBytes()));
		System.out.println("解密后的字符串:" + (new String(srcBytes1)));
	}
}


