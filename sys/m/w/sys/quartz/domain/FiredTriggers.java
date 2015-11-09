package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_FIRED_TRIGGERS")
@PK({ "schedName", "entryId" })
public class FiredTriggers implements Serializable {
    private static final long serialVersionUID = 2035135292118366259L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("ENTRY_ID")
	@ColDefine(notNull = true, width = 95)
	private String entryId;


	@Column("TRIGGER_NAME")
	@ColDefine(notNull = true, width = 200)
	private String triggerName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


	@Column("INSTANCE_NAME")
	@ColDefine(notNull = true, width = 200)
	private String instanceName;


	@Column("FIRED_TIME")
	private Long firedTime;


	@Column("PRIORITY")
	private Integer priority;


	@Column("STATE")
	@ColDefine(notNull = true, width = 16)
	private String state;


	@Column("JOB_NAME")
	@ColDefine(width = 200)
	private String jobName;


	@Column("JOB_GROUP")
	@ColDefine(width = 200)
	private String jobGroup;


	@Column("IS_NONCONCURRENT")
	@ColDefine(width = 1)
	private Boolean isNonconcurrent;


	@Column("REQUESTS_RECOVERY")
	@ColDefine(width = 1)
	private Boolean requestsRecovery;


    public String getSchedName() {
        return schedName;
    }


    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }


    public String getEntryId() {
        return entryId;
    }


    public void setEntryId(String entryId) {
        this.entryId = entryId;
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


    public String getInstanceName() {
        return instanceName;
    }


    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }


    public Long getFiredTime() {
        return firedTime;
    }


    public void setFiredTime(Long firedTime) {
        this.firedTime = firedTime;
    }


    public Integer getPriority() {
        return priority;
    }


    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
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


    public Boolean getIsNonconcurrent() {
        return isNonconcurrent;
    }


    public void setIsNonconcurrent(Boolean isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }


    public Boolean getRequestsRecovery() {
        return requestsRecovery;
    }


    public void setRequestsRecovery(Boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }


}