package m.w.sys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import m.w.App;
import m.w.Const;
import m.w.sys.util.StringUtils;

import m.w.sys.util.DateUtils;

public class CommonUtils {
	private static final SimpleDateFormat shortYearFormat = new SimpleDateFormat("yy");
	/** 获取两位长度的年 */
	public static String getShortYear(Date date){
		return shortYearFormat.format(date);
	}

	/**
	 * 指定日期加上给定工作日数目后的时间
	 * @param day
	 * @return
	 *  如果day为大于0的整数，则返回当前日期后指定工作日的时间字符串，格式如："'2013-06-26 16:55:46'"<br/>
	 *  如果day为空或小于0的数，则返回 "null"
	 */
	public static String nextWorkDay(String day){
		if(StringUtils.hasText(day)){
			int count = Integer.parseInt(day);
			if(count > 0){
				Date now = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(now);
				while(count > 0){
					cal.add(Calendar.DATE, 1);
					Date date = cal.getTime();
					if(isWorkday(date)){
						count--;
					}
				}
				return "'" + DateUtils.toString(cal.getTime(), Const.DEFAULT_DATETIME_FORMAT) + "'";
			}
		}
		return "null";
	}
	
	/**
	 * 判断给定的日期是否为工作日，当前只排除了周六日
	 * @param date
	 * @return
	 */
	public static boolean isWorkday(Date date){
		String strDate = DateUtils.toString(date);
		if(App.getWorkdays().contains(strDate)){//法定节工作日
			return true;
		}else if(App.getHolidays().contains(strDate)){//法定假日
			return false;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if(Calendar.SUNDAY==day || Calendar.SATURDAY==day){//普通周六日
				return false;
			}
		}
		return true;
	}
}
