package m.w;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import m.w.frs.mg.ImsRoles;
import m.w.frs.mg.util.Infos;
import m.w.sys.shiro.OpenIdAuthenticationToken;
import m.w.sys.util.Acls;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

@IocBean
public class HomeModule extends BasicModule {
    private static final Log log = Logs.get();
    private static final long ONE_HOUR = 3600000L;
    private static final String ATTR_MAC = "openid_mac";
    private static final String ATTR_ALIAS = "openid_alias";
    
    @Inject
    private OpenIdManager openIdManager;

    @At
    @Fail(">>:/login")
    public View index() {
        return new JspView(App.getHomepage());
    }
    
    @At
    @Fail(">>:/login")
    public View audit() {
        return new JspView(App.getAuditpage());
    }

    @At
    @Filters
    public View login(HttpServletRequest request, HttpServletResponse response) {
    	if(App.isOpenId()){
			Endpoint endpoint =  openIdManager.lookupEndpoint(App.getOpenIdEndpoint());
            Association association = openIdManager.lookupAssociation(endpoint);
            request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
            request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
            String url = openIdManager.getAuthenticationUrl(endpoint, association);
            return new ServerRedirectView(url);
    	}else{
    		return new JspView("/login");
    	}
    }
    @At
    @Filters
    public View openid(HttpServletRequest request, HttpServletResponse response) {
        // check sign on result from ztjs:
        checkNonce(param("openid.response_nonce"));
        // get authentication:
        byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
        String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
        Authentication authentication = openIdManager.getAuthentication(request, mac_key, alias);
        String fullname = authentication.getFullname();
        AuthenticationToken token = new OpenIdAuthenticationToken(fullname);
        SecurityUtils.getSubject().login(token);
    	return new ServerRedirectView("/index");
    }
    private void checkNonce(String nonce) {
        // check response_nonce to prevent replay-attack:
        if (nonce==null || nonce.length()<20)
            throw new OpenIdException("Verify failed.");
        // make sure the time of server is correct:
        long nonceTime = getNonceTime(nonce);
        long diff = Math.abs(System.currentTimeMillis() - nonceTime);
        if (diff > ONE_HOUR)
            throw new OpenIdException("Bad nonce time.");

    }
    private long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(nonce.substring(0, 19) + "+0000").getTime();
        }
        catch(ParseException e) {
            throw new OpenIdException("Bad nonce time.");
        }
    }
    @At
    @Ok(">>:/login")
    public void logout(HttpSession session) {
        SecurityUtils.getSubject().logout();
    }

    @At
    @Fail("http:404")
    @Filters
    @POST
    public View dologin(@Param("username") String username,
                      @Param("password") String password,
                      @Param("rememberMe") boolean rememberMe,
                      HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        if (Strings.isBlank(username) || Strings.isBlank(password)) {
            err("请输入用户名与口令");
            return new JspView("/login");
        }

        attr("username", username);
        attr("rememberMe", rememberMe);

        AuthenticationToken token = new UsernamePasswordToken(username,
                                                              password,
                                                              rememberMe,
                                                              request.getRemoteHost());
        try {
            SecurityUtils.getSubject().login(token);
            
            doAfterLogin();
            
            if(!Strings.isBlank(referer) && referer.indexOf("/login")<0 && referer.indexOf("/dologin")<0){
                return new ServerRedirectView(referer);
            }else{
//            	if(Acls.hasRole(ImsRoles.EVA_AUDIT) && !Acls.hasRole(ImsRoles.SYS_ADMIN)){
//            		return new ServerRedirectView("/audit");// 将审批画面独立出来
//            	}
                return new ServerRedirectView("/index");// 将审批画面独立出来,此处加if else
            }
        }
        catch (LockedAccountException e) {
            log.info("验证失败:" + e.getMessage());
            info("验证失败:" + e.getMessage());
            return new JspView("/login");
        }
        catch (AuthenticationException e) {
            log.info("验证失败:" + e.getMessage());
            info("验证失败");
            return new JspView("/login");
        }
        catch (Exception e) {
            log.error("登录失败", e);
            info("登录失败");
            return new JspView("/login");
        }
    }

    @At
    @Ok("jsp:/error")
    public void error() {

    }
    
    private void doAfterLogin(){
//    	Infos.userInfo();
    }
}