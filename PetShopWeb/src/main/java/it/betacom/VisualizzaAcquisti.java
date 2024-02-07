package it.betacom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.betacom.dao.AnimaleDAO;
import it.betacom.model.Animale;

/**
 * Servlet implementation class VisualizzaAcquisti
 */
@WebServlet("/Acquisti")
public class VisualizzaAcquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaAcquisti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String idClienteStr = request.getParameter("idCliente");
        if (idClienteStr != null) {
            int idCliente = Integer.parseInt(idClienteStr);

            AnimaleDAO animaleDAO = new AnimaleDAO();
            List<Animale> acquistiCliente = animaleDAO.trovaAnimaliCliente(idCliente);

            request.setAttribute("acquistiCliente", acquistiCliente);
            request.getRequestDispatcher("AcquistiCliente.jsp").forward(request, response);
        } else {
            response.sendRedirect("ListaClienti.jsp");
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
