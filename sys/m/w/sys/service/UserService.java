package m.w.sys.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import m.w.core.dto.Result;
import m.w.sys.domain.Role;
import m.w.sys.domain.User;
import m.w.sys.util.Users;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

@IocBean(fields="dao")
public class UserService extends AttachableService<User>{

    @Override
    protected void afterInsert(User user) {
        //TODO 插入用户后设置salt，并加密密码
        super.afterInsert(user);
    }

    @Override
    public boolean update(User user) {
        beforeUpdate(user);
        boolean success = (1 == dao().updateIgnoreNull(user));//可能密码没有输入则忽略
        if (success) {
            afterUpdate(user);
        }
        return success;
    }
    
    public Result chpwd(String oldPassword, String newPassword){
        User u = fetch(Users.user().getId());
        if(!u.getPassword().equals(oldPassword)){
            return Result.err("旧密码输入错误！");
        }
        u.setPassword(newPassword);
        update(u);
        return Result.ok("密码修改成功！");
    }
    
    //=========================================================================
    public User fetchWithRoles(Long id) {
        User user = fetch(id);
        user = dao().fetchLinks(user, "roles");
        return user;
    }

    public Result addRoles(Long id, String roleIds) {
        User user = fetchWithRoles(id);
        List<Role> roles = user.getRoles();
        if(null == roles){
            roles = new ArrayList<Role>();
        }
        Set<Long> old = new HashSet<Long>();
        for(Role r : roles){
            old.add(r.getId());
        }
        List<Role> toAdd = new ArrayList<Role>();
        for(String rid : Strings.splitIgnoreBlank(roleIds, ",")){
            if(!old.contains(Long.parseLong(rid))){
                toAdd.add(new Role(Long.parseLong(rid)));
            }
        }
        user.setRoles(toAdd);
        dao().insertRelation(user, "roles");
        return Result.ok("向"+user.getUsername()+"添加角色成功！");
    }
    public Result addRoles(String username, String roleNames) {
    	User u = this.fetch(Cnd.limit().and("username", "=", username));
    	u.setRoleNames(roleNames);
    	return this.update(u, "向"+username+"添加角色成功！", "向"+username+"添加角色失败！");
    	
    }

    public Result delRoles(Long id, String roleIds) {
        Set<Long> toDel = new HashSet<Long>();
        for(String rid : Strings.splitIgnoreBlank(roleIds, ",")){
            toDel.add(Long.parseLong(rid));
        }
        dao().clear("Sys_User_Role", Cnd.where("userId", "=", id).and("roleId", "in", toDel));
        return Result.ok("删除角色成功！");
    }
    public Result delRoles(String username) {
    	
       	User u = this.fetch(Cnd.limit().and("username", "=", username));
    	u.setRoleNames("");
    	return this.update(u, "向"+username+"取消角色成功！", "向"+username+"取消角色失败！");
 
    }
    
    public Result savePieces(Long id, String roleNames) {
    	User u = this.fetch(id);
    	u.setRoleEditPieces(roleNames);
    	return this.update(u, "成功！", "失败！");
    	
    }
    public Result delQuery(Long id) {
    	
       	User u = this.fetch(Cnd.limit().and("id", "=", id));
    	u.setRoleEditPieces("");
    	return this.update(u, "向"+u.getUsername()+"取消角色板块编辑范围成功！", "向"+u.getUsername()+"取消角色板块编辑范围失败！");
 
    }
}
