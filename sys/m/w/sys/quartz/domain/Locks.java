package m.w.sys.quartz.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Table;


@Table("QRTZ_LOCKS")
@PK({ "schedName", "lockName" })
public class Locks implements Serializable {
    private static final long serialVersionUID = -6751924287852701261L;


    @Column("SCHED_NAME")
    @ColDefine(notNull = true, width = 120)
	private String schedName;


	@Column("LOCK_NAME")
	@ColDefine(notNull = true, width = 40)
	private String lockName;


    public String getSchedName() {
        return schedName;
    }


    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }


    public String getLockName() {
        return lockName;
    }


    public void setLockName(String lockName) {
        this.lockName = lockName;
    }


}