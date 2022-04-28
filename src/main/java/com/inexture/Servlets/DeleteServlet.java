package com.inexture.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.inexture.Services.UserInterface;
import com.inexture.Services.UserService;

/**
 * It deletes the user based on user id clicked button by admin.
 * @author Yash
 *
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(DeleteServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Inside Delete Servlet.");
		
		String suid = request.getParameter("uid");
		int uid = Integer.parseInt(suid);
		
		LOG.debug("User deleting service calling.");
		
		UserInterface ds = new UserService();
		ds.deleteUser(uid);
		
		LOG.debug("User deleted, redirecting to admin servlet.");
		
		response.sendRedirect("AdminServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
