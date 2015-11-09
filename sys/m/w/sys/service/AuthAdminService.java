package m.w.sys.service;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.sys.domain.AuthAdmin;
import m.w.sys.util.PositionNameHelper;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

@IocBean(fields = "dao")
public class AuthAdminService extends WxIdService<AuthAdmin>{

	public Result addAuth(String positionIds, String positionNames){
		if(!Strings.isBlank(positionIds) && !Strings.isBlank(positionNames)){
			String[] ids = Strings.splitIgnoreBlank(positionIds);
			String[] names = Strings.splitIgnoreBlank(positionNames);
			if(ids != null && names != null && ids.length == names.length){
				for(int i=0; i<ids.length; i++){
					AuthAdmin auth = find(ids[i]);
					if(auth == null){
						dao().insert(new AuthAdmin(Long.parseLong(ids[i]), names[i]));
					}else if(!names[i].equals(auth.getPositionName())){
						PositionNameHelper.updatePositionName(Long.parseLong(ids[i]), names[i]);
					}
				}
				return Result.ok();
			}
		}
		return Result.err();
	}
	
	private AuthAdmin find(String positionId){
		return dao().fetch(AuthAdmin.class, Cnd.where("positionId", "=", positionId));
	}
}
