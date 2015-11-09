package m.w.sys.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import m.w.core.dto.tree.TreeItem;
import m.w.core.dto.tree.Treeable;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

import com.google.common.collect.Sets;

/**
 * 用户
 * 
 * @author WenWu
 * 
 */
@Table("Sys_User")
public class User extends WrapSupport implements Serializable,
		AttachableEntity, Comparable<User>, Constable {
	private static final long serialVersionUID = -6909410061423195974L;

	@Override
	public Map<String, String> getConstFieldMap() {
		Map<String, String> fm = new HashMap<String, String>();
		// fm.put("editor", "editorText");
		return fm;
	}

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	// =========================================================================
	// 数据库字段
	// =========================================================================

	@Id
	@Column
	protected Long id;

	/**
	 * 用户名
	 */
	@Column
	@ColDefine(notNull = true, width = 50)
	protected String username;

	/**
	 * 用户口令
	 */
	@Column
	@ColDefine(notNull = true, width = 50)
	private String password;

    @Column
    @ColDefine(notNull = true, width = 50)
    private String salt;
    
	/**
	 * 是否管理员，默认不是
	 */
	@Column
	@Default("0")
	@ColDefine(notNull = true, type = ColType.CHAR, width = 1)
	protected boolean admin;
	// private String pauseText;

	/**
	 * 是否编辑，默认不是
	 */
	@Column
	@Default("0")
	@ColDefine(notNull = true, type = ColType.CHAR, width = 1)
	protected boolean editor;

	/**
	 * 是否有效
	 */
	@Column
	@Default("1")
	@ColDefine(notNull = true, type = ColType.CHAR, width = 1)
	private boolean enable;

	/**
	 * 身份证号
	 */
	@Column
	@ColDefine(width = 18)
	private String idNumber;

	/**
	 * 电子邮件
	 */
	@Column
	@ColDefine(width = 50)
	private String email;

	/**
	 * 性别
	 */
	@Column
	@Default("1")
	@ColDefine(type = ColType.CHAR, width = 1)
	private boolean gender;

	/**
	 * 出生日期
	 */
	@Column
	@ColDefine(type = ColType.DATE)
	private java.sql.Date dateOfBirth;

	/**
	 * 最后登录日期时间
	 */
	@Column
	@ColDefine(type = ColType.DATETIME)
	private java.util.Date lastLogin;

	@Column
	@ColDefine(width = 500)
	private String attaIds;

	@Column
	@ColDefine(width = 500)
	private String roleNames;

	@Column
	@ColDefine(width = 500)
	private String roleEditPieces;

	// =========================================================================
	// 填充字段
	// =========================================================================

	// =========================================================================
	// RBAC 相关字段
	// =========================================================================
	@JsonField(ignore = true)
	@ManyMany(target = Group.class, relation = "Sys_User_Group", from = "UserId", to = "GroupId")
	private List<Group> groups;

	@JsonField(ignore = true)
	@ManyMany(target = Role.class, relation = "Sys_User_Role", from = "UserId", to = "RoleId")
	private List<Role> roles;

	@JsonField(ignore = true)
	@ManyMany(target = Permission.class, relation = "Sys_User_Permission", from = "UserId", to = "PermissionId")
	private List<Permission> permissions;

	// =========================================================================

	@Override
	public int compareTo(User o) {
		return id != null ? id.compareTo(o.id) : 0;
	}

	// =========================================================================
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public java.util.Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(java.util.Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getAttaIds() {
		return attaIds;
	}

	public void setAttaIds(String attaIds) {
		this.attaIds = attaIds;
	}

	@Override
	public Set<String> getAttaFields() {
		return Sets.newHashSet("attaIds");
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleEditPieces() {
		return roleEditPieces;
	}

	public void setRoleEditPieces(String roleEditPieces) {
		this.roleEditPieces = roleEditPieces;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean editor) {
		this.editor = editor;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	

}
