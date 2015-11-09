package m.w.sys.quartz.util;

import org.quartz.Calendar;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;

/**
 * 生成常用日历的工具类
 * @author WenWu
 *
 */
public class Calendars {

    /**
     *  获取一个工作日的日历
     * @param holidays 法定节假日列表
     * @param workdays 法定工作日列表
     * @return
     */
    public static WxCalendar getWorkDayCalendar(String holidays, String workdays){
        return getWorkDayCalendar(new WeeklyCalendar(), holidays, workdays);
    }
    
    /**
     * 获取一个工作日的日历
     * @param baseCalendar
     * @param holidays 法定节假日列表
     * @param workdays 法定工作日列表
     * @return
     */
    public static WxCalendar getWorkDayCalendar(Calendar baseCalendar, String holidays, String workdays){
        WxCalendar workDayCalendar = new WxCalendar(baseCalendar);
        workDayCalendar.addExcludedDate(holidays);
        workDayCalendar.addIncludedDate(workdays);
        return workDayCalendar;
    }
    
    /**
     * 获取一个工作时间的日历
     * @param holidays 法定节假日列表
     * @param workdays 法定工作日列表
     * @param amBegin 上午工作开始时间
     * @param amEnd   上午工作结束时间
     * @param pmBegin 下午工作开始时间
     * @param pmEnd   下午工作结束时间
     * @return
     */
    public static DailyCalendar getWorkTimeCalendar(String holidays, String workdays, String amBegin, String amEnd, String pmBegin, String pmEnd){
        return getWorkTimeCalendar(getWorkDayCalendar(holidays, workdays), amBegin, amEnd, pmBegin, pmEnd);
    }
    
    /**
     * 获取一个工作时间的日历
     * @param workDayCalendar
     * @param amBegin 上午工作开始时间
     * @param amEnd   上午工作结束时间
     * @param pmBegin 下午工作开始时间
     * @param pmEnd   下午工作结束时间
     * @return
     */
    public static DailyCalendar getWorkTimeCalendar(Calendar baseCalendar, String amBegin, String amEnd, String pmBegin, String pmEnd){
        DailyCalendar workTimeCalendar = new DailyCalendar(baseCalendar, amBegin, pmEnd);
        workTimeCalendar.setInvertTimeRange(true);
        return new DailyCalendar(workTimeCalendar, amEnd, pmBegin);
    }
}
