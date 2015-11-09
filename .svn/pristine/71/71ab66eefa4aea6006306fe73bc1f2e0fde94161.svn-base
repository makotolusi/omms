package m.w.sys.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

@Table("Sys_DictItem")
public class DictItem extends WrapSupport implements Serializable, IdEntity, Comparable<DictItem>, Constable {
	private static final long serialVersionUID = 3870667342171505643L;
	
	/** 使用 */
	public static final String STATE_1 = "1";
	/** 禁用 */
	public static final String STATE_0 = "0";
	
    @Override
    public Map<String, String> getConstFieldMap() {
        Map<String, String> fm = new HashMap<String, String>();
        fm.put("state",     "stateText");
        return fm;
    }
    
	@Id
	private Long id;

    /**
     * 字典类型id
     */
    @Column
    private Long typeId;
    
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 200)
	private String text;
	
	@Column
	@ColDefine(notNull = true, type = ColType.CHAR, width = 5)
	private String state;
	private String stateText;

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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(DictItem o) {
		if(typeId != o.typeId){
			return (int)(typeId - typeId);
		}else{
			return orders - o.orders;
		}
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

}
