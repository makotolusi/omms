package m.w.mg.sso.util;

import m.w.sys.domain.User;

import org.apache.shiro.SecurityUtils;

public class UserUtils {
	public static User CurrentUser()  {
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (obj != null && obj instanceof User) {
			User usr = (User) obj;
			return usr;
		}
		return null;
	}
}
