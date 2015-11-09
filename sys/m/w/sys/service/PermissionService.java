package m.w.sys.service;

import m.w.core.exception.AppException;
import m.w.core.service.WxIdService;
import m.w.sys.domain.Permission;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields="dao")
public class PermissionService extends WxIdService<Permission>{

    @Override
    protected void beforeInsert(Permission obj) {
        super.beforeInsert(obj);
        Permission p = fetch(Cnd.where("code", "=", obj.getCode()));
        if(p!=null){
            throw new AppException("已存在一个代码为["+p.getCode()+"]，名称为["+p.getName()+"]的权限资源，不可重复添加相同的权限资源！");
        }
    }

    @Override
    protected void beforeUpdate(Permission obj) {
        super.beforeUpdate(obj);
        Permission p = fetch(Cnd.where("code", "=", obj.getCode()).and("id", "<>", obj.getId()));
        if(p!=null){
            throw new AppException("已存在一个代码为["+p.getCode()+"]，名称为["+p.getName()+"]的权限资源，不可重复添加相同的权限资源！");
        }
    }

    @Override
    protected void afterDelete(Permission permission) {
        dao().clear("Sys_Role_Permission", Cnd.where("permissionId", "=", permission.getId()));
    }
}
