package m.w.sys.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import m.w.core.service.WxIdService;
import m.w.sys.domain.WorkCalendar;

@IocBean(fields="dao")
public class WorkCalendarService extends WxIdService<WorkCalendar>{
    
    /**
     * 查询指定日期范围内的所有节假日
     * @param from
     * @param to
     * @return
     */
    public List<WorkCalendar> getWorkCalendar(Date from, Date to){
        return query(Cnd.where("onDate", ">=", from).and("onDate", "<=", to), null);
    }

    /**
     * 获取本月的所有节假日
     * @return
     */
    public List<WorkCalendar> getThisModthWorkCalendar() {
        DateTime date = new DateTime();
        int month = date.getDayOfMonth();
        int year = date.getDayOfYear();
        DateTime from = new DateTime(year, month, 1, 0, 0);
        DateTime to = from.plusMonths(1).minusDays(1);
        return getWorkCalendar(from.toDate(), to.toDate());
    }
}
