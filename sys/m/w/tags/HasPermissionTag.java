package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有某个权限标签
 * 
 * @author WenWu
 * 
 */
public class HasPermissionTag extends NameBaseTag {
    private static final long serialVersionUID = 46918447530611756L;

    @Override
    protected boolean showTagBody(String permissionName) {
        return Acls.hasPermission(permissionName);
    }

}
