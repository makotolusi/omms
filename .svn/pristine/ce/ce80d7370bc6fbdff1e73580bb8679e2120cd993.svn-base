package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_CRON_TRIGGERS")
@PK({ "schedName", "triggerName", "triggerGroup" })
public class CronTriggers implements Serializable {
    private static final long serialVersionUID = 7629235223832633277L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("TRIGGER_NAME")
	@ColDefine(notNull = true, width = 200)
	private String triggerName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


	@Column("CRON_EXPRESSION")
	@ColDefine(notNull = true, width = 200)
	private String cronExpression;


	@Column("TIME_ZONE_ID")
	private String timeZoneId;


    public String getSchedName() {
        return schedName;
    }


    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }


    public String getTriggerName() {
        return triggerName;
    }


    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }


    public String getTriggerGroup() {
        return triggerGroup;
    }


    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }


    public String getCronExpression() {
        return cronExpression;
    }


    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public String getTimeZoneId() {
        return timeZoneId;
    }


    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }


}