package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_CALENDARS")
@PK({ "schedName", "calendarName" })
public class Calendars implements Serializable {
    private static final long serialVersionUID = 2533997101970226993L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("CALENDAR_NAME")
	@ColDefine(notNull = true, width = 200)
	private String calendarName;

	
	@Column("CALENDAR")
	private java.sql.Blob calendar;

    public String getSchedName() {
        return schedName;
    }
    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getCalendarName() {
        return calendarName;
    }
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    public java.sql.Blob getCalendar() {
        return calendar;
    }
    public void setCalendar(java.sql.Blob calendar) {
        this.calendar = calendar;
    }
	
	
}