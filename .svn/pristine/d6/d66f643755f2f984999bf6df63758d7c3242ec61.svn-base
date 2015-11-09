package m.w.sys.module;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.sys.service.AuthTreeNodeService;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.POST;

@At("/sys/authtreenode")
@IocBean
public class AuthTreeNodeModule extends BasicModule {
	
	@Inject
	private AuthTreeNodeService authTreeNodeService;
	
	@At("/auths/?")
    public DataGrid auths(String authType, String nodeId) {
    	if(Strings.isBlank(nodeId)){
    		return DataGrid.EMPTY;
    	}else{
    		return authTreeNodeService.datagrid(true, Cnd.where("authType", "=", authType));
    	}
    }
	
    @At("/add/?")
    @POST
    public Result add(String authType, String nodeId, String positionIds, String positionNames, String roleNames){
    	return authTreeNodeService.addAuth(authType, nodeId, positionIds, positionNames, roleNames);
    }
    
    @At
    @POST
    public Result del(String ids){
    	return authTreeNodeService.xdelete(ids, "删除授权成功", "删除授权失败");
    }
}
