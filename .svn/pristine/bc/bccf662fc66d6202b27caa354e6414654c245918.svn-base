package m.w.sys.service;

import java.util.Date;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.UserToken;
import m.w.sys.domain.CommerceUser;

@IocBean(fields = "dao")
public class CommerceUserService extends WxIdService<CommerceUser> {

	public CommerceUser getUserByToken(UserToken token){
		try {
			if (token == null
					|| token.getCommerceUserId() == null) {
				return null;
			} else {
				CommerceUser user = this
						.fetch(Cnd.where("id", "=",
								token.getCommerceUserId()));

				if (user == null) {
					return null;
				} else {
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CommerceUser login(UserToken token,CommerceUser user){
		try {
			if (token == null) {
				return null;
			} else {
				String phone=user.getPhoneNum();
				CommerceUser u=	this.fetch(Cnd.where("phoneNum", "=", phone));
				if (u == null) {
					return this.insert(user);
				} else {
					u.setLastLogin(new Date());
					return this.save(u);
				}
			}
		} catch (Exception e) {
			return null;
		}
	}
}
