package m.w.sys.domain;

import java.io.Serializable;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("Sys_Auth_Admin")
public class AuthAdmin extends WrapSupport implements Serializable, IdEntity{
	private static final long serialVersionUID = 6343104708599734182L;

	public AuthAdmin() {

	}
	public AuthAdmin(Long positionId, String positionName) {
		this.positionId = positionId;
		this.positionName = positionName;
	}

	@Id
	@Column
	private Long id;
	
    /**
     * 被授权的岗位ID
     */
    @Column
    @ColDefine(notNull = true, type = ColType.INT, width = 16, precision = 0)
    private Long positionId;
    
    /**
     * 被授权的岗位全程
     */
    @Column
    @ColDefine(notNull = true, type = ColType.VARCHAR, width = 500)
    private String positionName;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
