package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_reward")
@Comment("奖励")
public class Reward extends WrapSupport implements Serializable, IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 169680150134582562L;

	@Id
	@Column
	private Long id;

	@Column
	private String weixinId;
	/** 金额 */
	@Column
	private BigDecimal coupon;

	/** 时间 */
	@Column
	private Date effectiveData;

	/** 时间 */
	@Column
	private Date entertime;

	/** 电话号码 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String telNumber;

	@Column
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 5000)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public Date getEffectiveData() {
		return effectiveData;
	}

	public void setEffectiveData(Date effectiveData) {
		this.effectiveData = effectiveData;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

}
