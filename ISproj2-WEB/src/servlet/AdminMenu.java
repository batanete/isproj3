package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ejb.*;


/**
 * Servlet implementation class AdminMenu
 */
@WebServlet("/AdminMenu")
public class AdminMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GeneralBeanLocal ejb;

	@EJB
	private AdminBeanLocal adminejb;



	private String institutionalEmail,password;

	private Cookie usercookie,passcookie;

	private RequestDispatcher view;

	//a=admin,p=professor,s=student
	private static final String ALLOWED="a";

	private static final String mainpage="localhost:8080/ISproj2-WEB/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMenu() {
		super();
	}


	//create new account
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		String name=request.getParameter("name");
		String email=request.getParameter("institutional_email_create");
		String password=request.getParameter("password_create");
		String birthDateStr=request.getParameter("birth_date");

		String alternativeEmail=request.getParameter("alternative_email");
		String address=request.getParameter("address");
		String telephone=request.getParameter("telephone");

		String accType=request.getParameter("account_type");

		Date dateBirth=null;
		try{
			//parse date inserted
			dateBirth=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
					.parse(birthDateStr);

		}
		catch(ParseException e){
			request.setAttribute("message", "Error! Please give a date with DD/MM/YYYY format.");
			view=request.getRequestDispatcher("adminmenu.jsp");
			view.forward(request,response);
			return;
		}

		//create new professor
		if(accType.equals("professor")){
			String internalNumber=request.getParameter("internal_number");
			String office=request.getParameter("office");
			String salaryStr=request.getParameter("salary");
			String category=request.getParameter("category");
			String internalPhone=request.getParameter("internal_phone");

			double salary=0.0;

			try{
				salary=Double.parseDouble(salaryStr);
			}
			catch(Exception e){
				request.setAttribute("message", "Error! Salary must be a real number!");
				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
				return;
			}

			//successfully created
			if(adminejb.createProfessor(name, dateBirth, address, email, alternativeEmail, telephone, password,
					internalNumber, category, office, internalPhone, salary)){
				request.setAttribute("message", "account "+email+" created successfully!");

				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
				return;
			}
			//teacher with the given email already existed(or other error)
			else{
				request.setAttribute("message", "Error creating account. Probably already exists.");
				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
			}

		}
		//create new student
		else{
			String number=request.getParameter("number");
			String enrolmentYearStr=request.getParameter("enrolment_year");
			int enrolmentYear=0;
			try{
				enrolmentYear=Integer.parseInt(enrolmentYearStr);
			}
			catch(Exception e){
				request.setAttribute("message", "Error! Year must be an integer!");
				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
				return;
			}

			//successfully created
			if(adminejb.createStudent(name, dateBirth, address, email,
					alternativeEmail, telephone, password,
					number,enrolmentYear)){
				request.setAttribute("message", "account "+email+" created successfully!");

				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
				return;
			}
			//student with the given email already existed(or other error)
			else{
				request.setAttribute("message", "Error creating account. Probably already exists.");
				view=request.getRequestDispatcher("adminmenu.jsp");
				view.forward(request,response);
			}
		}
	}

	//deletes a user's account
	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String mail=request.getParameter("institutional_email_delete");

		if(adminejb.deletePerson(mail)){
			request.setAttribute("message", "Account deleted successfully.");
			view=request.getRequestDispatcher("adminmenu.jsp");
			view.forward(request,response);
		}
		else{
			request.setAttribute("message", "Error deleting account. Probably doesn't exist.");
			view=request.getRequestDispatcher("adminmenu.jsp");
			view.forward(request,response);
		}

	}


	//redirect to EditAccount servlet
	private void editAccount(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException {

		//account to change
		String account=request.getParameter("institutional_email_edit");

		//field to change
		String field=request.getParameter("field_edit");

		//new value for the field
		String newvalue=request.getParameter("new_value_edit");

		if(adminejb.changeInfo(account, field, newvalue)){
			request.setAttribute("message", "Field changed successfully");
		}
		else{
			request.setAttribute("message", "Couldn't change the field. Does the account exist?");
		}

		view=request.getRequestDispatcher("adminmenu.jsp");
		view.forward(request,response);

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
		
		char loginres=ejb.login(institutionalEmail, password);

		//user doesn't exist or isn't an allowed to view content
		if(ALLOWED.indexOf(loginres)==-1){
			
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		if(!validateAccess(request,response)){
			return;
		}

		request.setAttribute("display_message", "true");

		//logouts from the account(kills the cookie)
		if(request.getParameter("logout_btn")!=null){
			usercookie.setMaxAge(0);
			passcookie.setMaxAge(0);
			response.addCookie(usercookie);
			response.addCookie(passcookie);
			response.sendRedirect(response.encodeRedirectURL("Login"));
		}


		//create new account
		else if(request.getParameter("name")!=null){
			createAccount(request,response);
		}

		//delete account
		else if(request.getParameter("institutional_email_delete")!=null){
			deleteAccount(request,response);
		}

		//edit account info(redirect to the edit menu)
		else if(request.getParameter("institutional_email_edit")!=null){
			editAccount(request,response);
		}

		//no operation requested. just show the menu.
		else{
			request.setAttribute("display_message", "false");
			view=request.getRequestDispatcher("adminmenu.jsp");
			view.forward(request,response);
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
