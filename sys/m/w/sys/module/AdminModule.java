package m.w.sys.module;

import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.sys.service.AuthAdminService;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

@At("/sys/admin")
@IocBean
public class AdminModule extends BasicModule {
	private static final String PS = "/sys/admin";
	
	@Inject
	private AuthAdminService authAdminService;

	/**
	 * 信息维护主界面
	 */
	@At
	@Ok("jsp:"+PS+"/index")
	public void index() {

	}
		
    @At
    public DataGrid auths() {
    	return authAdminService.datagrid(true);
    }
	
    @At
    @POST
    public Result add(String positionIds, String positionNames){
    	return authAdminService.addAuth(positionIds, positionNames);
    }
    
    @At
    @POST
    public Result del(String ids){
    	return authAdminService.xdelete(ids, "删除授权成功", "删除授权失败");
    }
}
