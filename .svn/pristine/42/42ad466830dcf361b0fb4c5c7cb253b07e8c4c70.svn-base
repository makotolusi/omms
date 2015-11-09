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

@Table("Sys_Permission")
public class Permission extends WrapSupport implements Serializable, IdEntity, Constable {
    private static final long serialVersionUID = -8143507010828266169L;
    
    @Override
    public Map<String, String> getConstFieldMap() {
        Map<String, String> fm = new HashMap<String, String>();
        fm.put("module",     "moduleName");
        return fm;
    }

    public Permission() {}

    public Permission(Long id) {
        this.id = id;
    }
    
    @Id
    private Long id;

    @Column
    @ColDefine(notNull=true, type = ColType.VARCHAR, width = 100)
    private String code;
    
    @Column
    @ColDefine(notNull=true, type = ColType.VARCHAR, width = 200)
    private String name;
    
    @Column
    @ColDefine(notNull=true, type = ColType.VARCHAR, width = 5)
    private String module;
    private String moduleName;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    private String description;
    
    @Column
    private Integer orders;

    // =========================================================================
    // RBAC 相关字段
    // =========================================================================
    @JsonField(ignore = true)
    @ManyMany(target = Role.class, relation = "Sys_Role_Permission", from = "PermissionId", to = "RoleId")
    private List<Role> roles;

    @JsonField(ignore = true)
    @ManyMany(target = User.class, relation = "Sys_User_Permission", from = "PermissionId", to = "UserId")
    private List<User> users;

    @JsonField(ignore = true)
    @ManyMany(target = Group.class, relation = "Sys_Group_Permission", from = "PermissionId", to = "GroupId")
    private List<Group> groups;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
