
<%
    if (session == null || session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp?error=sessionExpired"); // Nessuna sessione attiva
        return;
    }
%>
<%@ page import="java.util.List" %>
<%@ page import="model.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="bootstrap.jsp" %>s
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
                <li class="nav-item">
                    <a class="nav-link" href="#">Farmaci</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="clienti#clients-section">Clienti</a>
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

    <!-- Contenu principal -->
    <div id="clients-section" class="container mt-5">
        <h2>Lista Clienti</h2>
        <%
            // Récupération de la liste des clients depuis les attributs de requête
            Object obj = request.getAttribute("clients");
           List<model.Client> clients = null;
          if (obj instanceof List<?>) {
        clients = (List<model.Client>) obj;
    }
           
            if (clients != null && !clients.isEmpty()) {
        %>
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
                <% for (model.Client client : clients) { %>
                    <tr>
                        <td><%= client.getId() %></td>
                        <td><%= client.getNome() %></td>
                       
                        <td><%= client.getEmail() %></td>
                        
                        <td>
                          <!-- Lien pour mettre à jour le client -->
                          <a href="updateClient.jsp?id=<%= client.getId() %>" class="btn btn-primary btn-sm">U</a>

                          <!-- Formulaire pour supprimer le client -->
                          <form action="deleteClient" method="post" style="display:inline;">
                           <input type="hidden" name="id" value="<%= client.getId() %>">
                           <button type="submit" class="btn btn-danger btn-sm">X</button>
                        </form>
                        </td>
                       
                    </tr>

                <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>Aucun client trouvé.</p>
        <% } %>
          <div class="text-center mt-4">
            <a href="addClient.jsp" class="btn btn-success">Add Client</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>