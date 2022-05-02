package com.inexture.Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inexture.Beans.UserBean;
import com.inexture.Services.UserInterface;
import com.inexture.Services.UserService;

@MultipartConfig
@Controller
public class FrontController {
	
	static final Logger LOG = Logger.getLogger(FrontController.class);
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword() {
		return "resetPassword";
	}
	
	@RequestMapping("/newPassword")
	public String newPassword() {
		return "newPassword";
	}
	
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
	
	@RequestMapping("/AuthEmailServlet")
	@ResponseBody
	public String AuthEmailServlet(@RequestParam("email") String email,HttpSession session) {
		LOG.debug("Inside Auth email servlet.");
		
		UserInterface aes = new UserService();
		if(!aes.checkEmail(email)) {
			LOG.info("Email exist in table.");
			return "<span style=\"color:red;\">Email Already Taken.</span>";
		}else {
			LOG.info("Email does not exist in table.");
			return "<span style=\"color:green;\">Email Available.</span>";
		}
	}
	
	@RequestMapping("/DeleteServlet")
	public String DeleteServlet(@RequestParam("uid") String suid,HttpSession session) {
		
		LOG.debug("Inside Delete Servlet.");
		
		int uid = Integer.parseInt(suid);
		
		LOG.debug("User deleting service calling.");
		
		UserInterface ds = new UserService();
		ds.deleteUser(uid);
		
		LOG.debug("User deleted, redirecting to admin servlet.");
		
		return "redirect:AdminServlet";
		
	}
	
	@RequestMapping("/EditServlet")
	public ModelAndView EditServlet(@RequestParam String email,HttpSession session) {
		LOG.debug("Inside Edit Servlet.");
		
		if(session != null) {
			
			LOG.debug("Session not null.");
			
			LOG.debug("Get email.");
			
			UserBean u = new UserBean(email);
			
			UserInterface es = new UserService();
			es.editProfile(u);
			
			LOG.debug("Setting user bean to request attribute.");
			
			ModelAndView model = new ModelAndView("register");
			model.addObject("user", u);
	        
	        LOG.debug("Redirecting to edit jsp page.");
			
	        return model;
		}else {
			LOG.debug("Session is null, redirecting to login page.");
			ModelAndView model = new ModelAndView("index");
			return model;
		}
	}
	
	@RequestMapping("/NewPasswordServlet")
	public String NewPasswordServlet(@RequestParam("email") String email,
									@RequestParam("password1") String password1,
									@RequestParam("password2") String password2,
									HttpSession session) {
		
		LOG.debug("Inside New Password Servlet.");
		
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
		
		if(password1==null || password2==null || password1.equals("") || password2.equals("")) {
//			out.print("<p>Password empty.</p>");
			return "resetPassword";
		}else {
			if(password1.equals(password2)) {
				
				LOG.debug("Password is same, reseting password.");
				
				UserInterface rps = new UserService();
				rps.resetPass(email, password1);
				
//				out.print("<p>Password changed.</p>");
				
				LOG.debug("Redirecting to login page.");
				return "index";
			}else {
				LOG.debug("Password not matched, redirecting to new password page.");
//				out.print("<p>Password not matched.</p>");
				return "newPassword";
			}
		}
	}

	@RequestMapping("/ResetPasswordServlet")
	public ModelAndView ResetPasswordServlet(@RequestParam String email,
									@RequestParam String birthdate,
									@RequestParam String que1,
									@RequestParam String que2,
									@RequestParam String que3,
									HttpSession session) {
		
		LOG.debug("Inside Reset Password Servlet.");
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		UserBean u = new UserBean(email,birthdate,que1,que2,que3);
		
		LOG.debug("Got data and set in userbean.");
		
		UserInterface fu = new UserService();
		
		if(fu.findUser(u)) {
			LOG.debug("User found, redirecting to new password page.");
			
			ModelAndView model = new ModelAndView("newPassword");
			
			model.addObject("email", email);
			
			return model;
		}else {
			LOG.debug("No user found, redirecting to reset password page.");
//			out.print("No user found");
			ModelAndView model = new ModelAndView("resetPassword");
			model.addObject("error", "No user found");
			return model;
		}
		
	}
	
	@PostMapping("/RegisterServlet")
	@ResponseBody
	public String RegisterServlet(@RequestParam("fname") String fname,
									@RequestParam("lname") String lname,
									@RequestParam("email") String email,
									@RequestParam("phone") String phone,
									@RequestParam String password1,
									@RequestParam String password2,
									@RequestParam String gender,
									@RequestParam String birthdate,
									@RequestParam String hobby,
									@RequestParam String que1,
									@RequestParam String que2,
									@RequestParam String que3,
									HttpSession session) {
		System.out.print(fname+lname+email+phone+password1+password2+gender+birthdate+hobby+que1+que2+que3);
		return fname;
	}
	
	@RequestMapping(path="/UpdateServlet",method=RequestMethod.GET)
	@ResponseBody
	public String UpdateServlet(@RequestParam String fname,
									@RequestParam String lname,
									@RequestParam String phone,
									@RequestParam String gender,
									@RequestParam String birthdate,
									@RequestParam String hobby,
									@RequestParam String que1,
									@RequestParam String que2,
									@RequestParam String que3,
									@RequestParam("inputstream") MultipartFile part,
									HttpSession session) {
		
		InputStream inputstream = null;
		try {
			inputstream = part.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.print(fname+lname+phone+gender+birthdate+hobby+que1+que2+que3+inputstream);
		return fname+lname+phone+gender+birthdate+hobby+que1+que2+que3+inputstream;
	}
}
