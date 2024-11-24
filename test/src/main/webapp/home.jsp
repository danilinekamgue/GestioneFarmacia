
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
                    <a class="nav-link" href="/test/ordini">Prenotazioni</a>
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
    <% } else if (request.getAttribute("orders") != null) {%>
    	<h2> Lista Orders</h2>
    <% }%>
</h2>

<% if (request.getAttribute("farmaci") != null) { %>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrizione</th>
                <th>Prezzo</th>
                <th>Quantità</th>
                <th>Actions</th>
               
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
                    <td><%= farmaco.getPrezzo() %></td>
                    <td><%= farmaco.getQuantita() %></td>
                     <td>
                        <!-- Pulsante di aggiornamento -->
                        <a href="updateFarmaco?id=<%= farmaco.getId() %>" class="btn btn-primary btn-sm">U</a>
                        
                        <!-- Pulsante di eliminazione -->
                        <form action="deletefarmaco" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= farmaco.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">X</button>
                        </form>
                    </td>
                    
                   
                </tr>
            <% } %> 
        </tbody>
    </table>
      <div class="text-center mt-3">
        <a href="addfarmaco.jsp" class="btn btn-success btn-lg">Add farmaco</a>
    </div>
<% } else if (request.getAttribute("clients") != null) { %>
    <table class="table table-bordered">
        <thead>
            <tr>
            
                <th>Email</th>
                <th>Nome</th>
                
                <th>Role</th>
                <th>Password</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            for (Client client : clients) { %>
                <tr>
                
                     <td><%= client.getEmail() %></td>
                    <td><%= client.getNome() %></td>
                   
                    <td><%= client.getRole() %></td>
                    <td><%= client.getPassword() %></td>
                    <td>
                        <!-- Pulsante di aggiornamento -->
                        <a href="updateclienti?email=<%= client.getEmail() %>" class="btn btn-primary btn-sm">U</a>
                        
                        <!-- Pulsante di eliminazione -->
                        <form action="deleteclienti" method="post" style="display:inline;">
                            <input type="hidden" name="email" value="<%= client.getEmail() %>">
                            <button type="submit" class="btn btn-danger btn-sm">X</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
     <div class="text-center mt-3">
        <a href="addclient.jsp" class="btn btn-success btn-lg">Add Clients</a>
    </div>
<% } else if (request.getAttribute("orders") != null) {%> 
     
   <div class="container mt-5">
        <h2>Elenco Ordini</h2>
        <% if (request.getAttribute("orders") != null) { %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nome Farmaco</th>
                        <th>Prezzo Unitario (€)</th>
                        <th>Quantità</th>
                        <th>Prezzo Totale (€)</th>
                        <th>Descrizione </th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Farmaco> orders = (List<Farmaco>) request.getAttribute("orders");
                    double totalPrice = (double) request.getAttribute("totalPrice");
                    for (Farmaco farmaco : orders) { %>
                        <tr>
                            <td><%= farmaco.getNome() %></td>
                            <td><%= farmaco.getPrezzo() %></td>
                            <td><%= farmaco.getQuantita() %></td>
                            <td><%= farmaco.calcolaPrezzoTotale() %></td>
                            <td><%= farmaco.getDescrizione() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="mt-3">
                <h4>Prezzo Totale Ordine: €<%= totalPrice %></h4>
            </div>
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