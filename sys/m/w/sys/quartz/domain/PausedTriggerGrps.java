package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_PAUSED_TRIGGER_GRPS")
@PK({ "schedName", "triggerGroup" })
public class PausedTriggerGrps implements Serializable {
    private static final long serialVersionUID = 3166750172254503292L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


    public String getSchedName() {
        return schedName;
    }


    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }


    public String getTriggerGroup() {
        return triggerGroup;
    }


    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

}