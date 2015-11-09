package m.w.sys.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import m.w.mg.sso.util.UserUtils;
import m.w.sys.domain.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.nutz.lang.Strings;

public abstract class Acls {
    public static Subject getSubject(SecurityManager securityManager,
                                     ServletRequest request,
                                     ServletResponse response) {
        return new WebSubject.Builder(securityManager, request, response).buildSubject();
    }

    /**
     * 判断当前用户是否具有某个角色
     * 
     * @param roleName
     *            角色名称
     * @return
     */
    public static boolean hasRole(String roleName) {
    	User u = UserUtils.CurrentUser();
    	if(u.isAdmin()){
    		return true;
    	}
        return SecurityUtils.getSubject().hasRole(roleName);

    }

    /**
     * 判断当前用户是否缺少某个角色
     * 
     * @param roleName
     *            角色名称
     * @return
     */
    public static boolean lacksRole(String roleName) {
        return !hasRole(roleName);
    }

    /**
     * 判断当前用户是否具有任意一个角色
     * 
     * @param roleNames
     *            逗号分隔的角色列表
     * @return
     */
    public static boolean hasAnyRoles(String roleNames) {
        if (Strings.isBlank(roleNames)) {
            return true;
        }
        return hasAnyRoles(Strings.splitIgnoreBlank(roleNames, " "));
    }

    /**
     * 判断当前用户是否具有任意一个角色
     * 
     * @param roleNames
     *            角色名称列表
     * @return
     */
    public static boolean hasAnyRoles(String... roleNames) {
        if (roleNames == null) {
            return true;
        }
        for (String name : roleNames) {
            if (hasRole(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前用户是否具有所有指定的角色
     * 
     * @param roleNames
     *            角色名称列表
     * @return
     */
    public static boolean hasEachRoles(String roleNames) {
        if (Strings.isBlank(roleNames)) {
            return true;
        }
        return hasAnyRoles(Strings.splitIgnoreBlank(roleNames, " "));
    }

    /**
     * 判断当前用户是否具有所有指定的角色
     * 
     * @param roleNames
     *            角色名称列表
     * @return
     */
    public static boolean hasEachRoles(String... roleNames) {
        if (roleNames == null) {
            return true;
        }
        for (String name : roleNames) {
            if (!hasRole(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断当前用户是否具有某个操作权限
     * 
     * @param permissionName
     *            权限名称
     * @return
     */
    public static boolean hasPermission(String permissionName) {
    	User u = UserUtils.CurrentUser();
    	if(u.isAdmin()){
    		return true;
    	}
        return SecurityUtils.getSubject().isPermitted(permissionName);
    }

    /**
     * 判断当前用户是否缺少某个权限
     * 
     * @param permissionName
     *            权限名称
     * @return
     */
    public static boolean lacksPermission(String permissionName) {
        return !hasPermission(permissionName);
    }

    /**
     * 判断当前用户是否具有任意一个操作权限
     * 
     * @param permissionNames
     *            逗号分隔的权限名称
     * @return
     */
    public static boolean hasAnyPermissions(String permissionNames) {
        if (Strings.isBlank(permissionNames)) {
            return true;
        }
        return hasAnyPermissions(Strings.splitIgnoreBlank(permissionNames, " "));
    }

    /**
     * 判断当前用户是否具有任意一个操作权限
     * 
     * @param permissionNames
     *            权限名称列表
     * @return
     */
    public static boolean hasAnyPermissions(String... permissionNames) {
        if (permissionNames == null) {
            return true;
        }
        for (String name : permissionNames) {
            if (hasPermission(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前用户是否具有所有列表中的操作权限
     * 
     * @param permissionNames
     *            权限名称列表
     * @return
     */
    public static boolean hasEachPermissions(String permissionNames) {
        if (Strings.isBlank(permissionNames)) {
            return true;
        }
        return hasAnyPermissions(Strings.splitIgnoreBlank(permissionNames, " "));
    }

    /**
     * 判断当前用户是否具有所有列表中的操作权限
     * 
     * @param permissionNames
     *            权限名称列表
     * @return
     */
    public static boolean hasEachPermissions(String... permissionNames) {
        if (permissionNames == null) {
            return true;
        }
        for (String name : permissionNames) {
            if (!hasPermission(name)) {
                return false;
            }
        }
        return true;
    }
}
