package com.inexture.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * It checks if session is active or not. If session is not active and user is trying to
 * go to the homepage then it will redirect to the login page.
 * @author Yash
 *
 */
@WebFilter(urlPatterns = {"/homepage","/EditServlet","/UpdateServlet","/AdminServlet"})
public class UserFilter implements Filter {
	static final Logger LOG = Logger.getLogger(UserFilter.class);
    /**
     * Default constructor. 
     */
    public UserFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		LOG.debug("Inside User Login Filter.");

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
		
		HttpSession session=req.getSession(false);  
		if(session==null || session.getAttribute("user")==null) {
			LOG.debug("Session is not active, redirecting to login page.");
			res.sendRedirect("index");
		}else {
			LOG.debug("Session is active, let him pass.");
	        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        res.setDateHeader("Expires", 0);
	        chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
