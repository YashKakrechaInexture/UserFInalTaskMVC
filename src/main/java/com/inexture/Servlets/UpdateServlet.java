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
 * It updates the user details from given inputs from register jsp page.
 * @author Yash
 *
 */
@MultipartConfig
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOG = Logger.getLogger(UpdateServlet.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("Inside Update Servlet.");
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String suid = request.getParameter("uid");
		int uid = Integer.parseInt(suid);
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String sphone = request.getParameter("phone");
		String email = request.getParameter("email");
		long phone = 0;
		try {
			phone = Integer.parseInt(sphone);
			
		}catch(Exception e) {
			out.print("Phone is not a number.");
			
			LOG.warn("Phone is not a number");
		}
		String password = "";
		String gender = request.getParameter("gender");
		String birthdate = request.getParameter("birthdate");
		String hobbyArray[] = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbyArray);
		Part filePart = null;
		InputStream inputStream = null;
		String fileName = null;
		try {
			filePart = request.getPart("profilepic");
			if(filePart != null && filePart.getSize()!=0) {
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
		
		RequestDispatcher rd = null;
		
		UserBean u = new UserBean(fname,lname,email,phone,password,gender,birthdate,hobby,que1,que2,que3,address,inputStream);
		
		UserInterface us = new UserService();
		
		if(!Validation.validate(u)) {
			LOG.debug("Validation failed.");
			out.print("Input Field is empty");
			rd = request.getRequestDispatcher("EditServlet?email="+email);
			rd.include(request, response);
		}else {
			
			LOG.debug("Validation passed, updating User data.");
			
			HttpSession session=request.getSession(false);  
			
			if(session!=null) {
				
				LOG.debug("Session is not null, updating user.");
				
				u.setUid(uid);
				
				us.updateUser(u,fileName);
				
				out.print("Updated Successfully");
				
				UserBean user = (UserBean)session.getAttribute("user");
				
				if(user.getType().equals("user")) {
					LOG.debug("Session is active and type is user. Redirecting to homepage.");
					response.sendRedirect("homepage.jsp");
		        }else if(user.getType().equals("admin")){
		        	LOG.debug("Session is active and type is admin. Redirecting to admin servlet.");
					response.sendRedirect("AdminServlet");
		        }else {
		        	LOG.error("Session is active but no user or admin found.");
		        }
			}else {
				LOG.warn("Session is null, redirecting to login page.");
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
