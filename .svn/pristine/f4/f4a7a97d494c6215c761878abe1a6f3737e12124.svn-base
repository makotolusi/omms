package m.w.sys.module;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.sys.domain.Role;
import m.w.sys.service.RoleService;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/sys/role")
@IocBean
public class RoleModule extends BasicModule {
    @Inject
    private RoleService roleService;

    @At
    @Ok("jsp:/sys/role/index")
    public void index() {

    }
    
    @At
    public DataGrid items() {
        return roleService.datagrid(true);
    }
    
    @At
    @GET
    @Ok("jsp:/ex/sys/role/add")
    public void add() {

    }
    
    @At
    @POST
    public Result add(@Param("..") Role role) {
        return roleService.insert(role, null, null);
    }
    
    @At("/update/?")
    @GET
    @Ok("jsp:/ex/sys/role/update")
    public Object update(Long id) {
        return roleService.view(id);
    }

    @At
    @POST
    public Result update(@Param("..") Role role) {
        return roleService.update(role, null, null);
    }

    @At("/delete/?")
    @DELETE
    public Result delete(Long id) {
        return roleService.delete(id, null, null);
    }
    
    @At("/view/?")
    @GET
    @Ok("jsp:/ex/sys/role/view")
    public Object view(Long id) {
        return roleService.view(id);
    }
    
    //=========================================================================
    @At("/permission/?")
    @GET
    @Ok("jsp:/ex/sys/role/permission")
    public Object permission(Long id){
        return roleService.fetch(id);
    }
    
    @At("/permissions/?")
    public Object permissions(Long id){
        return roleService.fetchWithPermissions(id).getPermissions();
    }
    
    @At("/addPermissions/?")
    @POST
    public Result addPermissions(Long id, @Param("permissionIds")String permissionIds){
        return roleService.addPermissions(id, permissionIds);
    }
    
    @At("/delPermissions/?")
    @POST
    public Result delPermissions(Long id, @Param("permissionIds")String permissionIds){
        return roleService.delPermissions(id, permissionIds);
    }

    //=========================================================================
    @At("/user/?")
    @GET
    @Ok("jsp:/ex/sys/role/user")
    public Object user(Long id){
        return roleService.fetch(id);
    }
    
    @At("/users/?")
    public Object users(Long id){
        return roleService.fetchWithUsers(id).getUsers();
    }
    
    @At("/addUsers/?")
    @POST
    public Result addUsers(Long id, @Param("userIds")String userIds){
        return roleService.addUsers(id, userIds);
    }
    
    @At("/delUsers/?")
    @POST
    public Result delUsers(Long id, @Param("userIds")String userIds){
        return roleService.delUsers(id, userIds);
    }
    
    //=========================================================================
    @At("/position/?")
    @GET
    @Ok("jsp:/ex/sys/role/position")
    public Object position(Long id){
        return roleService.fetch(id);
    }
}
