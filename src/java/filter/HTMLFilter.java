
package filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public final class HTMLFilter implements Filter{

    private FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest hreq=(HttpServletRequest)request;
        
        String encoding=hreq.getCharacterEncoding();
        
        if(!(encoding.equals("UTF-8"))){
            request.setCharacterEncoding("UTF-8");
            Logger.getLogger(HTMLFilter.class.getName()).log(Level.INFO, "Changed encoding from {0}", encoding);
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
