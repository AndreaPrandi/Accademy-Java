<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="Style.css">
</head>
<body>
    <h1>Menu  Utente:<%= session.getAttribute("username") %></h1>
    <div class="menu-container">
        <form action="ControllaRuoloServlet" method="get">
            <button type="submit">Lista Utenti</button>
        </form>
        <form action="ListaClienti.jsp" method="get">
            <button type="submit">Lista Animali</button>
        </form>
        <form action="Logout" method="get">
            <button type="submit">Logout</button>
        </form>
    </div>
     
</body>
</html>
