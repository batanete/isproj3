package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AdminBeanLocal;
import ejb.CreateAdminBeanRemote;
import ejb.GeneralBeanLocal;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String institutionalEmail,password;

	private Cookie usercookie,passcookie;

	private RequestDispatcher view;

	@EJB
	AdminBeanLocal ab;
	@EJB
	CreateAdminBeanRemote cab;

	@EJB
	GeneralBeanLocal ejb;

	//a=admin,p=professor,s=student
	private static final String ALLOWED="a";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	//gets cookie with the given name from the cookie list.
	//if cookie doesn't exist, return null
	private Cookie getCookie(HttpServletRequest request,String name) {
		Cookie[] cookies=request.getCookies();

		for(Cookie c:cookies){
			if(c.getName().equals(name))
				return c;
		}

		return null;
	}

	//validates access to servlet. if user isn't allowed to view content, we will simply redirect him
	//to the login screen. if he has just logged in, we will create cookies to maintain his session.
	private boolean validateAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/*institutionalEmail=request.getParameter("institutional_email");
							password=request.getParameter("password");*/

		//check if user has cookies with a logged session
		if(institutionalEmail==null || password==null){
			usercookie=getCookie(request,"institutional_email");
			passcookie=getCookie(request,"password");

			if(usercookie!=null && passcookie!=null){
				institutionalEmail=usercookie.getValue();
				password=passcookie.getValue();
			}
		}
		//create cookies if he just logged in
		else{
			usercookie=new Cookie("user",institutionalEmail);
			passcookie=new Cookie("pass",password);
			usercookie.setMaxAge(30);
			passcookie.setMaxAge(30);
		}

		if(institutionalEmail==null || password==null){
			response.sendRedirect(response.encodeRedirectURL("Login"));
			return false;
		}

		//user doesn't exist or isn't an allowed to view content
		if(ALLOWED.indexOf(ejb.login(institutionalEmail, password))==-1){
			response.setContentType("text/html");

			if(usercookie!=null && passcookie!=null){
				usercookie.setMaxAge(0);
				passcookie.setMaxAge(0);
				response.addCookie(usercookie);
				response.addCookie(passcookie);

			}
			response.sendRedirect(response.encodeRedirectURL("Login"));
			return false;
		}

		response.setContentType("text/html");
		response.addCookie(usercookie);
		response.addCookie(passcookie);

		request.setAttribute("institutional_email", institutionalEmail);
		request.setAttribute("password", password);

		return true;
	}

	//set message to be displayed when page is loaded
	private void setOnLoadMessage(HttpServletRequest request,String message){
		request.setAttribute("display_message", "true");
		request.setAttribute("message", message);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		if(!validateAccess(request,response)){
			return;
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!validateAccess(request,response)){
			return;
		}

		String opt = request.getParameter("opt_type");
		String institutionalEmail = request.getParameter("edit_institution_email");
		String fieldName = request.getParameter("field_name");
		String newValue = request.getParameter("new_value");
		
		if(ab.changeInfo(institutionalEmail, fieldName, newValue)){
			setOnLoadMessage(request,"user info changed.");
		}
		else{
			setOnLoadMessage(request,"user info could not be changed.");
		}
		
		view=request.getRequestDispatcher("adminuser.jsp");
		view.forward(request,response);
	}
}
