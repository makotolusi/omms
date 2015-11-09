package m.w.frs.cms.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import m.w.core.dao.IdEntity;
import m.w.sys.domain.Constable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/***
 * 数据字典
 * 
 * @author ZCC
 * 
 */
@Table("Dict")
public class Dict  implements Serializable, IdEntity ,Constable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3679507081607252110L;
	@Id
    private Long id; // Id
    @Column
    private String type; // 类型
    @Column
    private String name; // 名称
    @Column
    private String value; // 值
    @Column
    @Default("0")
    private Integer sortIndex; // 排序
    @Column
    private String description; // 说明

    /***
     * 取得Id
     * @return
     */
    public Long getId() {
        return id;
    }
    /***
     * 设置Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     * 取得类型
     * @return
     */
    public String getType() {
        return type;
    }
    /***
     * 设置类型
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /***
     * 取得名称
     * @return
     */
    public String getName() {
        return name;
    }
    /***
     * 设置名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * 取得值
     * @return
     */
    public String getValue() {
        return value;
    }
    /***
     * 设置值
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /***
     * 取得排序
     * @return
     */
    public Integer getSortIndex() {
        return sortIndex;
    }
    /***
     * 设置排序
     * @param sortIndex
     */
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    /***
     * 取得说明
     * @return
     */
    public String getDescription() {
        return description;
    }
    /***
     * 设置说明
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		return fm;
	}
	@Override
	public void wrap() {
		// TODO Auto-generated method stub
		
	}
}