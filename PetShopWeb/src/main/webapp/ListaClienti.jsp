<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="it.betacom.model.Cliente"%>
<%@ page import="java.util.List"%>
<%@ page import="it.betacom.dao.ClienteDAO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Elenco Clienti</title>
<link rel="stylesheet" href="Style.css">
</head>
<body>
	<h1>Elenco Clienti</h1>
	<table>
		<tr>

			<th>Nome</th>
			<th>Cognome</th>
			<th>Indirizzo</th>
			<th>Città</th>
			<th>Telefono</th>
			<th>acquisti</th>
		</tr>
		<%
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Cliente> listaClienti = clienteDAO.getTuttiClienti();
            for (Cliente cliente : listaClienti) {
        %>
		<tr>

			<td><%= cliente.getNome() %></td>
			<td><%= cliente.getCognome() %></td>
			<td><%= cliente.getIndirizzo() %></td>
			<td><%= cliente.getCitta() %></td>
			<td><%= cliente.getTelefono() %></td>
			<td>
				<form action="Acquisti" method="post">
					<input type="hidden" name="idCliente"
						value="<%= cliente.getIdCliente() %>"> <input
						type="submit" name="visualizzaAcquisti"
						value="Visualizza Acquisti">
				</form>
			</td>
		</tr>
		<%
            }
        %>
	</table>
</body>
</html>
