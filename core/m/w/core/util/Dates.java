package m.w.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 日期工具函数
 * 
 * @author WenWu
 * 
 */
public abstract class Dates {
    private static Log log = Logs.get();
    public static final String DATE = "yyyy-MM-dd";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期字符串转换为日期
     * 
     * @param date
     * @return
     */
    public static Date string2Date(String date) {
        return string2Date(date, DATE);
    }

    /**
     * 日期时间字符串转换为日期
     * 
     * @param datetime
     * @return
     */
    public static Date string2Datetime(String datetime) {
        return string2Date(datetime, DATETIME);
    }

    /**
     * 字符串转换为日期
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static Date string2Date(String date, String pattern) {
        if(Strings.isBlank(date)){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(date);
        }
        catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 转换为日期字符串
     * 
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, DATE);
    }

    /**
     * 转换为日期时间字符串
     * 
     * @param date
     * @return
     */
    public static String datetime2String(Date date) {
        return date2String(date, DATETIME);
    }

    /**
     * 日期转换为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern) {
        if(date == null){
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 指定年月日时分秒
     * 
     * @param year
     * @param month
     * @param date
     * @param hourOfDay
     * @param minute
     * @param second
     * @return
     */
    public static Date datetime(int year, int month, int date, int hour, int minute, int second) {
        Calendar cal = cal();
        cal.clear();
        cal.set(year, month, date, hour, minute, second);
        return cal.getTime();
    }

    /**
     * 指定年月日
     * 
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date date(int year, int month, int date) {
        Calendar cal = cal();
        cal.clear();
        cal.set(year, month, date);
        return cal.getTime();
    }

    /**
     * 当前的时间，年月日时分秒
     * 
     * @return
     */
    public static Date now() {
        Calendar cal = cal();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        cal.clear();
        cal.set(year, month, date, hour, minute, second);
        return cal.getTime();
    }

    /**
     * 今天，年月日
     * 
     * @return
     */
    public static Date today() {
        Calendar cal = cal();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        return cal.getTime();
    }

    /**
     * 明天，年月日
     * 
     * @return
     */
    public static Date tomorrow() {
        Calendar cal = cal();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        cal.roll(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 昨天，年月日
     * 
     * @return
     */
    public static Date yesterday() {
        Calendar cal = cal();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        cal.roll(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取日历
     * 
     * @return
     */
    private static Calendar cal() {
        return Calendar.getInstance();
    }
    
    /**
     * 获得本周的第一天
     * @return
     */
    public static Date getFirstDayOfThisWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (-1 * day + 1));
        return cal.getTime();
    }
    
    /**
     * 获得本周的最后一天
     * @return
     */
    public static Date getLastDayOfThisWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (7 -1 * day));
        return cal.getTime();
    }
    
    /**
     * 获取本周为本年第几周
     * @return
     */
    public static Integer getWeekOfYear(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    
    /**
     * 获得下周的第一天
     * @return
     */
    public static Date getFirstDayOfNextWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (-1 * day + 8));
        return cal.getTime();
    }
    
    /**
     * 获得下周的最后一天
     * @return
     */
    public static Date getLastDayOfNextWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (14 -1 * day));
        return cal.getTime();
    }
    
    /**
     * 获取下周为本年第几周
     * @return
     */
    public static Integer getNextWeekOfYear(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today());
        cal.add(Calendar.DATE, 7);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 当前时间后指定天数
     * @param day
     * @return
     */
    public static Date addDay(Integer day){
        return addDay(new Date(), day);
    }
    
    /**
     * 给指定时间增加指定的天数
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, Integer day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }
    
    /**
     * 给指定时间增加指定的天数
     * @param date
     * @param day
     * @return
     */
    public static String addmonth(String date, Integer month){
    	
        Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			cal.setTime(format.parse(date));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		cal.add(Calendar.MONTH,month);
        
        return format.format(cal.getTime());
    }
}
