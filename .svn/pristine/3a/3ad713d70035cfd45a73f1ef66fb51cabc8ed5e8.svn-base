package m.w.frs.mgserver.module.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;

import m.w.core.dto.Result;
import m.w.frs.mgserver.domain.UserToken;
import m.w.frs.mgserver.service.UserTokenService;
import m.w.frs.mgserver.service.snapup.OrderService;
import m.w.security.PrivateUtil;

@At("/ApCommonServices/OrderServices/")
@IocBean
public class OrderServices {

	@Inject
	private OrderService orderService;

	@Inject
	private UserTokenService userTokenService;
	
	private static final Log log = Logs.getLog(OrderServices.class);

	/** 得到用户订单列表 */
	@At
	@GET
	public Result getUserOrderByToken(HttpServletRequest request) {
		String resolution = null;
		if (request.getAttribute("resolution") != null) {
			resolution = request.getAttribute("resolution").toString();
		}
		Result r = Result.err();
		String token = PrivateUtil.decryptToken(request);
		if (StringUtils.isBlank(token)) {
			// response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return r;
		}
		try {
			UserToken userTokenTmp = userTokenService.getByToken(token);
			return orderService.getUserOrderByToken(userTokenTmp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.err("确认订单失败");
		}
	}

}
