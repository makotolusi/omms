package m.w.frs.mgserver.module;

import java.util.Date;

import m.w.core.dto.DataGrid;
import m.w.frs.mg.Utils;
import m.w.frs.mgserver.domain.IndexPage;
import m.w.frs.mgserver.service.IndexPageService;
import m.w.mg.sso.util.UserUtils;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/pageMaintenance")
@IocBean
public class PageMaintenanceModule {

	@Inject
	private IndexPageService indexPageService;

	private static final String PS = "/page/maintenance";

	/** 打开主页面 */
	@At
	@Ok("jsp:" + PS + "/index")
	public void index() {

	}

	/** 新增公共关系-页面 */
	@At({ "/add", "/add/?" })
	@Ok("jsp:" + PS + "/add")
	public Object add(String type, Long id) {

		if (id != null) {
			return indexPageService.fetch(id);
		}
		IndexPage queryObj = new IndexPage();
		queryObj.setSystemName("乐果极客");
		queryObj.setClentName("pc");
		queryObj.setHeaderContext("极客，极客");
		return queryObj;
	}

	/** 新增 */
	@At
	@POST
	public Object save(@Param("..") IndexPage addObject) {

		addObject.setEntertime(new Date());
		addObject.setUsername(UserUtils.CurrentUser().getUsername());
		if (addObject.getId() == null) {

			return indexPageService.insert(addObject, "操作成功", "操作失败");
		}
		IndexPage queryObj = indexPageService.fetch(addObject.getId());
		Utils.copyFieldValue(addObject, queryObj);

		return indexPageService.update(queryObj, "操作成功", "操作失败");
	}

	/**  */
	@At({ "/items", "/items/?/?" })
	public DataGrid items() {

		return indexPageService.datagrid();

	}

}
