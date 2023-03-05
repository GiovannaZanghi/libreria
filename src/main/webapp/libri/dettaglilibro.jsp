<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	page import="com.generation.libreria.entities.*" %>    
<%	Libro l = (Libro) request.getAttribute("libro"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>DETTAGLI <%= l.getTitolo() %></title>
		<link rel="stylesheet" type="text/css" href="../foglioDiStile.css">
	</head>
	<body>
		<h1>Dettaglio del libro <%= l.getTitolo() %></h1>
		<hr>
		<br>
		AUTORE <%= l.getAutore() %><br>
		TITOLO <%= l.getTitolo() %><br>
		<a href="formmodifica?id=<%=l.getId()%>">FORM MODIFICA</a>
	</body>
</html>