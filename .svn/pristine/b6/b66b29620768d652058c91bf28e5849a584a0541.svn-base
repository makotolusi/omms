package m.w.sys.module;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.sys.domain.Permission;
import m.w.sys.service.PermissionService;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/sys/permission")
@IocBean
public class PermissionModule extends BasicModule{
    @Inject
    private PermissionService permissionService;

    @At
    @Ok("jsp:/sys/permission/index")
    public void index() {

    }
    
    @At
    public DataGrid items() {
        return permissionService.datagrid(true, (Cnd)Cnd.limit().asc("module").asc("orders"));
    }
    
    @At
    @POST
    public Result add(@Param("..") Permission permission) {
        return permissionService.insert(permission, null, null);
    }

    @At
    @POST
    public Result update(@Param("..") Permission permission) {
        return permissionService.update(permission, null, null);
    }

    @At("/delete/?")
    @DELETE
    public Result delete(Long id) {
        return permissionService.delete(id, null, null);
    }    
}
