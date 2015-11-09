package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否缺少某个权限标签
 * 
 * @author WenWu
 * 
 */
public class LacksPermissionTag extends NameBaseTag {
    private static final long serialVersionUID = -1449529028604136929L;

    @Override
    protected boolean showTagBody(String permissionName) {
        return Acls.lacksPermission(permissionName);
    }

}
