	package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String mainpage="http://localhost:8080/ISproj2-WEB/";

	@EJB
	GeneralBeanLocal ejbremote;
	


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String user,pass;
		char acc_type;



		if ((user=request.getParameter("institutional_email")) != null &&
				(pass=request.getParameter("password")) != null) {
			try{
				System.out.println(user + pass);
				acc_type=ejbremote.login(user, pass);
				
				//create cookies and resend to admin menu
				if(acc_type!='0'){

					Cookie userCookie = new Cookie("institutional_email", user);
					userCookie.setMaxAge(30); //Store cookie for 30 seconds
					response.addCookie(userCookie);

					Cookie passCookie = new Cookie("password", pass);
					passCookie.setMaxAge(30); //Store cookie for 30 seconds
					response.addCookie(passCookie);
					
					if(acc_type=='a')
						response.sendRedirect(response.encodeRedirectURL(mainpage + "AdminMenu"));
					else if(acc_type=='s')
						response.sendRedirect(response.encodeRedirectURL(mainpage + "StudentMenu"));
					else if(acc_type=='p')
						response.sendRedirect(response.encodeRedirectURL(mainpage + "ProfessorMenu"));
				}
				
				else{
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
					dispatcher.forward(request, response);
				}


			}
			catch(Exception e){
				e.printStackTrace();
				out.println("error!");
			}

		}


		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.forward(request, response);
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
