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
 * It checks if session is active and session type is of admin type (hence admin is LOGged in)
 * and if it is admin type, it lets pass to servlet, else it will redirect to homepage or LOGin page based on session.
 * @author Yash
 *
 */
@WebFilter(urlPatterns = {"/admin.jsp","/AdminServlet","/DeleteServlet"})
public class AdminFilter implements Filter {
	static final Logger LOG = Logger.getLogger(AdminFilter.class);
    
	/**
     * Default constructor. 
     */
    public AdminFilter() {
        
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
		
		LOG.debug("Inside Admin Filter.");
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        res.setDateHeader("Expires", 0);
		
		HttpSession session=req.getSession(false); 
		if(session==null || session.getAttribute("user")==null) {
			LOG.debug("Session is not created, redirecting to LOGin page.");
			res.sendRedirect("index.ftl");
		}else {
			UserBean u = (UserBean)session.getAttribute("user");
			if(u.getType().equals("admin")) {
				LOG.debug("Session is active, and admin is verified, let him pass.");
				chain.doFilter(request, response);
			}else if(u.getType().equals("user")) {
				LOG.debug("Session is active, and user is trying to access AdminServlet, redirecting to Homepage.");
				res.sendRedirect("homepage.ftl");
			}else {
				LOG.error("Session is active, but its not admin or user. Redirecting to LOGin page.");
				res.sendRedirect("index.ftl");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
