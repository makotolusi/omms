package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.sql.Date;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;


@Table("tbl_mgTribeInfo")
@Comment("部落")
public class MgTribeInfo extends WrapSupport implements Serializable, IdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3876561310388506873L;

	@Id
	@Column
	private Long id;

	/** 部落名称 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String name;

	/** 部落首领 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String tribeChief;

	/** 部落公告 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 1000)
	private String notice;

	/** 部落副首领 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String tribeDeputyChief;

	/** 时间 */
	@Column
	private Date startdate;

	/** 是否注销 */
	@Column
	private String flag;
	
	/** 时间 */
	@Column
	private Date entertime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTribeChief() {
		return tribeChief;
	}

	public void setTribeChief(String tribeChief) {
		this.tribeChief = tribeChief;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getTribeDeputyChief() {
		return tribeDeputyChief;
	}

	public void setTribeDeputyChief(String tribeDeputyChief) {
		this.tribeDeputyChief = tribeDeputyChief;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}
	
	
	
}
