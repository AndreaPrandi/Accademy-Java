<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="it.betacom.model.User"%>
<%@ page import="java.util.List"%>
<%@ page import="it.betacom.dao.UserDAO"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Elenco Utenti</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <h1>Elenco Utenti</h1>
    <table>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Cellulare</th>
            <th>Data di Nascita</th>
            <th>Username</th>
            <th>Stato</th>
            <th>Ruolo</th>
            <th>Modifica Utente</th>
        </tr>
        <%
            UserDAO userDAO = new UserDAO();
            List<User> listaUtenti = userDAO.getTuttiClienti();
            for (User utente : listaUtenti) {
        %>
        <tr>
            <td><%= utente.getNome() %></td>
            <td><%= utente.getCognome() %></td>
            <td><%= utente.getEmail() %></td>
            <td><%= utente.getCellulare() %></td>
            <td><%= utente.getDataDiNascita() %></td>
            <td><%= utente.getUsername() %></td>
            <td>
                <form action="CambiaStatoUtente" method="post">
                    <input type="hidden" name="userId" value="<%= utente.getUsername() %>">
                    <input type="hidden" name="newStatus" value="<%= utente.getStato().equals("A") ? "D" : "A" %>">
                    <button type="submit">
                        <%= utente.getStato().equals("A") ? "Disabilita" : "Abilita" %>
                    </button>
                </form>
            </td>
            <td><%= utente.getRuolo() %></td>
            <td>
                <!-- Bottone per aprire il popup -->
                <button onclick="apriPopup('<%= utente.getUsername() %>', '<%= utente.getEmail() %>', '<%= utente.getCellulare() %>')">Modifica</button>
            </td>
        </tr>
        <% } %>
    </table>

    <!-- Overlay del popup -->
    <div class="popup-overlay" id="popupOverlay" style="display: none;"></div>

    <!-- Contenuto del popup -->
    <div class="popup-content" id="popupContent" style="display: none;">
        <h2>Modifica dati utente</h2>
        <form action="ModificaUser" method="post">
            <label for="username"></label>
            <input type="hidden" id="username" name="username" readonly>
            <br>
            <label for="email">Nuova Email:</label>
            <input type="text" id="email" name="email">
            <br>
            <label for="telefono">Nuovo Telefono:</label>
            <input type="text" id="telefono" name="telefono">
            <br>
            <input type="submit" value="Salva">
            <button onclick="chiudiPopup()">Annulla</button>
        </form>
    </div>

    <!-- JavaScript per gestire il popup -->
    <script>
        function apriPopup(username, email, telefono) {
            document.getElementById("popupOverlay").style.display = "block";
            document.getElementById("popupContent").style.display = "block";

            document.getElementById("username").value = username;
            document.getElementById("email").value = email;
            document.getElementById("telefono").value = telefono;
        }

        function chiudiPopup() {
            document.getElementById("popupOverlay").style.display = "none";
            document.getElementById("popupContent").style.display = "none";
        }
    </script>
</body>
</html>
