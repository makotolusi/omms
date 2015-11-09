package m.w.tags;

import java.util.Date;

import m.w.core.util.Dates;

public class DateTag {
    /**
     * 转换为 yyyy-MM-dd 格式的字符串日期
     * 
     * @param obj
     * @return
     */
    public static String date(Date date) {
        return Dates.date2String(date);
    }

    /**
     * 转换为 yyyy-MM-dd hh:mm:ss 格式的字符串日期时间
     * 
     * @param obj
     * @return
     */
    public static String datetime(Date datetime) {
        return Dates.datetime2String(datetime);
    }

    /**
     * 当前的日期时间
     * 
     * @return
     */
    public static String now() {
        return Dates.datetime2String(Dates.now());
    }

    /**
     * 今天
     * 
     * @return
     */
    public static String today() {
        return Dates.date2String(Dates.today());
    }
}
