package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_JOB_DETAILS")
@PK({ "schedName", "jobName", "jobGroup" })
public class JobDetails implements Serializable {
    private static final long serialVersionUID = -8990159891367224335L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("JOB_NAME")
	@ColDefine(notNull = true, width = 200)
	private String jobName;


	@Column("JOB_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String jobGroup;


	@Column("DESCRIPTION")
	@ColDefine(width = 250)
	private String description;


	@Column("JOB_CLASS_NAME")
	@ColDefine(width = 250)
	private String jobClassName;


	@Column("IS_DURABLE")
	@ColDefine(width = 1)
	private Boolean isDurable;


	@Column("IS_NONCONCURRENT")
	@ColDefine(width = 1)
	private Boolean isNonconcurrent;


	@Column("IS_UPDATE_DATA")
	@ColDefine(width = 1)
	private Boolean isUpdateData;


	@Column("REQUESTS_RECOVERY")
	@ColDefine(width = 1)
	private Boolean requestsRecovery;


	@Column("JOB_DATA")
	private java.sql.Blob jobData;


    public String getSchedName() {
        return schedName;
    }


    public void setSchedName(String schedName) {
        this.schedName = schedName;
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


    public String getJobClassName() {
        return jobClassName;
    }


    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }


    public Boolean getIsDurable() {
        return isDurable;
    }


    public void setIsDurable(Boolean isDurable) {
        this.isDurable = isDurable;
    }


    public Boolean getIsNonconcurrent() {
        return isNonconcurrent;
    }


    public void setIsNonconcurrent(Boolean isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }


    public Boolean getIsUpdateData() {
        return isUpdateData;
    }


    public void setIsUpdateData(Boolean isUpdateData) {
        this.isUpdateData = isUpdateData;
    }


    public Boolean getRequestsRecovery() {
        return requestsRecovery;
    }


    public void setRequestsRecovery(Boolean requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }


    public java.sql.Blob getJobData() {
        return jobData;
    }


    public void setJobData(java.sql.Blob jobData) {
        this.jobData = jobData;
    }


}