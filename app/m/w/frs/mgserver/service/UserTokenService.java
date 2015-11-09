package m.w.frs.mgserver.service;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.UserToken;

@IocBean(fields = "dao")
public class UserTokenService extends WxIdService<UserToken> {

	public int insertIgnore(UserToken userToken) {
		Sql sql = Sqls.create(
				"insert ignore into commerce_user_token (token, channel,os,createDate,lastReqDate,platform,reqCount,resolutionId) values (@token,@channel,@os,@createDate,@lastReqDate,@platform,@reqCount,@resolutionId)");
		sql.params().set("token", userToken.getToken());
		sql.params().set("channel", userToken.getChannel());
		sql.params().set("os", userToken.getOs());
		sql.params().set("createDate", userToken.getCreateDate());
		sql.params().set("lastReqDate", userToken.getLastReqDate());
		sql.params().set("platform", userToken.getPlatform());
		sql.params().set("reqCount", userToken.getReqCount());
		this.dao().execute(sql);
		return sql.getUpdateCount();
	}

	public UserToken getByToken(String token) {
		return this.fetch(Cnd.where("token", "=", token));
	}

	public boolean isNewUserToken(UserToken userToken) {
		return this.insertIgnore(userToken) > 0 ? true : false;
	}
}
