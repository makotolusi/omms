package m.w.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m.w.frs.mgserver.domain.UserToken;
import m.w.frs.mgserver.service.UserTokenService;
import m.w.sys.util.Webs;

import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.UTF8JsonView;

@IocBean
public class AuthFilter implements ActionFilter {

	@Inject
	private UserTokenService userTokenService;

	@Override
	public View match(ActionContext arg0) {
		HttpServletRequest request = arg0.getRequest();
		HttpServletResponse response = arg0.getResponse();
		String token = PrivateUtil.decryptToken(request);
		request.setAttribute("token", token);
		if (StringUtils.isBlank(token)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return failView();
		}

		if (userTokenService == null) {
			Ioc ioc = Webs.ioc();
			userTokenService = ioc.get(UserTokenService.class,
					"userTokenService");
		}

		UserToken userToken = userTokenService.getByToken(token);
		if (userToken == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return failView();
		} else if (userToken.getStatus() == UserToken.Status.FORBIDDEN
				.getValue()) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return failView();
		}
		userToken.setReqCount(userToken.getReqCount() + 1);
		userToken.setCreateDate(new Date());
		userTokenService.update(userToken);
		request.setAttribute("resolution", userToken.getResolution());
		return null;
	}

	private View failView() {
		// ServerRedirectView v1=new ServerRedirectView("/index.jsp");
		UTF8JsonView v = new org.nutz.mvc.view.UTF8JsonView(new JsonFormat());
		v.setData("forbidden");// json 格式
		return v;
	}

}
