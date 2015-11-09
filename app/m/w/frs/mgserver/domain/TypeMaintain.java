package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import m.w.core.dao.IdEntity;
import m.w.sys.domain.Constable;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_typemaintain")
@Comment("规则维护")
public class TypeMaintain extends WrapSupport implements Serializable,
		IdEntity, Constable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541257497772248914L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		// fm.put("type", "typeText");
		// fm.put("status", "statusText");

		return fm;
	}

	@Id
	@Column
	private Long id;

	/** 登录人name */
	@Column
	private String username;

	/** 时间 */
	@Column
	private Date entertime;

	/** 类型 */
	@Column("type")
	protected String type;
	// protected String typeText;

	/** 名称 */
	@Column("typeName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String typeName;

	/** 备注 */
	@Column("notes")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String notes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
