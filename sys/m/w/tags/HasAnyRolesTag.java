package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有任意一个角色标签
 * 
 * @author WenWu
 * 
 */
public class HasAnyRolesTag extends NameBaseTag {
    private static final long serialVersionUID = 385940014264901174L;

    @Override
    protected boolean showTagBody(String roleNames) {
        return Acls.hasAnyRoles(roleNames);
    }

}
