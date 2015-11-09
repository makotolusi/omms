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

@Table("tbl_activity")
@Comment("活动表")
public class Activity extends WrapSupport implements Serializable,
		IdEntity, Constable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470263626963122171L;


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

	/** 活动Name */
	@Column("activityName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String activityName;	
	
	/** 专场Name */
	@Column("specialName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String specialName;	
	
	/** 描述 */
	@Column("description")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String description;
	
	/** 抢购开始时间 */
	@Column("rushBeginTime")
	@ColDefine(notNull = false, type = ColType.DATETIME)
	protected Date rushBeginTime;	

	/** 抢购结束时间 */
	@Column("rushEndTime")
	@ColDefine(notNull = false, type = ColType.DATETIME)
	protected Date rushEndTime;	

	/** 抢购状态 */
	@Column("rushStatus")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String rushStatus=Status.NORMAL.value+"";
	
	/** 图片地址 */
	@Column("imgUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String imgUrl;	
	
	/** 图片e and token地址 */
	protected String imgUrlComplete;	
	
	/** 创建人*/
	@Column("userName")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	protected String userName;	
	
	/** 录入时间 */
	@Column("entertime")
	@ColDefine(notNull = false, type = ColType.DATETIME)
	protected Date entertime;	

	List<ActivityProductData> plist;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRushBeginTime() {
		return rushBeginTime;
	}

	public void setRushBeginTime(Date rushBeginTime) {
		this.rushBeginTime = rushBeginTime;
	}

	public Date getRushEndTime() {
		return rushEndTime;
	}

	public void setRushEndTime(Date rushEndTime) {
		this.rushEndTime = rushEndTime;
	}

	public String getRushStatus() {
		return rushStatus;
	}

	public void setRushStatus(String rushStatus) {
		this.rushStatus = rushStatus;
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

	public List<ActivityProductData> getPlist() {
		return plist;
	}

	public void setPlist(List<ActivityProductData> plist) {
		this.plist = plist;
	}
	
	
	public String getImgUrlComplete() {
		return imgUrlComplete;
	}

	public void setImgUrlComplete(String imgUrlComplete) {
		this.imgUrlComplete = imgUrlComplete;
	}


	public enum Status {
		STOP(0), NORMAL(1);

		private final Integer value;

		Status(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
}