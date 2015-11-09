package m.w.frs.mgserver.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.Activity;
import m.w.frs.mgserver.domain.DeviceResolutionRule;
import m.w.sys.service.PicHandlerService;

@IocBean(fields = "dao")
public class ActivityService extends WxIdService<Activity> {

	public static final String RUSH_STATUS = "1";

	@Inject
	private PicHandlerService picHandlerService;

	public Object getActivity(String appUserId, String res) {

		List<Activity> as = this.query(Cnd.where("rushStatus", "=", RUSH_STATUS).desc("entertime"), null);

		for (Activity a : as) {
			String url = picHandlerService.getQiniuPrivateUrl(a.getImgUrl(), res, DeviceResolutionRule.RULE1);
			a.setImgUrl(url);
		}

		return as;
	}

}
