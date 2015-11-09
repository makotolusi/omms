package m.w.sys.service;

import m.w.core.dto.Result;
import m.w.core.service.WxIdService;
import m.w.sys.domain.AuthTreeNode;
import m.w.sys.util.PositionNameHelper;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

@IocBean(fields = "dao")
public class AuthTreeNodeService extends WxIdService<AuthTreeNode>{

	public Result addAuth(String authType, String nodeId, String positionIds, String positionNames, String roleNames){
		if(!Strings.isBlank(nodeId) && !Strings.isBlank(positionIds) && !Strings.isBlank(positionNames) && !Strings.isBlank(roleNames)){
			nodeId = nodeId.trim();
			String[] ids = Strings.splitIgnoreBlank(positionIds);
			String[] names = Strings.splitIgnoreBlank(positionNames);
			if(ids != null && names != null && ids.length == names.length){
				for(int i=0; i<ids.length; i++){
					AuthTreeNode auth = find(authType, nodeId, ids[i]);
					if(auth == null){
						dao().insert(new AuthTreeNode(authType, nodeId, Long.parseLong(ids[i]), names[i], roleNames));
					}else if(!names[i].equals(auth.getPositionName())){
						PositionNameHelper.updatePositionName(Long.parseLong(ids[i]), names[i]);
					}
				}
				return Result.ok();
			}
		}
		return Result.err();
	}
	
	private AuthTreeNode find(String authType, String nodeId, String positionId){
		return dao().fetch(AuthTreeNode.class, Cnd.where("nodeId", "=", nodeId).and("positionId", "=", positionId).and("authType", "=", authType));
	}
}
