package m.w.sys.quartz.util;

import m.w.App;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.WeeklyCalendar;

public class Schedulers {
    private static Log log = Logs.get();
    public static Scheduler init(){
        Scheduler scheduler = null;
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            WxCalendar workDayCalendar = Calendars.getWorkDayCalendar(new WeeklyCalendar(), App.getHolidays(), App.getWorkdays());
            scheduler.addCalendar("workDayCalendar", workDayCalendar, true, true);//工作日
            scheduler.addCalendar("workTimeCalendar", Calendars.getWorkTimeCalendar(workDayCalendar, App.getAmBegin(), App.getAmEnd(), App.getPmBegin(), App.getPmEnd()), true, true);//工作日的工作时间
            scheduler.start();
        }
        catch (SchedulerException e) {
            log.error(e);
        }
        return scheduler;
    }
    
    public static void destroy(Scheduler scheduler){
        if(scheduler != null){
            try {
                scheduler.shutdown();
            }
            catch (Exception e) {
                log.error(e);
            }
        }
    }
}
