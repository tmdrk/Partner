package com.huizhongcf.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
/**
 * 日期工具类。<br>
 *
 * @author zhou jintong
 * @version 1.0
 */
public class DateUtil {
    /**
     * @Title: getCurrentMonthFirstDayDate
     * @Description:获取当月第一天，年月日时分秒
     * @param @return
     * @param @throws ParseException
     * @return Date    返回类型
     * @throws
     */
    public static Date getCurrentMonthFirstDayDate() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);//设置当月
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);//设置为0时
        c.set(Calendar.MINUTE, 0);//设置为0分
        c.set(Calendar.SECOND,0);//设置为0秒
        c.set(Calendar.MILLISECOND, 0);//设置为0毫秒
        String first = format.format(c.getTime());
        Date firstDate=format.parse(first);
        return firstDate;
    }
    /**
     * @Title: getCurrentMonthLastDayDate
     * @Description:获取当月最后一天，年月日时分秒
     * @param @return
     * @param @throws ParseException
     * @return Date    返回类型
     * @throws
     */
    public static Date getCurrentMonthLastDayDate() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, 0);//设置当月
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//设置当月
        ca.set(Calendar.HOUR_OF_DAY, 23);//将小时至23
        ca.set(Calendar.MINUTE, 59);//将分钟至59
        ca.set(Calendar.SECOND,59);//将秒至59
        ca.set(Calendar.MILLISECOND, 999);//将毫秒至999
        String last = format.format(ca.getTime());
        Date lastDate=format.parse(last);
        return lastDate;
    }
    /**
     * @Title: getLastMonthFirstDayDate
     * @Description:获取上一个月第一天，年月日时分秒
     * @param @return
     * @param @throws ParseException
     * @return Date    返回类型
     * @throws
     */
    public static Date getLastMonthFirstDayDate() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);//设置上一个月
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);//设置为0时
        c.set(Calendar.MINUTE, 0);//设置为0分
        c.set(Calendar.SECOND,0);//设置为0秒
        c.set(Calendar.MILLISECOND, 0);//设置为0毫秒
        String first = format.format(c.getTime());
        Date firstDate=format.parse(first);
        return firstDate;
    }
    /**
     * @Title: getLastMonthLastDayDate
     * @Description:获取上一个月最后一天，年月日时分秒
     * @param @return
     * @param @throws ParseException
     * @return Date    返回类型
     * @throws
     */
    public static Date getLastMonthLastDayDate() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, -1);//设置上一个月
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//设置当月
        ca.set(Calendar.HOUR_OF_DAY, 23);//将小时至23 
        ca.set(Calendar.MINUTE, 59);//将分钟至59
        ca.set(Calendar.SECOND,59);//将秒至59    
        ca.set(Calendar.MILLISECOND, 999);//将毫秒至999    
        String last = format.format(ca.getTime());
        Date lastDate=format.parse(last);
        return lastDate;
    }
	
	/**
	 * @Title: getDateStringByApplyPattern
	 * @Description:对Date时间，转换成字符串
	 * @param @return
	 * @param @throws ParseException
	 * @return Date    返回类型
	 * @throws
	 */
	public static String getDateStringByApplyPattern(Date date,String applyPattern) throws ParseException{
		if(date==null || StringUtils.isBlank(applyPattern)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(applyPattern);
		String result = format.format(date);
		return result;
	}

    /**
     * 按照指定的格式返回日期字符串. 默认 "yyyy-MM-dd"
     *
     * @param Date
     * @param String
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return "";
        if (pattern == null)
            pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return (sdf.format(date));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatDateTime() {
        return (formatDate(now(), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 当前时间 yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatDateTime2() {
        return formatDateTime2(now());
    }

    /**
     * 当前时间 yyyyMMddHHmmss
     *
     * @param 传入参数为时间类型，当入参为null时返回null
     * @return yyyyMMddHHmmss 时间格式字符串
     */
    public static String formatDateTime2(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date, "yyyyMMddHHmmss");
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return (formatDate(date, "yyyy-MM-dd"));
    }

    /**
     * 当前日期 yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate() {
        return (formatDate(now(), "yyyy-MM-dd"));
    }

    /**
     * 当前日期 yyyyMMdd
     *
     * @return
     */
    public static String formatDate2() {
        return (formatDate(now(), "yyyyMMdd"));
    }
    
    public static String formatDate2(Date date) {
        return (formatDate(date, "yyyyMMdd"));
    }
    
    public static String formatDate3(Date date) {
        return (formatDate(date, "yyyy年MM月dd日"));
    }
    /**
     * HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        return (formatDate(date, "HH:mm:ss"));
    }

    /**
     * 当前时间 HH:mm:ss
     *
     * @return
     */
    public static String formatTime() {
        return (formatDate(now(), "HH:mm:ss"));
    }

    /**
     * 当前时间 HHmmss
     *
     * @return
     */
    public static String formatTime2() {
        return (formatDate(now(), "HHmmss"));
    }

    /**
     * 当前时间 Date类型
     *
     * @return
     */
    public static Date now() {
        return (new Date());
    }

    /**
     * yyyy-MM-dd HH:mm:ss 转Date
     *
     * @param datetime
     * @return
     */
    public static Date parseDateTime(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ((datetime == null) || (datetime.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(datetime);
            } catch (ParseException e) {
                return parseDate(datetime);
            }
        }
    }

    /**
     * yyyy-MM-dd 转Date
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }
    /**
     * yyyyMMdd 转Date
     *
     * @param date
     * @return
     */
    public static Date parseDateToyyyyMMdd(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static Date parseDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static Date parseDate(Date datetime,String format) {
    	 SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (datetime == null) {
            return null;
        } else {
            try {
                return formatter.parse(formatter.format(datetime));
            } catch (ParseException e) {
            	e.printStackTrace();
                return null;
            }
        }
    }
    
    
    public static Date parseDate(Date datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (datetime == null) {
            return null;
        } else {
            try {
                return formatter.parse(formatter.format(datetime));
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static String formatDate(Object o) {
        if (o == null)
            return "";
        if (o.getClass() == String.class)
            return formatDate((String) o);
        else if (o.getClass() == Date.class)
            return formatDate((Date) o);
        else if (o.getClass() == Timestamp.class) {
            return formatDate(new Date(((Timestamp) o).getTime()));
        } else
            return o.toString();
    }

    public static String formatDateTime(Object o) {
        if (o.getClass() == String.class)
            return formatDateTime((String) o);
        else if (o.getClass() == Date.class)
            return formatDateTime((Date) o);
        else if (o.getClass() == Timestamp.class) {
            return formatDateTime(new Date(((Timestamp) o).getTime()));
        } else
            return o.toString();
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date
     *            要加减前的时间，如果不传，则为当前日期
     * @param field
     *            时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
     *            Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount
     *            按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    public static Date addMilliSecond(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMiunte(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date getDate() {
        return parseDate(formatDate());
    }

    public static Date getDateTime() {
        return parseDateTime(formatDateTime());
    }

    public static boolean between(Date date, int offset, TimeUnit unit) {
        return System.currentTimeMillis() - date.getTime() <= unit.toMillis(offset);
    }
    
    /**
	 * 计算年龄
	 * @param birthDate
	 * @return
	 */
	public static  int getAge(Date birthDay) {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    } 
}