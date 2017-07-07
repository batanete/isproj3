package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.GeneralBeanLocal;

/**
 * Servlet implementation class TransferMaterial
 */
@WebServlet("/TransferMaterialStudent")
public class TransferMaterialStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private String institutionalEmail,password;

	private Cookie usercookie,passcookie;

	private RequestDispatcher view;

	//a=admin,p=professor,s=student
	private static final String ALLOWED="aps";

	@EJB
	GeneralBeanLocal ejb;

	//type of account logged in
	private char acc_type;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferMaterialStudent() {
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

		acc_type=ejb.login(institutionalEmail, password);
		
		//user doesn't exist or isn't an allowed to view content
		if(ALLOWED.indexOf(acc_type)==-1){
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!validateAccess(request,response)){
			return;
		}

		String courseName = request.getParameter("course_name");
		String materialName = request.getParameter("material_name");

		byte[] arb = ejb.getMaterialData(materialName, courseName);

		if(arb!=null){

			// Make sure to show the download dialog
			response.setHeader("Content-disposition","attachment; filename="+materialName);

			OutputStream out = response.getOutputStream();

			for(byte b:arb){
				out.write(b);
			}

			out.flush();
		}
		else{
			setOnLoadMessage(request,"material could not be downloaded.does it exist?");
		}

		if(acc_type=='p')
			view = request.getRequestDispatcher("professormaterial.jsp");
		else if(acc_type=='a')
			view = request.getRequestDispatcher("adminmaterial.jsp");
		else if(acc_type=='s')
			view = request.getRequestDispatcher("studentmaterial.jsp");
		view.forward(request, response);
	}

}
