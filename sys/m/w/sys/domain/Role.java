package m.w.sys.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import m.w.core.dao.IdEntity;
import m.w.sys.util.WrapSupport;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

@Table("Sys_Role")
public class Role extends WrapSupport implements Serializable, IdEntity, Constable {
    private static final long serialVersionUID = -2364823167916852619L;
    
    @Override
    public Map<String, String> getConstFieldMap() {
        Map<String, String> fm = new HashMap<String, String>();
        fm.put("system",     "systemText");
        return fm;
    }
    
    public static enum SYSTEM{
        YES, NO
    }
    
    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    @Id
    @Column
    private Long id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String name;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;

    @Column("isSystem")
    @ColDefine(type = ColType.VARCHAR, width = 3)
    private SYSTEM system;
    private String systemText;

    // =========================================================================
    // RBAC 相关字段
    // =========================================================================
    @JsonField(ignore = true)
    @ManyMany(target = User.class, relation = "Sys_User_Role", from = "RoleId", to = "UserId")
    private List<User> users;
    
    @JsonField(ignore = true)
    @ManyMany(target = Group.class, relation = "Sys_Group_Role", from = "RoleId", to = "GroupId")
    private List<Group> groups;

    @JsonField(ignore = true)
    @ManyMany(target = Permission.class, relation = "Sys_Role_Permission", from = "RoleId", to = "PermissionId")
    private List<Permission> permissions;

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

    public SYSTEM getSystem() {
        return system;
    }

    public void setSystem(SYSTEM system) {
        this.system = system;
    }

    public String getSystemText() {
        return systemText;
    }

    public void setSystemText(String systemText) {
        this.systemText = systemText;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
    //=========================================================================
    
}
