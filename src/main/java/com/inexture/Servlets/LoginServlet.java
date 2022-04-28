package com.inexture.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserInterface;
import com.inexture.Services.UserService;


/**
 * It fetches the email and password from the database table and checks if this is user or admin,
 * and redirects them to their respective page.
 * @author Yash
 *
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(LoginServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Inside LoginServlet");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		LOG.info("Got email and password from login page");
		
		UserInterface ls = new UserService();
		UserBean u = ls.checkUser(email,password);
		
		LOG.debug("Inside LoginServlet : Email and password has been checked.");
		
		RequestDispatcher rd = null;
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		if(u != null) {
			
			HttpSession session=request.getSession();  
			session.setAttribute("email",u.getEmail());  
			session.setAttribute("user", u);
			
			LOG.debug("Session created and UserBean set to attribute.");
			
			if(u.getType().equals("admin")) {
				
				LOG.info("User is admin, redirecting to admin page.");
				session.setAttribute("admin","true");
				response.sendRedirect("AdminServlet");
				
			}else if (u.getType().equals("user")){
				
				LOG.info("User is normal user, redirecting to user home page.");
				response.sendRedirect("homepage.jsp");
				
			}else {
				
				LOG.error("User found but its not user or admin");
				response.sendRedirect("index.jsp");
				
			}
			
		}else{
			
			LOG.info("No user found with given email and password, redirecting to login page.");
			out.print("Enter Correct Details");
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
