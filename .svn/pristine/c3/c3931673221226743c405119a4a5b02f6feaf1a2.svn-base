package m.w.frs.mgserver.module;

import java.util.Date;

import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.frs.mgserver.domain.TypeMaintain;
import m.w.frs.mgserver.service.TypeMaintainService;
import m.w.mg.sso.util.UserUtils;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/typemaintain")
@IocBean
public class TypeMaintainModule {

	@Inject
	private TypeMaintainService typeMaintainService;

	/** 类别定制-编辑 */
	@At
	@GET
	@Ok("jsp:/page/type/typemaintain")
	public void maintain() {

	}

	@At({ "/save", "/save/?" })
	@GET
	@Ok("jsp:/page/type/save")
	public Object save(Long id) {
		if (id == null)
			return null;
		return typeMaintainService.fetch(id);

	}

	/** 单位-新增 */
	@At
	@POST
	public Object save(@Param("..") TypeMaintain addObject) {

		addObject.setEntertime(new Date());// 时间
		// addObject.setUserid(UserUtils.CurrentUser().getId());// 登录人id
		addObject.setUsername(UserUtils.CurrentUser().getUsername());// 登录人name

		if (addObject.getId() == null) {

			return typeMaintainService.insert(addObject, "操作成功", "操作失败");
		} else {

			return typeMaintainService.update(addObject, "操作成功", "操作失败");
		}

	}

	@At({ "/delete", "/delete/?" })
	@DELETE
	public Result delete(Long id) {
		return typeMaintainService.delete(id, "删除成功", "删除失败");
	}

	@At({ "/items" })
	public DataGrid items() {
		return typeMaintainService.datagrid();
	}

}
