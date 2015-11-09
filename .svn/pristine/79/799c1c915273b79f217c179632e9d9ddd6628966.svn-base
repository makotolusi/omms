package m.w.sys.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

public class ExtendWildcardPermissionResolver implements PermissionResolver{

    public Permission resolvePermission(String permissionString) {
        return new ExtendWildcardPermission(permissionString);
    }
}
