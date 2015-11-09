package m.w.sys.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

/** 机构树的授权 */
@Table("Sys_Auth_TreeNode")
public class AuthTreeNode extends WrapSupport implements Serializable, IdEntity{
	private static final long serialVersionUID = 6343104708599734182L;
	
	/**
	 * 授权类型
	 * @author WenWu
	 *
	 */
	public static enum AUTH_TYPE {
		/** 发票 */
		invoice,
		/** 文件借用 */
		file
	}
	
	public AuthTreeNode() {

	}

	public AuthTreeNode(String authType, String nodeId, Long positionId, String positionName, String roleNames) {
		this.nodeId = nodeId;
		this.positionId = positionId;
		this.positionName = positionName;
		this.authType = authType;
		this.roleNames = roleNames;
	}

	@Id
	@Column
	private Long id;
	
	/**
	 * 授权类型
	 * 发票授权：invoice
	 * 文件借用：file
	 */
    @Column
    @ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String authType;
	
	/**
	 * 被授权的节点ID
	 */
    @Column
    @ColDefine(notNull = true, type = ColType.VARCHAR, width = 50)
	private String nodeId;

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
    
    /**
     * 被授权的角色名称，使用逗号分割
     */
    @Column
    @ColDefine(notNull = true, type = ColType.VARCHAR, width = 500)
    private String roleNames;
    
    private Set<String> roles;
    
    /** 判断该授权是否包含某个角色 */
    public synchronized boolean hasRole(String roleName){
    	if(roles==null){
    		roles = new HashSet<String>();
    		roles.addAll(Lang.array2list(Strings.splitIgnoreBlank(roleNames)));
    	}
    	return roles.contains(roleName);
    }
    
    /**
     * 判断该节点授权是否为对根节点的授权
     * @return
     */
    public boolean isRootNode(){
    	return "ROOT".equals(nodeId);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}
}
