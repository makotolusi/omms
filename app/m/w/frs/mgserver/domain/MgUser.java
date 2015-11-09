package m.w.frs.mgserver.domain;

import java.io.Serializable;
import java.sql.Date;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_mgUser")
@Comment("用户")
public class MgUser extends WrapSupport implements Serializable, IdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3876561310388506873L;

	@Id
	@Column
	private Long id;

	/** 名称 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String username;

	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String password;

	/** 性别 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 2)
	private String sex;

	/** 个人邮箱 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String mail;

	/** 手机号码 */
	@Column
	@ColDefine(notNull = true, type = ColType.VARCHAR, width = 100)
	private String tel;
	
	/** 时间 */
	@Column
	private Date entertime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getEntertime() {
		return entertime;
	}

	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}
	
	
}
