<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="it.betacom.model.Animale"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquisti Cliente</title>
<link rel="stylesheet" href="Style.css">
</head>
<body>
	<h1>Acquisti</h1>
	<table>
		<tr>
			<th>Matricola</th>
			<th>Nome Animale</th>
			<th>Data Acquisto</th>
			<th>Prezzo</th>
			<th>Tipo Animale</th>
		</tr>
		<c:forEach items="${acquistiCliente}" var="animale">
			<tr>
				<td>${animale.getMatricola()}</td>
				<td>${animale.getNomeAnimale()}</td>
				<td>${animale.getDataAcquisto()}</td>
				<td>${animale.getPrezzo()}</td>
				<td>${animale.getTipoAnimale()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
