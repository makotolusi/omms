package m.w.frs.mgserver.service;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.SplashScreen;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class SplashScreenService extends WxIdService<SplashScreen> {

	public Object getSplashScreen() {
		// TODO 
		return this.fetch(1);
	}
	
	public Object getSplashForHomeHeaderCell(String flg,String size){
		
		// TODD 需要返回带大小的图片
		return this.query(Cnd.where("flg", "=", flg), null);
		
	}
}
