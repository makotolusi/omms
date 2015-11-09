package m.w.frs.mgserver.module.rest;

import javax.servlet.http.HttpServletRequest;

import m.w.core.dto.Result;
import m.w.frs.mgserver.domain.UserToken;
import m.w.frs.mgserver.service.UserTokenService;
import m.w.security.PrivateUtil;
import m.w.sys.domain.CommerceUser;
import m.w.sys.service.CommerceUserService;
import m.w.sys.service.PicHandlerService;
import m.w.sys.util.MirrorUtils;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.POST;

@At("/ApCommonServices/CommerceUserServices/")
@IocBean
public class CommerceUserServices {

	@Inject
	private CommerceUserService commerceUserService;

	@Inject
	private UserTokenService userTokenService;


	@Inject
	private PicHandlerService picHandlerService;
	
	private static final Log log = Logs.getLog(CommerceUserServices.class);

	/** 得到用户订单列表 */
	@At
	@GET
	public Result login(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		String phoneNum = request.getParameter("phoneNum");
		if (StringUtils.isBlank(token) || StringUtils.isBlank(phoneNum)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = new CommerceUser();
			user.setPhoneNum(phoneNum);
			user=commerceUserService.login(userTokenTmp, user);
			if (user!=null) {
				r.setSuccess(true);
				userTokenTmp.setCommerceUserId(user.getId());
				userTokenService.update(userTokenTmp);
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}

	@At
	@GET
	public Result getUserInfo(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		if (StringUtils.isBlank(token)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = commerceUserService.fetch(userTokenTmp.getCommerceUserId());
			if (user!=null) {
				r.setSuccess(true);
				String url= picHandlerService.getQiniuPrivateUrl(user.getImgUrl(),
							picHandlerService.getPicConfig(null, "rule2"));
			   
				user.setImgUrl(url);
				r.setData(MirrorUtils.convertBeanToJson(user));
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}

	@At
	@GET
	public Result updateUserName(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		String username = request.getParameter("username");
		if (StringUtils.isBlank(token)||StringUtils.isBlank(username)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = commerceUserService.fetch(userTokenTmp.getCommerceUserId());
			if (user!=null) {
				r.setSuccess(true);
				user.setUsername(username);
				commerceUserService.update(user);
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}
	@At
	@GET
	public Result updateGender(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		String gender = request.getParameter("gender");
		if (StringUtils.isBlank(token)||StringUtils.isBlank(gender)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = commerceUserService.fetch(userTokenTmp.getCommerceUserId());
			if (user!=null) {
				r.setSuccess(true);
				user.setGender(gender.equals("1")?true:false);
				commerceUserService.update(user);
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}
	
	@At
	@GET
	public Result updateHeadUrl(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		String url = request.getParameter("url");
		if (StringUtils.isBlank(token)||StringUtils.isBlank(url)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = commerceUserService.fetch(userTokenTmp.getCommerceUserId());
			if (user!=null) {
				r.setSuccess(true);
				user.setImgUrl(url);
				commerceUserService.update(user);
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}
	
	@At
	@POST
	public Result updateAddress(HttpServletRequest request) {
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		String address = request.getParameter("address");
		if (StringUtils.isBlank(token)||StringUtils.isBlank(address)) {
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			CommerceUser user = commerceUserService.fetch(userTokenTmp.getCommerceUserId());
			if (user!=null) {
				r.setSuccess(true);
				user.setAddr(address);
				commerceUserService.update(user);
				return r;
			} else {
				return r.err();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("失败");
		}
	}
}
