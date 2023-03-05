<%@ page 
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@	page import="java.util.List" %>
<%@	page import="com.generation.libreria.entities.Libro" %>
<% List<Libro> libri = (List<Libro>) request.getAttribute("elencolibri"); %>
<% String salutoSimpa = request.getAttribute("salutosimpa") + ""; %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Elenco libri JSP</title>
		<link rel="stylesheet" type="text/css" href="../foglioDiStile.css">
	</head>
	<body>
	<!--	menu.html non è più nella stessa cartella di elencolibri.jsp quindi bisogna modificare
			il riferimento con ../menu.html laddove "../" indica che stiamo USCENDO dalla cartella
			libri e puntiamo a menu.html in webapp -> "../" = "torna su di un livello".
			Stessa cosa per il foglio di stile e le eventuali librerie come JQuery
	 -->
	<%@ include file="../menu.html" %>
	<hr>
	
	<h1>ELENCO DEI LIBRI NELLA LIBRERIA</h1>
	
	<table>
		<tr>
			<td class="intestazione">
				ID
			</td>
			<td class="intestazione">
				AUTORE
			</td>
			<td class="intestazione">
				TITOLO
			</td>
		</tr>
		<!--	In JSP ci sono diversi simboli sintattici:
				CONFIGURAZIONE	-> < % @ quando vengono gestite le impostazioni della pagina: IMPORT etc.
				CODICE JAVA 	-> < %	 quando scriviamo codice come fossimo in una classe JAVA
				STAMPA			-> < % = quando vogliamo stampare i dati da JAVA (System.out.println())	
		 -->
		<% for(Libro l : libri) {%>
		<tr>
			<td><%= l.getId() %></td>
			<td><%= l.getAutore() %></td>
			<td><%= l.getTitolo() %></td>
			<td>
				<a href="dettaglilibro?id=<%= l.getId() %>">DETTAGLI</a>
			</td>
			<td>
				<!-- "localhost:8080/eliminalibro?id=IDLIBRO" -->
				<a href="eliminalibro?id=<%= l.getId() %>">ELIMINA</a>
			</td>
		</tr>
		<%} %>
	</table>
	
	<h1><%= salutoSimpa %></h1>
	
	</body>
</html>