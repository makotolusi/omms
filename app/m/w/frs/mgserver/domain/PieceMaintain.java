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

@Table("tbl_piecemaintain")
@Comment("板块")
public class PieceMaintain extends WrapSupport implements Serializable,
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
	@Column("text")
	protected String text;
	// protected String typeText;

	/** 类型 */
	@Column("type")
	protected String type;

	/** 名称 */
	@Column("typeName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String typeName;

	/** 备注 */
	@Column("notes")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String notes;

	/** 图片地址 */
	@Column("imgUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String imgUrl;

	/** 图片地址 */
	@Column("countryImgUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String countryImgUrl;

	@Column("pieceCategory")
	protected String pieceCategory = "1";// 板块类别，1：商品板块 9：拯救板块

	List<PieceMaintain> plist;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public List<PieceMaintain> getPlist() {
		return plist;
	}

	public void setPlist(List<PieceMaintain> plist) {
		this.plist = plist;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCountryImgUrl() {
		return countryImgUrl;
	}

	public void setCountryImgUrl(String countryImgUrl) {
		this.countryImgUrl = countryImgUrl;
	}

	public String getPieceCategory() {
		return pieceCategory;
	}

	public void setPieceCategory(String pieceCategory) {
		this.pieceCategory = pieceCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
