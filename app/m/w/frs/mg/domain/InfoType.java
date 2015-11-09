package m.w.frs.mg.domain;

import java.io.Serializable;

import m.w.core.dao.IdEntity;
import m.w.core.dto.tree.TreeItem;
import m.w.core.dto.tree.Treeable;
import m.w.frs.mg.util.InfoTreeItem;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("info_type")
@Comment("系统信息分类表")
public class InfoType extends WrapSupport implements Serializable, IdEntity, Treeable, Comparable<InfoType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9082719567471557732L;

	@Override
	public TreeItem toTreeItem() {
		InfoTreeItem t = new InfoTreeItem("type-"+id, text, 0, "info_type", orders);
    	t.setTypeId(id);
    	t.setTypeName(text);
		return t;
	}
	
	@Override
	public int compareTo(InfoType o) {
		return orders != null ? orders.compareTo(o.orders) : 0;
	}
	
    /**
     * 主键
     */
    @Id
    @Column
    private Long id;
    
    /**
     * 名称
     */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	@Comment("分类名称")
	private String text;

	/**
	 * 排序号
	 */
	@Column
	@Comment("排序号")
	private Integer orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

}
