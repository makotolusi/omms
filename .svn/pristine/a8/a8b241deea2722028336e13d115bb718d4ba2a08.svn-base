package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否具有所有角色标签
 * 
 * @author WenWu
 * 
 */
public class HasEachRolesTag extends NameBaseTag {
    private static final long serialVersionUID = 4733782502335653680L;

    @Override
    protected boolean showTagBody(String roleNames) {
        return Acls.hasEachRoles(roleNames);
    }

}
