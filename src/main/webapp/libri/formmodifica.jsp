<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@	page import="com.generation.libreria.entities.*" %>    
<%	Libro l = (Libro) request.getAttribute("libro"); %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>MODIFICA LIBRO <%= l.getTitolo() %></title>
		<link rel="stylesheet" type="text/css" href="foglioDiStile.css">
	</head>
	<body>
		<h1>Dettaglio del libro con id = <%= l.getId() %></h1>
		<form action="modificalibro" method="get">
			ID <input type="number" name="id" value="<%= l.getId() %>" readonly><br>
			AUTORE <input type="text" name="autore" value="<%= l.getAutore() %>"><br>
			TITOLO <input type="text" name="titolo" value="<%= l.getTitolo() %>"><br>
			<input type="submit" value="AGGIORNA">
		</form>
	</body>
</html>