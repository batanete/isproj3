package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AdminBeanLocal;
import ejb.GeneralBeanLocal;

/**
 * Servlet implementation class EditCourse
 */
@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String institutionalEmail,password;

	private Cookie usercookie,passcookie;

	private RequestDispatcher view;

	@EJB
	AdminBeanLocal ab;

	@EJB
	GeneralBeanLocal ejb;

	//a=admin,p=professor,s=student
	private static final String ALLOWED="a";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCourse() {
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

		if("AddProfessor".equals(opt)){
			String courseName = request.getParameter("professor_course_name");
			String institutionalEmail = request.getParameter("professor_institution_email");
			
			if(ab.changeCourseProfessor(courseName, institutionalEmail)){
				setOnLoadMessage(request,"the course's professor has been changed.");
			}
			else
				setOnLoadMessage(request,"the course's professor could not be changed.");
			
			view=request.getRequestDispatcher("admincourse.jsp");
			view.forward(request,response);
		}

		else if("AddStudent".equals(opt)){
			String courseName = request.getParameter("student_course_name");
			String institutionalEmail = request.getParameter("student_institution_email");
			
			if(ab.addStudentToCourse(courseName, institutionalEmail)){
				setOnLoadMessage(request,"student added to course.");
			}
			else{
				setOnLoadMessage(request,"student could not be added to course.");
			}
			
			view=request.getRequestDispatcher("admincourse.jsp");
			view.forward(request,response);
		}
		else if("ChangeCourseName".equals(opt)){
			String courseName = request.getParameter("change_course_name");
			String newName = request.getParameter("new_course_name");

			System.out.println(courseName + newName);
			
			if(ab.renameCourse(courseName, newName)){
				setOnLoadMessage(request,"course renamed.");
			}
			else{
				setOnLoadMessage(request,"course could not be renamed.");
			}
			
			view=request.getRequestDispatcher("admincourse.jsp");
			view.forward(request,response);
		}
		else if("RemoveStudent".equals(opt)){
			String courseName = request.getParameter("remove_student_course_name");
			String institutionalEmail = request.getParameter("remove_student_institutional_email");
			
			if(ab.removeStudentFromCourse(courseName, institutionalEmail)){
				setOnLoadMessage(request,"student removed from course.");
			}
			else{
				setOnLoadMessage(request,"student could not be removed from to course.");
			}
			
			view=request.getRequestDispatcher("admincourse.jsp");
			view.forward(request,response);
		}
		else{
			String courseName = request.getParameter("remove_course_name");
			
			if(ab.deleteCourse(courseName)){
				setOnLoadMessage(request,"course deleted.");
			}
			else{
				setOnLoadMessage(request,"course could not be deleted.");
			}
			
			view=request.getRequestDispatcher("admincourse.jsp");
			view.forward(request,response);
		}
	}

}
