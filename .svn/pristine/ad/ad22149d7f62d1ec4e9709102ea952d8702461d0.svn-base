package m.w.sys.shiro;

import java.lang.reflect.Method;

import m.w.sys.util.Users;
import m.w.sys.util.Webs;

import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

/**
 * 在入口方法中应用Shiro注解来进行安全过滤
 * @author wendal
 *
 */
public class ShiroActionFilter implements ActionFilter {

    private static final Log log = Logs.get();
    
	public View match(final ActionContext actionContext) {
		try {
		    log.debugf("ShiroActionFilter 请求的路径为:", actionContext.getPath());
	    
//		    if(!Users.isLogged()){
//		        return new JspView("/login");
//		    }

			ShiroAnnotationsAuthorizingMethodInterceptor.defaultAuth.assertAuthorized(new MethodInvocation() {
				
				public Object proceed() throws Throwable {
					throw Lang.noImplement();
				}
				public Object getThis() {
					return actionContext.getModule();
				}
				public Method getMethod() {
					return actionContext.getMethod();
				}
				
				public Object[] getArguments() {
					return actionContext.getMethodArgs();
				}
			});
		} catch (AuthorizationException e) {
			return whenAuthFail(actionContext, e);
		}
		return null;
	}

	private View view;
	private View ajax;
	
	public ShiroActionFilter() {
		view = new ViewWrapper(new JspView("/error"), "权限验证失败，请与管理员联系");
		ajax = new ViewWrapper(new JspView("/ex/error"), "权限验证失败，请与管理员联系");
	}
	
	protected View whenAuthFail(ActionContext ctx, AuthorizationException e) {
		return Webs.isAjax() ? ajax : view;
	}
}


