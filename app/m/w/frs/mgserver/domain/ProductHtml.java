package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

@Table("tbl_product_html_url")
@Comment("商品html对照表")
public class ProductHtml extends WrapSupport implements Serializable, IdEntity,
		Constable {

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

	/** 商品code */
	@Column("productCode")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String productCode;

	/** url */
	@Column("url")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String url;

	/** notes */
	@Column("notes")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	private String notes;

	/** 0：单纯网业（default），1：包含视频 */
	@Column("type")
	protected int type = 0;

	/** 登录人name */
	@Column
	private String username;

	/** 时间 */
	@Column
	private Date entertime;

	List<ProductHtml> plist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public List<ProductHtml> getPlist() {
		return plist;
	}

	public void setPlist(List<ProductHtml> plist) {
		this.plist = plist;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
