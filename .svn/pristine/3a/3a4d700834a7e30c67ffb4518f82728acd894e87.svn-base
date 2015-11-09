package m.w.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.sys.domain.Permission;
import m.w.sys.domain.Role;
import m.w.sys.domain.User;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

@IocBean(fields="dao")
public class RoleService extends WxIdService<Role> {

    public Role fetchWithPermissions(Long id) {
        Role role = fetch(id);
        role = dao().fetchLinks(role, "permissions");
        List<Permission> permissions = role.getPermissions();
        for(Permission p : permissions){
            p.wrap();
        }
        return role;
    }

    public Result addPermissions(Long id, String permissionIds) {
        Role role = fetchWithPermissions(id);
        List<Permission> permissions = role.getPermissions();
        if(null == permissions){
            permissions = new ArrayList<Permission>();
        }
        Set<Long> old = new HashSet<Long>();
        for(Permission p : permissions){
            old.add(p.getId());
        }
        List<Permission> toAdd = new ArrayList<Permission>();
        for(String pid : Strings.splitIgnoreBlank(permissionIds, ",")){
            if(!old.contains(Long.parseLong(pid))){
                toAdd.add(new Permission(Long.parseLong(pid)));
            }
        }
        role.setPermissions(toAdd);
        dao().insertRelation(role, "permissions");
        return Result.ok("向"+role.getName()+"添加权限成功！");
    }
    
    public Result delPermissions(Long id, String permissionIds) {
        Set<Long> toDel = new HashSet<Long>();
        for(String pid : Strings.splitIgnoreBlank(permissionIds, ",")){
            toDel.add(Long.parseLong(pid));
        }
        dao().clear("Sys_Role_Permission", Cnd.where("roleId", "=", id).and("PermissionId", "in", toDel));
        return Result.ok("删除权限成功！");
    }

    //=========================================================================
    public Role fetchWithUsers(Long id) {
        Role role = fetch(id);
        role = dao().fetchLinks(role, "users");
        List<User> users = role.getUsers();
        for(User u : users){
            u.wrap();
        }
        return role;
    }
    
    public Result addUsers(Long id, String userIds) {
        Role role = fetchWithUsers(id);
        List<User> users = role.getUsers();
        if(null == users){
            users = new ArrayList<User>();
        }
        Set<Long> old = new HashSet<Long>();
        for(User u : users){
            old.add(u.getId());
        }
        List<User> toAdd = new ArrayList<User>();
        for(String uid : Strings.splitIgnoreBlank(userIds, ",")){
            if(!old.contains(Long.parseLong(uid))){
                toAdd.add(new User(Long.parseLong(uid)));
            }
        }
        role.setUsers(toAdd);
        dao().insertRelation(role, "users");
        return Result.ok("向"+role.getName()+"添加用户成功！");
    }
    
    public Result delUsers(Long id, String userIds) {
        Set<Long> toDel = new HashSet<Long>();
        for(String uid : Strings.splitIgnoreBlank(userIds, ",")){
            toDel.add(Long.parseLong(uid));
        }
        dao().clear("Sys_User_Role", Cnd.where("roleId", "=", id).and("UserId", "in", toDel));
        return Result.ok("删除用户成功！");
    }

    //=========================================================================
    @Override
    protected void afterDelete(Role role) {
        dao().clear("Sys_User_Role", Cnd.where("roleId", "=", role.getId()));
        dao().clear("Sys_Role_Permission", Cnd.where("roleId", "=", role.getId()));
    }
}
