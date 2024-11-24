
<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp?error=sessionExpired"); // Nessuna sessione attiva
        return;
    }
%>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<%@ page import="model.Farmaco" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="bootstrap.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
</head>
<body>



<% if (request.getAttribute("farmaci") != null) { %>

<% } else if (request.getAttribute("clients") != null) { %>

<% } else if (request.getAttribute("orders") != null) {%> 
     
   <div class="container mt-5">
        <h2>Elenco Ordini</h2>
        <% if (request.getAttribute("orders") != null) { %>

        <% } else { %>
            <div class="alert alert-warning">
                Nessun ordine disponibile.
            </div>
        <% } %>
    </div>
        

     <%} %>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootst
    rap.bundle.min.js"></script>
</body>
</html>