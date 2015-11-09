package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.util.Date;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_indexPage")
@Comment("首页")
public class IndexPage extends WrapSupport implements Serializable, IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339615509043624990L;

	@Id
	@Column
	private Long id;

	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 100)
	private String systemName;

	/** clentName */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 100)
	private String clentName;

	/** pagename */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 100)
	private String pageName;

	/** 头部 */
	@Column("headerContext")
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 800)
	private String headerContext;

	/** 导航内容 */
	@Column("navContext")
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 800)
	private String navContext;

	/** 时间 */
	@Column
	private Date entertime;
	
	/** 登录人name */
	@Column
	private String username;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public String getClentName() {
		return clentName;
	}

	public void setClentName(String clentName) {
		this.clentName = clentName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getHeaderContext() {
		return headerContext;
	}

	public void setHeaderContext(String headerContext) {
		this.headerContext = headerContext;
	}

	public String getNavContext() {
		return navContext;
	}

	public void setNavContext(String navContext) {
		this.navContext = navContext;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
