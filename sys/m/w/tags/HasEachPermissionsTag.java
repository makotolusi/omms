package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有所有指定权限标签
 * 
 * @author WenWu
 * 
 */
public class HasEachPermissionsTag extends NameBaseTag {
    private static final long serialVersionUID = -3127220588094948078L;

    @Override
    protected boolean showTagBody(String permissionNames) {
        return Acls.hasEachPermissions(permissionNames);
    }

}
