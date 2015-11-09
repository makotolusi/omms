package m.w.frs.mgserver.service;

import java.util.List;

import m.w.core.service.WxIdService;
import m.w.frs.mgserver.domain.PieceMaintain;

import org.apache.axis.utils.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(fields = "dao")
public class PieceMaintainService extends WxIdService<PieceMaintain> {
	
	// 正式发布后将master数据存入缓存中
	public String getTextbyIds(String ids) {

		if (StringUtils.isEmpty(ids))
			return "";
		Cnd cnd = Cnd.limit();
		cnd.and("id", "in", ids);
		List<PieceMaintain> pm = this.query(cnd, null);
		String texts = "";
		for (PieceMaintain p : pm) {
			texts = texts + p.getText() + " ";
		}
		return texts;
	}
}
