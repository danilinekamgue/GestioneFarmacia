
<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp?error=sessionExpired"); // Nessuna sessione attiva
        return;
    }
%>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<%@ page import="model.Farmaco" %>
<%@include file="../bootstrap.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar</title>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">PharmaDaniella</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <!-- Cambia l'ordine dei link -->
                <li class="nav-item">
                    <a class="nav-link" href="/test/farmaci">Farmaci</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/test/clienti">Clienti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Prenotazioni</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <form action="logout" method="post" style="display: inline;">
                        <button type="submit" class="btn btn-outline-light btn-sm">Logout</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Contenuto principale -->
    <div class="container mt-5">
    
        <h2>
    <% if (request.getAttribute("farmaci") != null) { %>
        <h2>Lista Farmaci</h2>
    <% } else if (request.getAttribute("clients") != null) { %>
        <h2>Lista Clienti</h2>
    <% } %>
</h2>

<% if (request.getAttribute("farmaci") != null) { %>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrizione</th>
               
            </tr>
        </thead>
        <tbody>
            <% 
            List<Farmaco> farmaci = (List<Farmaco>) request.getAttribute("farmaci");
            for (Farmaco farmaco : farmaci) { %>
                <tr>
                    <td><%= farmaco.getId() %></td>
                    <td><%= farmaco.getNome() %></td>
                    <td><%= farmaco.getDescrizione() %></td>
                     <td>
                        <!-- Pulsante di aggiornamento -->
                        <a href="updateFarmaco.jsp?id=<%= farmaco.getId() %>" class="btn btn-primary btn-sm">U</a>
                        
                        <!-- Pulsante di eliminazione -->
                        <form action="deleteClient" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= farmaco.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">X</button>
                        </form>
                    </td>
                    
                   
                </tr>
            <% } %>
        </tbody>
    </table>
      <div class="text-center mt-3">
        <a href="addFarmaco.jsp" class="btn btn-success btn-lg">Add farmaco</a>
    </div>
<% } else if (request.getAttribute("clients") != null) { %>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            for (Client client : clients) { %>
                <tr>
                    <td><%= client.getNome() %></td>
                    <td><%= client.getEmail() %></td>
                    
                    <td>
                        <!-- Pulsante di aggiornamento -->
                        <a href="updateClient.jsp?id=<%= client.getEmail() %>" class="btn btn-primary btn-sm">U</a>
                        
                        <!-- Pulsante di eliminazione -->
                        <form action="deleteClient" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= client.getEmail() %>">
                            <button type="submit" class="btn btn-danger btn-sm">X</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
     <div class="text-center mt-3">
        <a href="addFarmaco.jsp" class="btn btn-success btn-lg">Add Clients</a>
    </div>
<% } %>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>