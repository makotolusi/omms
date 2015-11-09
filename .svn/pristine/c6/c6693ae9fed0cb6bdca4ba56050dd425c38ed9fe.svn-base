package m.w.frs.mgserver.domain;

import java.io.Serializable;
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

@Table("tbl_wish")
@Comment("想要表")
public class Wish extends WrapSupport implements Serializable, IdEntity,
		Constable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470263626963122171L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		return fm;
	}

	@Id
	@Column
	private Long id;

	/** 商品code */
	@Column("productCode")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	private String productCode;
	
	@Column
	private String flg;// 1：想要，2：关注 3：拯救
	
	@Column
	private String pieceCategory;// 板块类别，1：商品板块 9：拯救板块
	
	@Column
	private Long userId;

	/** 想要描述 */
	@Column("description")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	private String description;

	/** 回复 */
	@Column("returnDescription")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	private String returnDescription;

	/** 图片地址 */
	@Column("imgUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	private String imgUrl;

	/** 创建人 */
	@Column("userName")
	private String userName;

	/** 录入时间 */
	@Column("entertime")
	@ColDefine(notNull = false, type = ColType.DATETIME)
	private Date entertime;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReturnDescription() {
		return returnDescription;
	}

	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getPieceCategory() {
		return pieceCategory;
	}

	public void setPieceCategory(String pieceCategory) {
		this.pieceCategory = pieceCategory;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}
	
	

}