package m.w.sys.module;

import java.util.List;

import m.w.App;
import m.w.BasicModule;
import m.w.core.dto.DataGrid;
import m.w.core.dto.Result;
import m.w.frs.mgserver.service.PieceMaintainService;
import m.w.mg.sso.util.UserPassUtil;
import m.w.sys.domain.User;
import m.w.sys.service.UserService;
import m.w.sys.util.Users;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.DELETE;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

@At("/sys/user")
@IocBean
public class UserModule extends BasicModule {
	// private static final String PS = "/sys/user";
	private static final String PS = "/page/authuser";
	private static final String NM = "用户";

	@Inject
	private UserService userService;

	@Inject
	private PieceMaintainService pieceMaintainService;

	@At
	@Ok("jsp:" + PS + "/index")
	public void index() {

	}

	@At
	public Object tree() {
		return Users.tree();
	}

	@At
	public DataGrid items() {
		DataGrid dg = userService.datagrid();
		List<User> us = (List<User>) dg.getRows();
		for (User u : us) {
			u.setRoleEditPieces(pieceMaintainService.getTextbyIds(u
					.getRoleEditPieces()));
		}
		return dg;
	}

	@At
	@GET
	@Ok("jsp:" + PS + "/add")
	public void add() {

	}

	@At
	@POST
	public Result add(@Param("..") User user) {
		User u = userService.fetch(Cnd.limit().and("username", "=",
				user.getUsername()));
		if (u != null) {
			return Result.err("用户已经存在,请在名字后面加数字");
		}
		user.setPassword(UserPassUtil.encryptyEC("1"));
		return userService.insert(user, NM + "新建成功", NM + "新建失败");
	}

	@At("/update/?")
	@GET
	@Ok("jsp:" + PS + "/update")
	public Object update(Long id) {
		return userService.view(id);
	}

	@At
	@POST
	public Result update(@Param("..") User user) {
		return userService.update(user, NM + "更新成功", NM + "更新失败");
	}

	@At("/delete/?")
	@DELETE
	public Result delete(Long id) {
		return userService.delete(id, NM + "删除成功", NM + "删除失败");
	}

	@At("/view/?")
	@GET
	@Ok("jsp:" + PS + "/view")
	public Object view(Long id) {
		return userService.view(id);
	}

	@At
	@GET
	@Ok("jsp:" + PS + "/query")
	public void query() {

	}

	/**
	 * 更改密码
	 * 
	 * @param user
	 * @return
	 */
	@At
	@POST
	public Result chpwd(String oldPassword, String newPassword) {
		if (App.isRoot()) {
			boolean success = App.resetRoot(oldPassword, newPassword);
			return success ? Result.ok("密码修改成功") : Result.err("密码修改失败");
		}
		oldPassword = UserPassUtil.encryptyEC(oldPassword);
		newPassword = UserPassUtil.encryptyEC(newPassword);

		return userService.chpwd(oldPassword, newPassword);
	}

	// =========================================================================
	@At("/role/?")
	@GET
	@Ok("jsp:/ex/sys/user/role")
	public Object role(Long id) {
		return userService.fetch(id);
	}

	@At("/roles/?")
	public Object roles(Long id) {
		return userService.fetchWithRoles(id).getRoles();
	}

	@At({ "/addRoles", "/addRoles/?" })
	@POST
	public Result addRoles(String username, @Param("roleNames") String roleNames) {
		return userService.addRoles(username, roleNames);
	}

	@At({ "/delRoles", "/delRoles/?" })
	@POST
	public Result delRoles(String username) {
		return userService.delRoles(username);
	}

	@At({ "/addPieces", "/addPieces/?" })
	@POST
	public Result addPieces(Long id, @Param("pieces") String roleNames) {
		return userService.savePieces(id, roleNames);
	}

	@At({ "/delPieces", "/delPieces/?" })
	@POST
	public Result delPieces(Long id) {
		return userService.savePieces(id,"");
	}

	@At
	@Ok("jsp:/sys/invoice/authuserinfo")
	public void auth() {

	}

}
