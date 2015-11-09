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

@Table("splash_screen")
@Comment("闪屏页表")
public class SplashScreen extends WrapSupport implements Serializable,
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

	/** picUrl1 */
	@Column("picUrl1")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String picUrl1;

	/** picUrl2 */
	@Column("picUrl2")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	protected String picUrl2;

	/**  */
	@Column("fragmentIndex")
	protected int fragmentIndex = 0;

	/**  */
	@Column("duration")
	protected int duration = 5000;
	
	/**  */
	@Column("sort")
	protected int sort = 0;
	
	/** ios android flg */
	@Column
	private String flg;

	/** 登录人name */
	@Column
	private String username;

	/** 时间 */
	@Column
	private Date entertime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicUrl1() {
		return picUrl1;
	}

	public void setPicUrl1(String picUrl1) {
		this.picUrl1 = picUrl1;
	}

	public String getPicUrl2() {
		return picUrl2;
	}

	public void setPicUrl2(String picUrl2) {
		this.picUrl2 = picUrl2;
	}

	public int getFragmentIndex() {
		return fragmentIndex;
	}

	public void setFragmentIndex(int fragmentIndex) {
		this.fragmentIndex = fragmentIndex;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}
	

}
