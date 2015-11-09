package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Table("tbl_activity_product")
@Comment("活动商品对应表")
public class ActivityProductData extends WrapSupport implements Serializable,
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

	/** 活动Id */
	@Column("activityId")
	protected Long activityId;

	/** 商品code */
	@Column("productCode")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String productCode;

	/** 商品Name */
	@Column("productName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String productName;

	/** 抢购数量 */
	@Column("rushQuantity")
	protected int rushQuantity;

	@Column
	private BigDecimal rushPrice;// 价格

	@Column
	private BigDecimal bargainPrice;// 优惠额度

	@Column
	private int sortNum;// 排序

	@Column
	private String status;

	/** 图片地址 */
	@Column("imageUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String imageUrl;

	/** 商品图片1地址 */
	@Column("picUrl1")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String picUrl1;

	/** 登录人name */
	@Column
	private String username;

	/** 时间 */
	@Column
	private Date entertime;

	List<ActivityProductData> plist;

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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getRushQuantity() {
		return rushQuantity;
	}

	public void setRushQuantity(int rushQuantity) {
		this.rushQuantity = rushQuantity;
	}

	public BigDecimal getRushPrice() {
		return rushPrice;
	}

	public void setRushPrice(BigDecimal rushPrice) {
		this.rushPrice = rushPrice;
	}

	public BigDecimal getBargainPrice() {
		return bargainPrice;
	}

	public void setBargainPrice(BigDecimal bargainPrice) {
		this.bargainPrice = bargainPrice;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ActivityProductData> getPlist() {
		return plist;
	}

	public void setPlist(List<ActivityProductData> plist) {
		this.plist = plist;
	}

	public String getPicUrl1() {
		return picUrl1;
	}

	public void setPicUrl1(String picUrl1) {
		this.picUrl1 = picUrl1;
	}

}
