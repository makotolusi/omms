package m.w.sys.shiro;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

public class ExtendWildcardPermission extends WildcardPermission {
    private static final long serialVersionUID = 2565695349956066059L;
    protected static final String EXTEND_WILDCARD_TOKEN = "#";

    public ExtendWildcardPermission(String permissionString) {
        super(permissionString);
    }

    @Override
    public boolean implies(Permission p) {
        // By default only supports comparisons with other WildcardPermissions
        if (!(p instanceof ExtendWildcardPermission)) {
            return false;
        }

        ExtendWildcardPermission wp = (ExtendWildcardPermission) p;

        List<Set<String>> otherParts = wp.getParts();
        boolean hasExtendWildCard = false;
        int i = 0;
        for (Set<String> otherPart : otherParts) {
            // If this permission has less parts than the other permission, everything after the number of parts contained
            // in this permission is automatically implied, so return true
            if (getParts().size() - 1 < i) {
                return true;
            } else {
                Set<String> part = getParts().get(i);
                //特殊处理一下EXTEND_WILDCARD_TOKEN #
                if (otherPart.contains(EXTEND_WILDCARD_TOKEN)) {
                    hasExtendWildCard = true;
                }else if(!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)){
                    return false;
                }
                i++;
            }
        }

        if(!hasExtendWildCard){
            // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
            for (; i < getParts().size(); i++) {
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN)) {
                    return false;
                }
            }
        }

        return true;
    }

    
}
