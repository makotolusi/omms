package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_SIMPLE_TRIGGERS")
@PK({ "schedName", "triggerName", "triggerGroup" })
public class SimpleTriggers implements Serializable {
    private static final long serialVersionUID = 3562873833698264286L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("TRIGGER_NAME")
	@ColDefine(notNull = true, width = 200)
	private String triggerName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


	@Column("REPEAT_COUNT")
	private Long repeatCount;


	@Column("REPEAT_INTERVAL")
	private Long repeatInterval;


	@Column("TIMES_TRIGGERED")
	private Long timesTriggered;


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


    public Long getRepeatCount() {
        return repeatCount;
    }


    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }


    public Long getRepeatInterval() {
        return repeatInterval;
    }


    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }


    public Long getTimesTriggered() {
        return timesTriggered;
    }


    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }
}