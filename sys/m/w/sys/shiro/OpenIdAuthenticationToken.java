package m.w.sys.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class OpenIdAuthenticationToken implements HostAuthenticationToken, RememberMeAuthenticationToken{
	private static final long serialVersionUID = -7865942950668478677L;
    protected String host;
    protected boolean rememberMe;
    protected String username;
    
    public static final String NO_CREDENTIALS = "OpenID does not require authentication credentials.";
    
    public OpenIdAuthenticationToken(String username){
    	this.username = username;
    }
    
    public OpenIdAuthenticationToken(String username, boolean rememberMe, String host){
    	this.username = username;
    	this.rememberMe = rememberMe;
    	this.host = host;
    }

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public Object getCredentials() {
		return NO_CREDENTIALS;
	}

	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}

	@Override
	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
