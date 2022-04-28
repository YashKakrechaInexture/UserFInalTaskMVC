package com.inexture.Servlets;

import java.io.IOException;

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
 * It fetches all the data of user based on email address when user or admin clicks edit profile.
 * @author Yash
 *
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(EditServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);  
		
		if(session==null || session.getAttribute("email")==null) {
			response.sendRedirect("homepage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Inside Edit Servlet.");
		
		HttpSession session = request.getSession(false);  
		
		if(session != null) {
			
			LOG.debug("Session not null.");
			
			String email = request.getParameter("email");
			
			LOG.debug("Get email.");
			
			UserBean u = new UserBean(email);
			
			UserInterface es = new UserService();
			es.editProfile(u);
			
			LOG.debug("Setting user bean to request attribute.");
			
	        request.setAttribute("user", u);
	        
	        LOG.debug("Redirecting to edit jsp page.");
			
	        request.getRequestDispatcher("register.jsp").include(request, response);
	        
		}else {
			LOG.debug("Session is null, redirecting to login page.");
			
			response.sendRedirect("index.html");
		}
	}

}
