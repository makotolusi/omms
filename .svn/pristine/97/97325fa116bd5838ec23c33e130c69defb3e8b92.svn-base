package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;

@Table("QRTZ_BLOB_TRIGGERS")
@PK({ "schedName", "triggerName", "triggerGroup"})
public class BlobTriggers implements Serializable {
    private static final long serialVersionUID = -7087073059241162532L;


    @Column("SCHED_NAME")
	@ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("TRIGGER_NAME")
	@ColDefine(notNull = true, width = 200)
	private String triggerName;


	@Column("TRIGGER_GROUP")
	@ColDefine(notNull = true, width = 200)
	private String triggerGroup;


	@Column("BLOB_DATA")
	private java.sql.Blob blobData;

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
    public java.sql.Blob getBlobData() {
        return blobData;
    }
    public void setBlobData(java.sql.Blob blobData) {
        this.blobData = blobData;
    }


}