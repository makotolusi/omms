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

@Table("tbl_comments")
@Comment("评论表表")
public class CommentsTbl extends WrapSupport implements Serializable, IdEntity,
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

	@Column
	private Long pid;

	/** 商品code */
	@Column("productCode")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	private String productCode;

	@Column
	private String delflg = "0";//

	@Column
	private String userHeadPortraitUrl;// 用户头像url

	@Column
	private Long userId;

	/** 创建人 */
	@Column("userName")
	private String userName;

	/** 评论 */
	@Column("comments")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1500)
	private String comments;

	/** 点赞数 */
	@Column("starCount")
	private int starCount;

	/** 图片地址 */
	@Column("imgUrl")
	@ColDefine(notNull = false, type = ColType.VARCHAR, width = 1000)
	private String imgUrl;

	/** 录入时间 */
	@Column("entertime")
	@ColDefine(notNull = false, type = ColType.DATETIME)
	private Date entertime;

	@Column
	private String childrenFlg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDelflg() {
		return delflg;
	}

	public void setDelflg(String delflg) {
		this.delflg = delflg;
	}

	public String getUserHeadPortraitUrl() {
		return userHeadPortraitUrl;
	}

	public void setUserHeadPortraitUrl(String userHeadPortraitUrl) {
		this.userHeadPortraitUrl = userHeadPortraitUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}

	public String getChildrenFlg() {
		return childrenFlg;
	}

	public void setChildrenFlg(String childrenFlg) {
		this.childrenFlg = childrenFlg;
	}

}