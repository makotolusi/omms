package m.w.sys.quartz.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

import m.w.core.util.Dates;

import org.nutz.lang.Strings;
import org.quartz.Calendar;
import org.quartz.impl.calendar.BaseCalendar;

/**
 * 一个扩展的节假日日历，可以设定节假日与工作日，其中设定的工作日优先级最高
 * @author WenWu
 *
 */
public class WxCalendar extends BaseCalendar implements Calendar, Serializable {
    private static final long serialVersionUID = -7467172822402687988L;
    public static String DEFAULT_DATE_FROMART = "yyyy-MM-dd";
    
    // A sorted set to store the holidays
    private TreeSet<Date> holidays = new TreeSet<Date>();
    // A sorted set to store the workdays
    private TreeSet<Date> workdays = new TreeSet<Date>();

    public WxCalendar() {
    }

    public WxCalendar(Calendar baseCalendar) {
        super(baseCalendar);
    }

    public WxCalendar(TimeZone timeZone) {
        super(timeZone);
    }

    public WxCalendar(Calendar baseCalendar, TimeZone timeZone) {
        super(baseCalendar, timeZone);
    }

    @Override
    public Object clone() {
        WxCalendar clone = (WxCalendar) super.clone();
        clone.holidays = new TreeSet<Date>(holidays);
        clone.workdays = new TreeSet<Date>(workdays);
        return clone;
    }

    /**
     * 是否包含在指定的工作日中
     * @param timeStamp
     * @return
     */
    public boolean isWorkdayIncluded(long timeStamp) {
        Date lookFor = getStartOfDayJavaCalendar(timeStamp).getTime();
        if(workdays.contains(lookFor)){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isTimeIncluded(long timeStamp) {
        Date lookFor = getStartOfDayJavaCalendar(timeStamp).getTime();
        if(workdays.contains(lookFor)){
            return true;
        }
        if (super.isTimeIncluded(timeStamp) == false) {
            return false;
        }

        return !(holidays.contains(lookFor));
    }

    @Override
    public long getNextIncludedTime(long timeStamp) {
        //首先查看是否包含在指定工作日中，然后查看base calendar，最后查看指定的节假日
        long workdayTime = 0;
        long maxWorkdayTime = 0;
        if(!workdays.isEmpty()){
            maxWorkdayTime = workdays.last().getTime();
        }
        
        java.util.Calendar workday = getStartOfDayJavaCalendar(timeStamp);
        while(workday.getTimeInMillis() < maxWorkdayTime){
            if (isWorkdayIncluded(workday.getTime().getTime()) == false) {
                workday.add(java.util.Calendar.DATE, 1);
            }else{
                workdayTime = workday.getTime().getTime();
                break;
            }
        }

        long baseTime = super.getNextIncludedTime(timeStamp);
        if ((baseTime > 0) && (baseTime > timeStamp)) {
            if(workdayTime > 0 && workdayTime > timeStamp){
                if(baseTime > workdayTime){
                    return workdayTime;
                }
            }
            timeStamp = baseTime;
        }

        // Get timestamp for 00:00:00
        java.util.Calendar day = getStartOfDayJavaCalendar(timeStamp);
        while (isTimeIncluded(day.getTime().getTime()) == false) {
            day.add(java.util.Calendar.DATE, 1);
        }

        return day.getTime().getTime();
    }
    
    /**
     * 添加一批用逗号分割的日期到节假日列表中，默认日期格式为yyyy-MM-dd
     * @param excludedDates
     */
    public void addExcludedDate(String excludedDates) {
        addExcludedDate(excludedDates, DEFAULT_DATE_FROMART);
    }

    /**
     *  添加一批用逗号分割的日期到节假日列表中
     * @param excludedDates
     * @param pattern
     */
    public void addExcludedDate(String excludedDates, String pattern) {
        if(!Strings.isBlank(excludedDates)){
            pattern = Strings.sBlank(pattern, DEFAULT_DATE_FROMART);
            String[] dates = Strings.splitIgnoreBlank(excludedDates, ",");
            for(String date : dates){
                addExcludedDate(Dates.string2Date(date, pattern));
            }
        }
    }
    
    /**
     *  添加一批日期到节假日列表中
     * @param excludedDates
     */
    public void addExcludedDate(Collection<? extends Date> excludedDates) {
        if(excludedDates != null && !excludedDates.isEmpty()){
            for(Date excludedDate : excludedDates){
                addExcludedDate(excludedDate);
            }
        }
    }

    /**
     * 添加一个日期到节假日列表中
     * @param excludedDate
     */
    public void addExcludedDate(Date excludedDate) {
        Date date = getStartOfDayJavaCalendar(excludedDate.getTime()).getTime();
        this.holidays.add(date);
    }

    /**
     * 从节假日列表中删除指定的日期
     * @param dateToRemove
     */
    public void removeExcludedDate(Date dateToRemove) {
        Date date = getStartOfDayJavaCalendar(dateToRemove.getTime()).getTime();
        holidays.remove(date);
    }

    /**
     * 获取当前设定的节假日列表
     * @return
     */
    public SortedSet<Date> getExcludedDates() {
        return Collections.unmodifiableSortedSet(holidays);
    }

    /**
     * 添加一批逗号分割的日期到工作日列表中，默认日期格式为yyyy-MM-dd
     * @param includedDates
     */
    public void addIncludedDate(String includedDates) {
        addIncludedDate(includedDates, DEFAULT_DATE_FROMART);
    }

    /**
     * 添加一批逗号分割的日期到工作日列表中
     * @param excludedDates
     * @param pattern
     */
    public void addIncludedDate(String excludedDates, String pattern) {
        if(!Strings.isBlank(excludedDates)){
            pattern = Strings.sBlank(pattern, DEFAULT_DATE_FROMART);
            String[] dates = Strings.splitIgnoreBlank(excludedDates, ",");
            for(String date : dates){
                addIncludedDate(Dates.string2Date(date, pattern));
            }
        }
    }
    
    /**
     * 添加一批日期到工作日列表中
     * @param includedDates
     */
    public void addIncludedDate(Collection<? extends Date> includedDates) {
        if(includedDates != null && !includedDates.isEmpty()){
            for(Date excludedDate : includedDates){
                addIncludedDate(excludedDate);
            }
        }
    }
    
    /**
     * 添加一个日期到工作日列表中
     * @param includedDate
     */
    public void addIncludedDate(Date includedDate) {
        Date date = getStartOfDayJavaCalendar(includedDate.getTime()).getTime();
        this.workdays.add(date);
    }

    /**
     * 从工作日列表中删除指定日期
     * @param dateToRemove
     */
    public void removeIncludedDate(Date dateToRemove) {
        Date date = getStartOfDayJavaCalendar(dateToRemove.getTime()).getTime();
        workdays.remove(date);
    }

    /**
     * 获取当前设定的工作日列表
     * @return
     */
    public SortedSet<Date> getIncludedDates() {
        return Collections.unmodifiableSortedSet(workdays);
    }
}
