package it.betacom;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.betacom.model.User;
import it.betacom.dao.UserDAO;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	UserDAO userDAO = new UserDAO();
        // Recupera i parametri inviati dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String cellulare = request.getParameter("cellulare");
        String dataDiNascita = request.getParameter("data_di_nascita");
       String ruolo= "G";
        String password = request.getParameter("password");
        String username = userDAO.createUsername(nome, cognome, dataDiNascita);
        // Crea un nuovo oggetto Cliente
        User user = new User();
        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);
        user.setCellulare(cellulare);
        user.setDataDiNascita(dataDiNascita);
        user.setUsername(username);
        user.setPassword(password);
        user.setRuolo(ruolo);
        user.setStato("A");
        // Utilizza il UserDAO per inserire il nuovo utente nel database
        
        userDAO.aggiungiCliente(user);

        // Reindirizza l'utente a una pagina di conferma registrazione
        response.sendRedirect("Login.jsp");
    }
}
