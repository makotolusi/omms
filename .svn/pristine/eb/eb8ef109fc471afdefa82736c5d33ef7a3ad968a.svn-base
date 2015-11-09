package m.w.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

public class CharacterEncodingFilter implements Filter{
    private String encoding;
    private boolean forceEncoding = true;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        if (request instanceof HttpServletRequest)
            doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
        else
            chain.doFilter(request, response);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String reqenc = request.getCharacterEncoding();
        if(!Strings.isBlank(encoding) && (Strings.isBlank(reqenc) || forceEncoding)){
        	request.setCharacterEncoding(encoding);
        }
        String resenc = response.getCharacterEncoding();
        if(!Strings.isBlank(encoding) && (Strings.isBlank(resenc) || forceEncoding)){
        	response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        forceEncoding = Lang.parseBoolean(config.getInitParameter("forceEncoding"));
    }
}
