<%@ page import="java.sql.*, model.Farmaco" %>
<%@ page import="model.Farmaco" %>
<%@ page import="config.DbConfig" %>
<%@ page import="config.DbInfo" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Farmaco</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Aggiorna Farmaco</h1>

    <% 
        Farmaco farmaco = (Farmaco) request.getAttribute("farmaco");
        if (farmaco != null) {
    %>
    <form action="updateFarmaco" method="post">
        <input type="hidden" name="id" value="<%= farmaco.getId() %>">

        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" id="nome" name="nome" value="<%= farmaco.getNome() %>" required>
        </div>

        <div class="form-group">
            <label for="descrizione">Descrizione</label>
            <textarea class="form-control" id="descrizione" name="descrizione" rows="3" required><%= farmaco.getDescrizione() %></textarea>
        </div>

        <div class="form-group">
            <label for="prezzo">Prezzo</label>
            <input type="number" class="form-control" id="prezzo" name="prezzo" value="<%= farmaco.getPrezzo() %>" step="0.01" required>
        </div>

        <div class="form-group">
            <label for="quantita">Quantita</label>
            <input type="number" class="form-control" id="quantita" name="quantita" value="<%= farmaco.getQuantita() %>" required>
        </div>


            <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">Salva</button>
                <a href="admin-farmaci" class="btn btn-secondary">Annulla</a>
            </div>
    </form>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        Farmaco non trovato.
    </div>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>