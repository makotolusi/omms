package m.w.tags;

import m.w.sys.util.Acls;

/**
 * 判定是否缺少某个角色标签
 * 
 * @author WenWu
 * 
 */
public class LacksRoleTag extends NameBaseTag {
    private static final long serialVersionUID = 793100263451504244L;

    @Override
    protected boolean showTagBody(String roleName) {
        return Acls.lacksRole(roleName);
    }

}
