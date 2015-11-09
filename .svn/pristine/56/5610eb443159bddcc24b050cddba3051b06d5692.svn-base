package m.w.sys.domain;

import java.io.Serializable;
import java.util.List;

import m.w.core.dao.IdEntity;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Table("Sys_Group")
public class Group implements Serializable, IdEntity {
    private static final long serialVersionUID = 2225281534404890387L;

    @Id
    private Long id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String name;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;

    @Column("isSystem")
    @ColDefine(type = ColType.CHAR, width = 1)
    private boolean system;

    // =========================================================================
    // RBAC 相关字段
    // =========================================================================
    @JsonField(ignore = true)
    @ManyMany(target = User.class, relation = "Sys_User_Group", from = "GroupId", to = "UserId")
    private List<User> users;

    @JsonField(ignore = true)
    @ManyMany(target = Role.class, relation = "Sys_Group_Role", from = "GroupId", to = "RoleId")
    private List<Role> roles;

    @JsonField(ignore = true)
    @ManyMany(target = Permission.class, relation = "Sys_Group_Permission", from = "GroupId", to = "PermissionId")
    private List<Permission> permissions;

    // =========================================================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

}
