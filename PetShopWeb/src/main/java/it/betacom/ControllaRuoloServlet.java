package it.betacom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.betacom.dao.UserDAO;
import it.betacom.model.User;

/**
 * Servlet implementation class ControllaRuoloServlet
 */
@WebServlet("/ControllaRuoloServlet")
public class ControllaRuoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllaRuoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getClientePerUsername(username);
            if (user != null && user.getRuolo().equals("A")) {
               
                response.sendRedirect("ListaUtenti.jsp");
            } else {
               
                response.sendRedirect("ListaUtentiGuest.jsp");
            }
        } else {
            // L'utente non è loggato, reindirizza alla pagina di login
            response.sendRedirect("login.jsp");
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
