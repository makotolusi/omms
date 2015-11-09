package m.w.sys.shiro;

import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import m.w.sys.domain.Permission;
import m.w.sys.domain.Role;
import m.w.sys.domain.User;
import m.w.sys.util.Webs;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class ShiroDbRealm extends AuthorizingRealm {
    private static final Log log = Logs.get();

    protected Dao dao;

    /**
     * 认证回调函数,登录时调用.
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = dao().fetch(User.class, Cnd.where("username", "=", token.getUsername()));
        
        if (user != null) {
            if(!user.isEnable()){
                throw new LockedAccountException("用户 [" + token.getUsername() + "] 被锁定！");
            }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
                                                                         user.getPassword(),
                                                                         getName());
            if (!Strings.isBlank(user.getSalt())) {
                ByteSource salt = ByteSource.Util.bytes(user.getSalt());
                info.setCredentialsSalt(salt);
            }
            log.debugf(getName() + " %s 登录成功", user.getUsername());
            return info;
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection<?> objs = principals.fromRealm(getName());
        if(objs.isEmpty()){
            return null;
        }
        Object obj = objs.iterator().next();
        if(obj == null || !(obj instanceof User)){
            return null;
        }
        User user = (User) obj;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        user = dao().fetchLinks(user, "roles");
        List<Role> roles = user.getRoles();
        for(Role r : roles){
            info.addRole(r.getName());
            r = dao().fetchLinks(r, "permissions");
            List<Permission> permissions = r.getPermissions();
            for(Permission p : permissions){
                info.addStringPermission(p.getCode());
            }
        }
        log.debugf(getName() + " %s 授权成功", user.getUsername());
        return info;
    }

    public ShiroDbRealm() {
        this("ShiroDbRealm");
    }

    public ShiroDbRealm(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dao dao() {
        if (dao == null) {
            Ioc ioc = Webs.ioc();
            dao = ioc.get(Dao.class, daoBeanName);
            return dao;
        }
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void setDataSource(DataSource dataSource) {
        this.dao = new NutDao(dataSource);
    }

    private String daoBeanName = "dao";

    public void setDaoBeanName(String daoBeanName) {
        this.daoBeanName = daoBeanName;
    }
}
