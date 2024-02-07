package it.betacom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.betacom.dao.UserDAO;
import it.betacom.model.User;

/**
 * Servlet implementation class CambiaStatoUtente
 */
@WebServlet("/CambiaStatoUtente")
public class CambiaStatoUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaStatoUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String userId = request.getParameter("userId");
	        String newStatus = request.getParameter("newStatus");

	        UserDAO userDAO = new UserDAO();
	        User user = userDAO.getClientePerUsername(userId);

	        if (user != null) {
	            user.setStato(newStatus);
	            userDAO.aggiornaCliente(user);
	        }

	        // Redireziona alla pagina ListaUtenti.jsp dopo l'aggiornamento dello stato
	        response.sendRedirect(request.getContextPath() + "/ListaUtenti.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
