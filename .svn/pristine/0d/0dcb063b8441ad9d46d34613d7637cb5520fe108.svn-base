package m.w.sys.domain;

import java.io.Serializable;

import m.w.core.dao.IdEntity;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("Sys_DictType")
public class DictType implements Serializable, IdEntity, Comparable<DictType> {
	private static final long serialVersionUID = -6949887480348123021L;

	@Id
	private Long id;

	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String code;
	
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String text;

	@Column
	private Integer orders;
	
	@Column
	@ColDefine(type = ColType.VARCHAR, width = 200)
	private String notes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public int compareTo(DictType t) {
		return orders - t.orders;
	}
}
