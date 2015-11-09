package m.w.mg.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import m.w.App;
import m.w.frs.mg.ImsRoles;
import m.w.mg.sso.util.UserPassUtil;
import m.w.sys.domain.User;
import m.w.sys.util.Webs;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class Realm extends AuthorizingRealm {
	private static final Log log = Logs.get();

	protected Dao dao;

	/**
	 * 认证回调函数,登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// try {
		// User user = dao().fetch(User.class,
		// Cnd.where("username", "=", token.getUsername()));
		// if (user.getPassword().equals(String.valueOf(token.getPassword()))) {
		//
		// SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
		// user, token.getPassword(), getName());
		// log.debugf(getName() + " %s 登录成功", user.getEmail());
		// return info;
		// }
		// } catch (Exception e) {
		// log.debug(e);
		// }
		// return null;
		try {
			if (App.isRootAccount(token.getUsername())) {
				if (App.isMatchRoot(token.getUsername(),
						new String(token.getPassword()))) {
					// ZtjsUser user = ZtjsUsers.XMAN;
					// user.setToken(token);
					// SimpleAuthenticationInfo info = new
					// SimpleAuthenticationInfo(user, token.getPassword(),
					// getName());
					// log.debugf(getName() + " %s 登录成功", user.getUsername());
					// return info;
				}
			} else {
				User user = dao().fetch(User.class,
						Cnd.where("username", "=", token.getUsername()));
				String pw = UserPassUtil.encryptyEC(String.valueOf(token
						.getPassword()));
				if (user.getPassword().equals(pw)) {

					SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
							user, token.getPassword(), getName());
					log.debugf(getName() + " %s 登录成功", user.getEmail());
					return info;
				}
			}
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Collection<?> objs = principals.fromRealm(getName());
		if (objs.isEmpty()) {
			return null;
		}
		Object obj = objs.iterator().next();
		if (!(obj instanceof User)) {
			return null;
		}

		User user = (User) obj;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roleNames = new HashSet<String>();

		String rNames = user.getRoleNames();
		if (StringUtils.isEmpty(rNames)) {
			return null;
		}
		if (rNames.contains("板块编辑")) {
			roleNames.add("板块编辑");
		}
		if (rNames.contains("维护员")) {
			roleNames.add("维护员");
		}
		if (rNames.contains("商品发布管理员")) {
			roleNames.add("商品发布管理员");
		}
		if (rNames.contains("审批员")) {
			roleNames.add("审批员");
		}
		
		if (user.isAdmin()) {
			roleNames.add(ImsRoles.SYS_ADMIN);
		}
		if (!roleNames.isEmpty()) {
			if (roleNames.contains(ImsRoles.SYS_ADMIN)) {
				info.addRoles(ImsRoles.all());
			} else {
				info.addRoles(roleNames);
			}
		}

		return info;
	}

	public Realm() {
		this("BkmstRealm");
	}

	public Realm(String name) {
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
