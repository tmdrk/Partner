package com.huizhongcf.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	private static final ThreadLocal<String> threadlocal = new ThreadLocal<String>();
	
	private static Object obj = new Object();
	
	/**
	 * 
	 * Description: 获取请求包体
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年8月21日 上午10:25:23
	 */
	public static String getBody(){
		synchronized (obj) {
			return threadlocal.get();
		}
	}
	
	public static void setBody(String body){
		synchronized (obj) {
			threadlocal.set(body);
		}
	}


	/**
	 * 由出生日期获得年龄  
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay){  
		
		//保单生效日期
		Date beginDate = getPolicyBeginDate(null);
		Calendar cal = Calendar.getInstance();  
		cal.setTime(beginDate);
		
		if (cal.before(birthDay)) {  
			throw new RuntimeException("出生日志不能大于当前时间");  
		}  
		int yearNow = cal.get(Calendar.YEAR);  
		int monthNow = cal.get(Calendar.MONTH);  
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
		cal.setTime(birthDay);  

		int yearBirth = cal.get(Calendar.YEAR);  
		int monthBirth = cal.get(Calendar.MONTH);  
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  

		int age = yearNow - yearBirth;  

		if (monthNow > monthBirth) {
			age++;
		}else if(monthNow == monthBirth){
			if(dayOfMonthNow >= dayOfMonthBirth){
				age++;
			}
		}
		return age - 1;//周岁
	}  
	
	/**
	 * 保单生效日期
	 * @return
	 */
	public static Date getPolicyBeginDate(Date date){
		Calendar calendar = Calendar.getInstance();
		if(date != null){
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.MINUTE, 0);
		calendar.add(Calendar.SECOND, 0);
		calendar.add(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * Description: 获取保单失效日期
	 *
	 * @param 
	 * @return Date 保单生效日期
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年9月14日 上午9:24:52
	 */
	public static Date getPolicyEndDate(Date policyBeginDate){
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(policyBeginDate);
	    calendar.add(Calendar.YEAR,1);
	    calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
		
		
	}
	
	/**
	 * 获取保单号
	 * @return
	 */
	public static String getOrderNo(int num){
		// T 20170314172559 4571004292085
		String format = DateUtil.formatDateTime2();
		String str = "T"+format+getRandNum(num);
		return str;
	}
	
	/**
	 * 生成随机数
	 * @param num 随机数的位数
	 * @return
	 */
	private static String getRandNum(int num) {
		//String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String a = "0123456789";
		String str = "";
		for (int i = 0; i < num; i++) 
		{ 
			int rand = (int) (Math.random() * a.length()); 
			str += a.charAt(rand); 
		} 
		return str;
	}
	
	
	/**  
	* 取得两个日期的间隔天数 
	* return t2 与t1的间隔天数  
	*/   
	@SuppressWarnings("unused")
	public static int getBetweenDays(Date dateStart,Date dateEnd){   
	        /*DateFormat format = new SimpleDateFormat("yyyyMMdd");   
	        int betweenDays = 0;   
	        Date d1 = format.parse(t1);   
	        Date d2 = format.parse(t2);   */
		
	        Calendar c1 = Calendar.getInstance();   
	        Calendar c2 = Calendar.getInstance();   
	        c1.setTime(dateStart);   
	        c2.setTime(dateEnd);   
	        // 保证第二个时间一定大于第一个时间   
	        if(c1.after(c2)){   
	            c1 = c2;   
	            c2.setTime(dateStart);   
	        }
	        int betweenDays = 0;  
	        int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);   
	        betweenDays = c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR);   
	        for(int i=0;i<betweenYears;i++){   
	            int tmp=countDays(c1.get(Calendar.YEAR));  
	            betweenDays+=countDays(c1.get(Calendar.YEAR));  
	            c1.set(Calendar.YEAR,(c1.get(Calendar.YEAR)+1));   
	        }   
	        return betweenDays;   
	    } 
	
	/**
	 * 计算某年有多少天
	 * @param year
	 * @return
	 */
    public static int countDays(int year){  
        int n=0;  
        for (int i = 1; i <= 12; i++) {    
            n += countDays(i,year);    
        }    
        return n;  
    }
    
    /**
	  * 计算某年某月有多少天
	  * @param month
	  * @param year
	  * @return
	  */
	public static int countDays(int month, int year){    
	        int count = -1;    
	        switch(month){    
	          case 1:    
	          case 3:       
	          case 5:    
	          case 7:    
	          case 8:    
	          case 10:    
	          case 12:    
	            count = 31;    
	            break;    
	          case 4:    
	          case 6:    
	          case 9:    
	          case 11:    
	              count = 30;    
	              break;    
	          case 2:    
	              if(year % 4 == 0)    
	                  count = 29;    
	              else    
	                  count = 28;    
	              if((year % 100 ==0) & (year % 400 != 0))    
	                      count = 28;    
	        }    
	        return count;    
	} 
	
	
	/**
	 * 
	 * Description: 获取请求包体
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年8月18日 下午3:20:55
	 */
	public static String getBody(HttpServletRequest request) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br=new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8")); 
			String line = null;  
			while ((line = br.readLine()) != null) {  
			      sb.append(line);
			}
		}catch(Exception e){
			logger.error("", e);
			if(br != null){
				br.close();
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * Description: 获取session
	 *
	 * @param 
	 * @return void
	 * @throws 
	 * @Author bao
	 * Create Date: 2017年9月28日 下午6:32:13
	 */
	public static HttpSession getSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		return session;
	}
	
	
	
	public static void main(String[] args) throws ParseException {
		/*Date d = DateUtil.parseDate("2002-04-21");
		int age = getAge(d);
		System.out.println(age);*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateStart = dateFormat.parse("20170227");
		Date dateEnd = dateFormat.parse("20170428");
		//Date dateEnd = getPolicyBeginDate();
		int days = getBetweenDays(dateStart, dateEnd);
		System.out.println(days);
	}
	
	
}
