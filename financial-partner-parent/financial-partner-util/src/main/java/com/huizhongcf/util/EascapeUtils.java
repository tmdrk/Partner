package com.huizhongcf.util;
import org.apache.commons.lang3.StringEscapeUtils;
/**
 * @author xxc
 * 【1.0 针对JS HTML xss 注入】
 * 【2.0 针对SQL 注入转义字符】
 * 
 * */
public class EascapeUtils {
	
	//-- 禁止外部构造
	private EascapeUtils(){
		
	}
	
	/**
	 * html入参转义
	 * */
	public static String escapeHtml(String text){
		
		return	StringEscapeUtils.escapeHtml4(text);
	}
	
	/**
	 * html反转义入参转义
	 * */
    public static String unescapeHtml(String text){
		
		return	StringEscapeUtils.unescapeHtml4(text);
	}
    
    /***
     * escapeSql  防止SQL 注入
     * */
    
     public static String escapeSql(String text){
    	 
    	 return	org.apache.commons.lang.StringEscapeUtils.escapeSql(text);
     }
     
     public static void main(String args[]){
    	 /***************转义和还原字符begin************************/
    	 String aa1 = EascapeUtils.escapeHtml("/'<scrpit>alert('/xss查询数据密码')<scrpit>///\\");
   		 System.out.println(aa1);
   		 
   		 String b1 = EascapeUtils.unescapeHtml(aa1);
   		 System.out.println(b1);
   		 /***************转义和还原字符end************************/
   		 
   		 /*******************sql注入案例begin 查询出全部结果*******************************/
   		 String username="1' or '1'='1";
   		 
   		 String sql="select * from bid_info where bid_id='"+username+"'";
         System.out.println(sql);
         /*******************sql注入案例end*******************************/
         
         /********************以下代码控制SQLz注入*********************/
         String sql1="select * from bid_info where bid_id='"+EascapeUtils.escapeSql(username)+"'";
         System.out.println(sql1);
     }
}
