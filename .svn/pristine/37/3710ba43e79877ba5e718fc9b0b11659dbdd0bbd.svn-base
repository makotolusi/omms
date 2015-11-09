package m.w.sys.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import m.w.Const;


/** 字符串格式化工具 */
public class DateUtils {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Const.DEFAULT_DATE_FORMAT);
	/** 按默认格式转换为字符串 */
	public static String toString(Date date){
		return toString(date, dateFormat);
	}
	
	/** 按指定格式转换为字符串 */
	public static String toString(Date date, String format){
		return toString(date, new SimpleDateFormat(format));
	}
	
	/** 按指定格式转换为字符串 */
	public static String toString(Date date, DateFormat format){
		return date == null ? "" : format.format(date);
	}

	/** 按默认格式获得今天的字符串 */
	public static String getToday(){
		return toString(new Date());
	}
	
	/** 按默认时间日期格式获得今天的字符串 */
	public static String getDateTime(){
		return toString(new Date(), Const.DEFAULT_DATETIME_FORMAT);
	}
	
	/** 按给定格式获得今天的字符串 */
	public static String getToday(String format){
		return toString(new Date(), format);
	}
	
	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getCurrMonthLast(String date) {

		String[] sp = date.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), 31, 23, 59, 59);
		return c.getTime();
		
	}
}
