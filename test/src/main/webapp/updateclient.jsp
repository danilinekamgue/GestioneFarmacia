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


    <div class="container mt-5">
    <h1>Aggiorna Client</h1>

    <% 
        Client client = (Client) request.getAttribute("client");
        if (client != null) {
    %>
    <form action="updateclienti" method="post">
        <input type="hidden" name="email" value="<%= client.getEmail() %>">

        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" value="<%= client.getNome() %>" required>
        </div>


        <div class="form-group">
            <label for="password">Password</label>
            <input type="text" class="form-control" id="password" name="password" value="<%= client.getPassword() %>" required>
        </div>

        <div class="form-group">
         <label for="role">Role</label>
         <select class="form-control" id="role" name="role" required>
        <option value="" disabled selected>Seleziona un ruolo</option>
        <option value="admin">Admin</option>
        <option value="client">Client</option>
       
    </select>
</div>

        <button type="submit" class="btn btn-primary">Salva</button>
        <a href="home.jsp" class="btn btn-secondary">Annulla</a>
    </form>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        Client non trovato.
    </div>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>