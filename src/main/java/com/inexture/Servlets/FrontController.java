package com.inexture.Servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserInterface;
import com.inexture.Services.UserService;

@Controller
public class FrontController {
	
	static final Logger LOG = Logger.getLogger(FrontController.class);
	
	
	@RequestMapping("/LoginServlet")
	public String login(HttpServletRequest request,HttpSession session) {
		
		LOG.debug("Inside LoginServlet");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		
		LOG.info("Got email and password from login page");
		
		UserInterface ls = new UserService();
		UserBean u = ls.checkUser(email,password);
		
		LOG.debug("Inside LoginServlet : Email and password has been checked.");
		
//		PrintWriter out = response.getWriter();
		
//		response.setContentType("text/html");
		
		if(u != null) {
			
			session=request.getSession();  
			session.setAttribute("user", u);
			
			LOG.debug("Session created and UserBean set to attribute.");
			
			if(u.getType().equals("admin")) {
				
				LOG.info("User is admin, redirecting to admin page.");
				//response.sendRedirect("AdminServlet");
				return "redirect:AdminServlet";
				
			}else if (u.getType().equals("user")){
				
				LOG.info("User is normal user, redirecting to user home page.");
				//response.sendRedirect("homepage.jsp");
				return "homepage";
				
			}else {
				
				LOG.error("User found but its not user or admin");
				//response.sendRedirect("index.jsp");
				return "index";
			}
			
		}else{
			
			LOG.info("No user found with given email and password, redirecting to login page.");
//			out.print("Enter Correct Details");
//			rd = request.getRequestDispatcher("index.jsp");
//			rd.include(request, response);
			return "index";
		}
		
		//return "viewpage3"+email+" "+password;
	}
	
	@RequestMapping("/AdminServlet")
	public String admin(HttpServletRequest request,HttpSession session) {
		
		LOG.info("Inside Admin Servlet.");
		
		UserInterface as = new UserService();
		
		LOG.debug("Adding User list to request attribute.");
		
		request.setAttribute("data", as.showUsers("user"));
		
		LOG.debug("Redirecting to Admin page.");
		
		return "admin";
	}
	
	@RequestMapping("/LogoutServlet")
	public String logout(HttpServletRequest request,HttpSession session) {
		
		LOG.debug("Inside Logout Servlet.");
		
		session=request.getSession(false);  
		
		if(session!=null) {
			LOG.debug("Session is not null, invalidating it.");
			session.invalidate();
		}
		
		LOG.debug("Redirecting to login page.");
//		response.sendRedirect("index.jsp");
		return "index";
		
	}
}
