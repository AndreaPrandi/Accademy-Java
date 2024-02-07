package it.betacom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.betacom.dao.UserDAO;
import it.betacom.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxAttempts = 3; 
		int attempts = 0;
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAttempts") != null) {
			attempts = (int) session.getAttribute("loginAttempts");
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password + " " + username);
		UserDAO cl = new UserDAO();
		User user = cl.getClientePerUsername(username);

		if (user != null && password.equals(user.getPassword())&& user.getStato().equals("A")) {
			session.setAttribute("username", username);
			session.setAttribute("loginAttempts", 0);
			response.sendRedirect("menu.jsp");

			return;
		} else  {
			
			attempts++;
			if (attempts >= maxAttempts) {
				session.setAttribute("loginAttempts", 0);
				User user2 = cl.getClientePerUsername(username);
				user2.setStato("D");
				cl.updateUser(user2);
				response.sendRedirect("Login.jsp"); 
				return;
			}
			session.setAttribute("loginAttempts", attempts);
			response.sendRedirect("Login.jsp");
			return;
		}
	}


/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}
