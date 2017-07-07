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
 * Servlet implementation class CreateCourse
 */
@WebServlet("/CreateCourse")
public class CreateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String institutionalEmail,password;

	private Cookie usercookie,passcookie;

	private RequestDispatcher view;

	@EJB
	GeneralBeanLocal ejb;

	@EJB
	AdminBeanLocal ab;

	//a=admin,p=professor,s=student
	private static final String ALLOWED="a";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCourse() {
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

		String opt = request.getParameter("opt_type");
		String name = request.getParameter("name");
		String institutionalEmail = request.getParameter("institutional_email");
		String alternativeEmail = request.getParameter("alternative_email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String birthDateParameter = request.getParameter("birth_date");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date birthDate = null;
		try {
			birthDate = sdf.parse(birthDateParameter);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(opt);

		if("Professor".equals(opt)){
			String internalnumber = request.getParameter("internal_number");
			String category = request.getParameter("category");
			String internalPhone = request.getParameter("internal_phone");
			String office = request.getParameter("office");
			Double salary = Double.parseDouble(request.getParameter("salary"));
			if(ab.createProfessor(name, birthDate, address,
					institutionalEmail, alternativeEmail, phone,
					password, internalnumber, category, office, internalPhone, salary)){
				setOnLoadMessage(request,"professor created.");
			}
			else
				setOnLoadMessage(request,"professor could not be created.");

			view=request.getRequestDispatcher("adminuser.jsp");
			view.forward(request,response);
		}

		else if("Student".equals(opt)){
			String internalNumber =  request.getParameter("internal_number");
			int enrollementYear = Integer.parseInt(request.getParameter("enrollement_year"));
			if(ab.createStudent(name, birthDate, address, institutionalEmail,
					alternativeEmail, phone, password,
					internalNumber, enrollementYear)){
				setOnLoadMessage(request,"student created.");
			}
			else
				setOnLoadMessage(request,"student could not be created.");
			view=request.getRequestDispatcher("adminuser.jsp");
			view.forward(request,response);
		}
		else{
			response.sendRedirect("AdminMenu");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!validateAccess(request,response)){
			return;
		}

		String courseName = request.getParameter("course_name");
		
		if(ab.createCourse(courseName)){
			setOnLoadMessage(request,"course created");
		}
		else
			setOnLoadMessage(request,"course could not be created");
		
		view=request.getRequestDispatcher("adminuser.jsp");
		view.forward(request,response);
	}

}
