package m.w.frs.cms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import m.w.App;
import m.w.core.dao.IdEntity;
import m.w.sys.domain.Constable;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

/***
 * 文章
 * 
 * @author ZCC
 * 
 */
@Table("cms_article")
public class Article  implements Serializable, IdEntity ,Constable {
	public static int STATE_UNAUDITED = 0;
	public static int STATE_AUDITED = 1;
    /**
	 * 
	 */
	private static final long serialVersionUID = -404103337319009980L;
	@Id
    private Long id; // Id
    @Column
    private String title; // 标题
    @Column
    private String type; // 分类
    @Column
    private String context; // 内容
    @Column
    private String name; // 名称
    @Column
    private String addr; // 发现地址
    @Column
    private String description; // 描述
    @Column
    private String experience; // 心得
    @Column
    private String currency; // 货币
    @Column
    private BigDecimal price; // 价格
    @Column
    private String piece; // 板块
    @Column
    private String picUrl1; // 图片1
    @Column
    private String picUrl2; // 图片2
    @Column
    private String picUrl3; // 图片3
    @Column
    private String picUrl4; // 图片4
    @Column
    private String picUrl5; // 图片5
    @Column
    private String picUrl6; // 图片6
    @Column
    private String picUrl7; // 图片7
    @Column
    private String picUrl8; // 图片8
    @Column
    private String picUrl9; // 图片9
    @Column
    private String picUrl10; // 图片10
    @Column
    private Long authorId; // 作者ID
    @Column
    private Date createTime; // 发表时间
    @Column
    private Date lastUpdateTime; // 最后更新时间
    @Column
    private Long lastUpdateUserId; // 最后更新用户ID
    @Column
    private Integer state; // 状态
    @Column
    private Integer pushTimes; // 推送次数
    @Column
    private Integer readTimes; // 阅读次数
    @Column
    private Integer replyTimes; // 回复次数
    @Column
    private Integer collectTimes; // 收藏次数
    @Column
    private String authorName; // 作者名
    @Column
    private String summary; // 摘要
    @Column
    private String imgUrl; // 封面地址
    @Column
    private String imgThumbUrl; // 封面缩略图地址
    
