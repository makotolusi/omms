package m.w.frs.mgserver.module;

import java.util.Date;
import java.util.List;

import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.frs.mgserver.domain.PieceMaintain;
import m.w.frs.mgserver.service.PieceMaintainService;
import m.w.mg.sso.util.UserUtils;
import m.w.sys.service.PicHandlerService;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/piecemaintain")
@IocBean
public class PieceMaintainModule {

	@Inject
	private PieceMaintainService pieceMaintainService;

	@Inject
	private PicHandlerService picHandlerService;
	
	/** 类别定制-编辑 */
	@At
	@GET
	@Ok("jsp:/page/piece/piecemaintain")
	public void maintain() {

	}

	@At({ "/save", "/save/?" })
	@GET
	@Ok("jsp:/page/piece/save")
	public Object save(Long id) {
		if (id == null)
			return null;
		PieceMaintain pm = pieceMaintainService.fetch(id);
		String tokenUrl = picHandlerService.getQiniuPrivateUrl(pm.getImgUrl());
		pm.setImgUrl(tokenUrl);
		return pm;

	}

	/** 单位-新增 */
	@At
	@POST
	public Object save(@Param("..") PieceMaintain addObject) {

		addObject.setEntertime(new Date());// 时间
		// addObject.setUserid(UserUtils.CurrentUser().getId());// 登录人id
		addObject.setUsername(UserUtils.CurrentUser().getUsername());// 登录人name

		if (addObject.getId() == null) {

			return pieceMaintainService.insert(addObject, "操作成功", "操作失败");
		} else {

			return pieceMaintainService.update(addObject, "操作成功", "操作失败");
		}

	}

	@At({ "/delete", "/delete/?" })
	@DELETE
	public Result delete(Long id) {
		return pieceMaintainService.delete(id, "删除成功", "删除失败");
	}

	@At({ "/items" })
	public DataGrid items() {
		return pieceMaintainService.datagrid();
	}

	@At({ "/itemsByRole" })
	public DataGrid itemsByRole() {
		DataGrid dg = null;
		String roleEditePieces = UserUtils.CurrentUser().getRoleEditPieces();
		if (!StringUtils.isEmpty(roleEditePieces)
				&& roleEditePieces.indexOf(",") >= 0) {
			dg=pieceMaintainService.datagrid(Cnd.where("id", "in", roleEditePieces));
		} else {
			dg = pieceMaintainService.datagrid();
		}
		return dg;
	}

	@At({ "/addAuthPiece", "/addAuthPiece/?" })
	@GET
	@Ok("jsp:/page/piece/authuserpiece")
	public Object addAuthPiece(Long id) {

		PieceMaintain pm = pieceMaintainService.fetch(id);

		List<PieceMaintain> p = pieceMaintainService.query(Cnd.limit(), null);
		pm.setPlist(p);
		return pm;
	}

}
