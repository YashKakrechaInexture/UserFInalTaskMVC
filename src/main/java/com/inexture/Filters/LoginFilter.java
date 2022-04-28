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

import com.inexture.Beans.UserBean;

/**
 * This filter checks if session is null or not on the login page.
 * If session is active then user cannot go to login page and redirects to homepage.
 * @author Yash
 *
 */
@WebFilter(urlPatterns = {"/index.jsp"})
public class LoginFilter implements Filter {
	/**
	 * Logger
	 */
	static final Logger LOG = Logger.getLogger(UserFilter.class);
    
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        
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
		
		LOG.debug("Inside Login Filter.");

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
		
		HttpSession session=req.getSession(false);  
		if(session==null || session.getAttribute("user")==null) {
			LOG.debug("Session is not active, chaining to login page.");
			chain.doFilter(request, response);
		}else {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        res.setDateHeader("Expires", 0);
	        UserBean u = (UserBean)session.getAttribute("user");
	        if(u!=null) {
	        	if( ("user").equals(u.getType()) ) {
	        		LOG.debug("Session is active and its type user, redirecting to homepage.jsp.");
	        		res.sendRedirect("homepage.ftl");
	        	}else if( ("admin").equals(u.getType()) ) {
	        		LOG.debug("Session is active and its type admin, redirecting to AdminServlet.");
	        		res.sendRedirect("AdminServlet");
	        	}else {
	        		LOG.error("Session is active but not user or admin found.");
		        	chain.doFilter(request, response);
	        	}
	        }else {
	        	LOG.error("In if condition session was active but in else condition session is null.");
	        	chain.doFilter(request, response);
	        }
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
