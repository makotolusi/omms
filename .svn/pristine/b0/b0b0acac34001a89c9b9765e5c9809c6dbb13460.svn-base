package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有任意一个权限标签
 * 
 * @author WenWu
 * 
 */
public class HasAnyPermissionsTag extends NameBaseTag {
    private static final long serialVersionUID = 4471186031231702306L;

    @Override
    protected boolean showTagBody(String permissionNames) {
        return Acls.hasAnyPermissions(permissionNames);
    }

}
