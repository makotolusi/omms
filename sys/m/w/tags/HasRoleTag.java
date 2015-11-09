package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有某个角色标签
 * 
 * @author WenWu
 * 
 */
public class HasRoleTag extends NameBaseTag {
    private static final long serialVersionUID = -2404101926716003429L;

    @Override
    protected boolean showTagBody(String roleName) {
        return Acls.hasRole(roleName);
    }

}