    @Readonly
    private String appName; // 应用名
    @Readonly
    private String appSign; // 应用签名
    @Readonly
    private String issueDate; // 发表日期
    @Readonly
    private String typeName; // 分类名称
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
     * 取得标题
     * @return
     */
    public String getTitle() {
        return title;
    }
    /***
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     * 取得分类
     * @return
     */
    public String getType() {
        return type;
    }
    /***
     * 设置分类
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /***
     * 取得内容
     * @return
     */
    public String getContext() {
        return context;
    }
    /***
     * 设置内容
     * @param context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the piece
	 */
	public String getPiece() {
		return piece;
	}
	/**
	 * @param piece the piece to set
	 */
	public void setPiece(String piece) {
		this.piece = piece;
	}
	/**
	 * @return the picUrl1
	 */
	public String getPicUrl1() {
		return picUrl1;
	}
	/**
	 * @param picUrl1 the picUrl1 to set
	 */
	public void setPicUrl1(String picUrl1) {
		this.picUrl1 = picUrl1;
	}
	/**
	 * @return the picUrl2
	 */
	public String getPicUrl2() {
		return picUrl2;
	}
	/**
	 * @param picUrl2 the picUrl2 to set
	 */
	public void setPicUrl2(String picUrl2) {
		this.picUrl2 = picUrl2;
	}
	/**
	 * @return the picUrl3
	 */
	public String getPicUrl3() {
		return picUrl3;
	}
	/**
	 * @param picUrl3 the picUrl3 to set
	 */
	public void setPicUrl3(String picUrl3) {
		this.picUrl3 = picUrl3;
	}
	/**
	 * @return the picUrl4
	 */
	public String getPicUrl4() {
		return picUrl4;
	}
	/**
	 * @param picUrl4 the picUrl4 to set
	 */
	public void setPicUrl4(String picUrl4) {
		this.picUrl4 = picUrl4;
	}
	/**
	 * @return the picUrl5
	 */
	public String getPicUrl5() {
		return picUrl5;
	}
	/**
	 * @param picUrl5 the picUrl5 to set
	 */
	public void setPicUrl5(String picUrl5) {
		this.picUrl5 = picUrl5;
	}
	/**
	 * @return the picUrl6
	 */
	public String getPicUrl6() {
		return picUrl6;
	}
	/**
	 * @param picUrl6 the picUrl6 to set
	 */
	public void setPicUrl6(String picUrl6) {
		this.picUrl6 = picUrl6;
	}
	/**
	 * @return the picUrl7
	 */
	public String getPicUrl7() {
		return picUrl7;
	}
	/**
	 * @param picUrl7 the picUrl7 to set
	 */
	public void setPicUrl7(String picUrl7) {
		this.picUrl7 = picUrl7;
	}
	/**
	 * @return the picUrl8
	 */
	public String getPicUrl8() {
		return picUrl8;
	}
	/**
	 * @param picUrl8 the picUrl8 to set
	 */
	public void setPicUrl8(String picUrl8) {
		this.picUrl8 = picUrl8;
	}
	/**
	 * @return the picUrl9
	 */
	public String getPicUrl9() {
		return picUrl9;
	}
	/**
	 * @param picUrl9 the picUrl9 to set
	 */
	public void setPicUrl9(String picUrl9) {
		this.picUrl9 = picUrl9;
	}
	/**
	 * @return the picUrl10
	 */
	public String getPicUrl10() {
		return picUrl10;
	}
	/**
	 * @param picUrl10 the picUrl10 to set
	 */
	public void setPicUrl10(String picUrl10) {
		this.picUrl10 = picUrl10;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/***
     * 取得作者ID
     * @return
     */
    public Long getAuthorId() {
        return authorId;
    }
    /***
     * 设置作者ID
     * @param authorId
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /***
     * 取得发表时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }
    /***
     * 设置发表时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /***
     * 取得最后更新时间
     * @return
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }
    /***
     * 设置最后更新时间
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /***
     * 取得最后更新用户ID
     * @return
     */
    public Long getLastUpdateUserId() {
        return lastUpdateUserId;
    }
    /***
     * 设置最后更新用户ID
     * @param lastUpdateUserId
     */
    public void setLastUpdateUserId(Long lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    /***
     * 取得状态
     * @return
     */
    public Integer getState() {
        return state;
    }
    /***
     * 设置状态
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /***
     * 取得推送次数
     * @return
     */
    public Integer getPushTimes() {
        return pushTimes;
    }
    /***
     * 设置推送次数
     * @param pushTimes
     */
    public void setPushTimes(Integer pushTimes) {
        this.pushTimes = pushTimes;
    }

    /***
     * 取得阅读次数
     * @return
     */
    public Integer getReadTimes() {
        return readTimes;
    }
    /***
     * 设置阅读次数
     * @param readTimes
     */
    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    /***
     * 取得回复次数
     * @return
     */
    public Integer getReplyTimes() {
        return replyTimes;
    }
    /***
     * 设置回复次数
     * @param replyTimes
     */
    public void setReplyTimes(Integer replyTimes) {
        this.replyTimes = replyTimes;
    }

    /***
     * 取得收藏次数
     * @return
     */
    public Integer getCollectTimes() {
        return collectTimes;
    }
    /***
     * 设置收藏次数
     * @param collectTimes
     */
    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
    }

    /***
     * 取得作者名
     * @return
     */
	public String getAuthorName() {
		return authorName;
	}
    /***
     * 设置作者名
     * @param authorName
     */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
    /***
     * 取得应用名
     * @return
     */
	public String getAppName() {
		return App.getAppName();
	}
    /***
     * 设置应用名
     * @param appName
     */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
    /***
     * 取得应用签名
     * @return
     */
	public String getAppSign() {
		return appSign;
	}
    /***
     * 设置应用签名
     * @param appSign
     */
	public void setAppSign(String appSign) {
		this.appSign = appSign;
	}
	
    /***
     * 取得发表日期
     * @return
     */
	public String getIssueDate() {
		if (StringUtils.isEmpty(issueDate) && createTime!=null)
		{
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			issueDate =  formatDate.format(createTime);
		} 
		return issueDate;
	}
	
    /***
     * 取得摘要
     * @return
     */
	public String getDescription() {
		return description;
	}
    /***
     * 设置摘要
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}
	
    /***
     * 取得封面图片地址
     * @return
     */
	public String getImgUrl() {
		return imgUrl;
	}
    /***
     * 设置封面图片地址
     * @param imgUrl
     */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
    /***
     * 取得封面图片缩略图地址
     * @return
     */
	public String getImgThumbUrl() {
		if (StringUtils.isNotEmpty(imgThumbUrl))
			return imgThumbUrl;
		else
			return  imgUrl;
	}
    /***
     * 设置封面缩略图地址
     * @param imgThumbUrl
     */
	public void setImgThumbUrl(String imgThumbUrl) {
		this.imgThumbUrl = imgThumbUrl;
	}
	

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
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