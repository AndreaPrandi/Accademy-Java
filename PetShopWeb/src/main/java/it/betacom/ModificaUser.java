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
 * Servlet implementation class ModificaUser
 */
@WebServlet("/ModificaUser")
public class ModificaUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
			String username = request.getParameter("username");
			String nuovaEmail = request.getParameter("email");
			String nuovoTelefono = request.getParameter("telefono");

			// Otteniamo l'utente dal database utilizzando l'username
			UserDAO userDAO = new UserDAO();
			User utente = userDAO.getClientePerUsername(username);

			if (utente != null) {
				// Applichiamo le modifiche
				if (nuovaEmail != null && !nuovaEmail.isEmpty()) {
					utente.setEmail(nuovaEmail);
				}

				if (nuovoTelefono != null && !nuovoTelefono.isEmpty()) {
					utente.setCellulare(nuovoTelefono);
				}

				// Aggiorniamo l'utente nel database
				userDAO.updateUser(utente);

				// Reindirizziamo alla pagina dell'elenco utenti dopo la modifica
				response.sendRedirect("ListaUtenti.jsp");
			} else {
				// L'utente non è stato trovato, gestire l'errore di conseguenza
				response.getWriter().println("Utente non trovato!");
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
