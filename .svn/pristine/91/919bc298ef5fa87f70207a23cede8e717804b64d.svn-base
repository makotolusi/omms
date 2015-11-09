package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_SCHEDULER_STATE")
@PK({ "schedName", "instanceName" })
public class SchedulerState implements Serializable {
    private static final long serialVersionUID = 349452828118735874L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("INSTANCE_NAME")
	@ColDefine(notNull = true, width = 200)
	private String instanceName;
	/**
	 * 
	 */
	@Column("LAST_CHECKIN_TIME")
	private Long lastCheckinTime;
	/**
	 * 
	 */
	@Column("CHECKIN_INTERVAL")
	private Long checkinInterval;
    public String getSchedName() {
        return schedName;
    }
    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getInstanceName() {
        return instanceName;
    }
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }
    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }
    public Long getCheckinInterval() {
        return checkinInterval;
    }
    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

}