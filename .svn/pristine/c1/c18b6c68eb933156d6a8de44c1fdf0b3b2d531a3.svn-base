package m.w.sys.shiro;

import java.util.List;

import javax.sql.DataSource;

import m.w.App;
import m.w.sys.domain.Permission;
import m.w.sys.domain.Role;
import m.w.sys.domain.User;
import m.w.sys.util.Webs;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class ShiroRootRealm extends AuthorizingRealm {
	private static final Log log = Logs.get();

	protected Dao dao;

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = null;
		if (App.isRootAccount(token.getUsername())) {
			if (App.isMatchRoot(token.getUsername(),
					new String(token.getPassword()))) {
				// TODO 构造默认root用户
				user = new User();
				user.setUsername(token.getUsername());
				user.setAdmin(true);
//				user.set("系统管理员");
			} else {
				throw new AccountException("禁用的用户名，登录失败！");
			}
		}

		if (user != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
					user.getPassword(), getName());
			log.debugf(getName() + " %s 登录成功", user.getUsername());
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (!App.isRoot()) {
			return null;
		}
		Object obj = principals.fromRealm(getName()).iterator().next();
		if (obj == null || !(obj instanceof User)) {
			return null;
		}
		User user = (User) obj;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		List<Role> roles = dao().query(Role.class, null);
		for (Role r : roles) {
			info.addRole(r.getName());
			r = dao().fetchLinks(r, "permissions");
			List<Permission> permissions = r.getPermissions();
			for (Permission p : permissions) {
				info.addStringPermission(p.getCode());
			}
		}

		info.addRole("admin");
		info.addStringPermission("*");
		log.debugf(getName() + " %s 授权成功", user.getUsername());
		return info;
	}

	public ShiroRootRealm() {
		this("ShiroRootRealm");
	}

	public ShiroRootRealm(String name) {
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
