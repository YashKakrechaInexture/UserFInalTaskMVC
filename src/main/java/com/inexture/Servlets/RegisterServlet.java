package com.inexture.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.inexture.Beans.AddressBean;
import com.inexture.Beans.UserBean;
import com.inexture.Services.UserInterface;
import com.inexture.Services.UserService;
import com.inexture.Utilities.Validation;

/**
 * It registers the new user after checking the validation & redirects them to admin/login page based on session.
 * @author Yash
 *
 */
@MultipartConfig
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(RegisterServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Inside Register Servlet.");
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String sphone = request.getParameter("phone");
		long phone = 0;
		try {
			phone = Integer.parseInt(sphone);
		}catch(Exception e) {
			out.print("Phone is not a number.");
			
			LOG.warn("Phone is not a number.");
		}
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String gender = request.getParameter("gender");
		String birthdate = request.getParameter("birthdate");
		String hobbyArray[] = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbyArray);
		Part filePart = null;
		InputStream inputStream = null;
		String fileName = null;
		try {
			filePart = request.getPart("profilepic");
			if(null!=filePart && filePart.getSize()!=0) {
				String fileurl = filePart.getSubmittedFileName();
				Path path = fileurl==null ? null : Paths.get(fileurl);
				Path filen = path==null? null : path.getFileName();
				fileName = path==null? null : filen.toString();
			}
			if(fileName!=null && !fileName.equals("")){
				inputStream = filePart.getInputStream();
			}
		}catch(Exception e) {
			LOG.error("Something went wrong! Exception : {}",e);
		}
		
		String que1 = request.getParameter("que1");
		String que2 = request.getParameter("que2");
		String que3 = request.getParameter("que3");
		
		String home[] = request.getParameterValues("home");
		String city[] = request.getParameterValues("city");
		String state[] = request.getParameterValues("state");
		String country[] = request.getParameterValues("country");
		String pincode[] = request.getParameterValues("pincode");
		
		ArrayList<AddressBean> address = new ArrayList<AddressBean>();
		
		for(int i=0 ; i<home.length ; i++) {
			AddressBean a = new AddressBean(home[i],city[i],state[i],country[i],pincode[i]);
			address.add(a);
		}
		
		LOG.debug("Got all the data from register page.");
		
		RequestDispatcher rd = null;
		
		UserBean u = new UserBean(fname,lname,email,phone,password1,gender,birthdate,hobby,que1,que2,que3,address,inputStream);
		
		UserInterface rs = new UserService();
		
		if(fileName==null || fileName.equals("")){

			LOG.debug("Image empty.");
			request.setAttribute("failuser", u);
			out.print("<p>Image is empty.</p>");
			rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
			
		}else if(!Validation.validate(u)) {
			
			LOG.debug("Validation failed.");
			request.setAttribute("failuser", u);
			out.print("<p>Input Field is empty or too large or type mismatch.</p>");
			rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
			
		}else if(!password1.equals(password2)) {
			
			LOG.debug("Password not matched.");
			request.setAttribute("failuser", u);
			out.print("<p>Password not matched</p>");
			rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
			
		}else {	
			
			LOG.debug("Validation passed, creating user.");
			
			HttpSession session=request.getSession(false);  
			
			rs.registerUser(u);
			
			LOG.debug("User created.");
			
			if(session!=null && session.getAttribute("user")!=null){
				
				LOG.debug("Session is not null");
				
				UserBean user = (UserBean)session.getAttribute("user");
				if(user.getType().equals("admin")) {
					LOG.debug("Admin is true, redirecting to admin servlet.");
					response.sendRedirect("AdminServlet");
				}else if(user.getType().equals("user")) {
					LOG.debug("User session is active, redirecting to homepage.");
					response.sendRedirect("homepage.jsp");
				}else {
					LOG.error("Session is active but not user or admin found.");
					response.sendRedirect("register.jsp");
				}
			}else {
				LOG.debug("Session is null, redirecting to login page.");
				response.sendRedirect("index.jsp");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
