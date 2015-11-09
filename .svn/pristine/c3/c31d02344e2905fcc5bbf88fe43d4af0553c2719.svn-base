package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_TRIGGERS")
@PK({ "schedName", "triggerName", "triggerGroup" })
public class Triggers implements Serializable {
    private static final long serialVersionUID = -7801010722170042785L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("TRIGGER_NAME")
	@ColDefine(notNull = true, width = 200)
	private String triggerName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


	@Column("JOB_NAME")
	@ColDefine(notNull = true, width = 200)
	private String jobName;


	@Column("JOB_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String jobGroup;

	
	@Column("DESCRIPTION")
	@ColDefine(width = 250)
	private String description;


	@Column("NEXT_FIRE_TIME")
	private Long nextFireTime;


	@Column("PREV_FIRE_TIME")
	private Long prevFireTime;


	@Column("PRIORITY")
	private Integer priority;


	@Column("TRIGGER_STATE")
	@ColDefine(notNull = true, width = 16)
	private String triggerState;


	@Column("TRIGGER_TYPE")
	@ColDefine(notNull = true, width = 8)
	private String triggerType;


	@Column("START_TIME")
	private Long startTime;


	@Column("END_TIME")
	private Long endTime;


	@Column("CALENDAR_NAME")
	@ColDefine(width = 200)
	private String calendarName;


	@Column("MISFIRE_INSTR")
	private Integer misfireInstr;


	@Column("JOB_DATA")
	private java.sql.Blob jobData;


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


    public String getJobName() {
        return jobName;
    }


    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public String getJobGroup() {
        return jobGroup;
    }


    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Long getNextFireTime() {
        return nextFireTime;
    }


    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }


    public Long getPrevFireTime() {
        return prevFireTime;
    }


    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }


    public Integer getPriority() {
        return priority;
    }


    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    public String getTriggerState() {
        return triggerState;
    }


    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }


    public String getTriggerType() {
        return triggerType;
    }


    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }


    public Long getStartTime() {
        return startTime;
    }


    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }


    public Long getEndTime() {
        return endTime;
    }


    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


    public String getCalendarName() {
        return calendarName;
    }


    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }


    public Integer getMisfireInstr() {
        return misfireInstr;
    }


    public void setMisfireInstr(Integer misfireInstr) {
        this.misfireInstr = misfireInstr;
    }


    public java.sql.Blob getJobData() {
        return jobData;
    }


    public void setJobData(java.sql.Blob jobData) {
        this.jobData = jobData;
    }

}