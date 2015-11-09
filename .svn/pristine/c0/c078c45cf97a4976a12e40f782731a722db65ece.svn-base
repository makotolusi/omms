package m.w.sys.module;

import java.util.Date;

import m.w.BasicModule;
import m.w.core.dto.Result;
import m.w.sys.domain.WorkCalendar;
import m.w.sys.service.WorkCalendarService;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@At("/sys/workcalendar")
@IocBean
public class WorkCalendarModule extends BasicModule{
    @Inject
    private WorkCalendarService workCalendarService;
    
    @At
    @Ok("jsp:/sys/workcalendar/index")
    public void index() {

    }
    
    @At
    public Object holiday(Long start, Long end){
       return workCalendarService.getWorkCalendar(new Date(start*1000), new Date(end*1000)); 
    }
    
    @At
    public Result save(@Param("..")WorkCalendar workCalendar){
        return workCalendarService.save(workCalendar, null, null);
    }
    
    @At("/delete/?")
    @DELETE
    public Result delete(Long id) {
        return workCalendarService.delete(id, null, null);
    }
}
