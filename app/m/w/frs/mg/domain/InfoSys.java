package m.w.frs.mg.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import m.w.core.dao.IdEntity;
import m.w.core.dto.tree.TreeItem;
import m.w.core.dto.tree.Treeable;
import m.w.frs.mg.util.InfoTreeItem;
import m.w.frs.mg.util.Infos;
import m.w.sys.domain.Constable;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("info_sys")
@Comment("信息系统表")
public class InfoSys extends WrapSupport implements Serializable, IdEntity, Comparable<InfoSys>, Constable, Treeable, InfoTypeable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4426700833312351348L;

	/** 使用 */
	public static final String STATE_1 = "1";
	/** 禁用 */
	public static final String STATE_0 = "0";
	
	@Override
	public TreeItem toTreeItem() {
		InfoTreeItem t = new InfoTreeItem(id, text, "type-"+typeId, orders, true);
    	t.setTypeId(typeId);
    	t.setTypeName(Infos.get(typeId).getText());
    	t.setContact(contact);
    	t.setContactDept(contactDept);
    	t.setTel(tel);
		return t;
	}
	
	@Override
	public Map<String, String> getInfoTypeFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
        fm.put("typeId",     "typeName");
        return fm;
	}
	
    @Override
    public Map<String, String> getConstFieldMap() {
        Map<String, String> fm = new HashMap<String, String>();
        fm.put("state",     "stateText");
        return fm;
    }
    
	@Override
	public int compareTo(InfoSys o) {
		return typeId != null ? typeId.compareTo(o.typeId) : 0;
	}
	
    /**
     * 主键
     */
    @Id
    @Column
    private Long id;
    
    /**
     * 类型id
     */
    @Column
    private Long typeId;
    private String typeName;
    
    /**
     * 名称
     */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	@Comment("名称")
	private String text;
	
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 10)
	private String state;
	private String stateText;
	
	/**
	 * 排序号
	 */
	@Column
	@Comment("排序号")
	private Integer orders;
	
	/**
     * 负责部门
     */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 200)
	@Comment("负责部门")
	private String contactDept;
	
	/**
     * 负责人
     */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	@Comment("负责人")
	private String contact;
	
	/**
     * 联系电话
     */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	@Comment("联系电话")
	private String tel;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getContactDept() {
		return contactDept;
	}

	public void setContactDept(String contactDept) {
		this.contactDept = contactDept;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
