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
 * This is homepage filter. It checks for user session at homepage.
 * Only lets user pass to homepage, and not admin.
 * @author Yash
 *
 */
@WebFilter(urlPatterns = {"/homepage.jsp"})
public class HomepageFilter implements Filter {
	static final Logger LOG = Logger.getLogger(HomepageFilter.class);
    
    /**
     * Default constructor. 
     */
    public HomepageFilter() {
        
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
		
		LOG.debug("Inside Homepage Filter.");

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
		
		HttpSession session=req.getSession(false);  
		if(session==null || session.getAttribute("user")==null) {
			LOG.debug("Session is not created, redirecting to login page.");
			res.sendRedirect("index.ftl");
		}else {
			UserBean u = (UserBean)session.getAttribute("user");
			if(u.getType().equals("admin")) {
				LOG.debug("Session is active, and type is admin, redirecting to admin servlet.");
				res.sendRedirect("AdminServlet");
			}else {
				LOG.error("Session is active, and type user, let him pass.");
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
