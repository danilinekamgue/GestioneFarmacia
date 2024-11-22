<%@ page import="java.sql.*, model.Client" %>
<%@ page import="model.Client" %>
<%@ page import="config.DbConfig" %>
<%@ page import="config.DbInfo" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Client</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>


    

    <% 
        Client client = (Client) request.getAttribute("client");
        if (client != null) {
    %>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg" style="width: 30rem;" >
            <div class="card-body">
                <h3 class="card-title text-center mb-4 text-primary" >Aggiorna Client</h3>
                <form action="updateclienti" method="post" style="background-color: #f0f8ff; padding: 20px; border-radius: 10px;">
                    <input type="hidden" name="email" value="<%= client.getEmail() %>">
                    
                    <!-- Nome -->
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" 
                               value="<%= client.getNome() %>" placeholder="Inserisci il nome" required>
                    </div>

                    <!-- Password -->
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" 
                               value="<%= client.getPassword() %>" placeholder="Inserisci la password" required>
                    </div>

                    <!-- Role -->
                    <div class="form-group">
                        <label for="role">Role</label>
                        <select class="form-control" id="role" name="role" required>
                            <option value="" disabled selected>Seleziona un ruolo</option>
                            <option value="admin" <%= client.getRole().equals("admin") ? "selected" : "" %>>Admin</option>
                            <option value="client" <%= client.getRole().equals("client") ? "selected" : "" %>>Client</option>
                        </select>
                    </div>

                    <!-- Pulsanti -->
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">Salva</button>
                        <a href="home.jsp" class="btn btn-secondary">Annulla</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        Client non trovato.
    </div>
    <% } %>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>