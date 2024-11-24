<!DOCTYPE html>
<html lang="en">
<%@ include file="../bootstrap.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Farmaco</title>
</head>
<body>
    <div class="container mt-5">
        <h2>Aggiungi Farmaco</h2>
        <form action="addfarmaco" method="post">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <input type="text" class="form-control" id="descrizione" name="descrizione" required>
            </div>
            
            <div class="form-group">
                <label for="prezzo">Prezzo</label>
                <input type="text" class="form-control" id="prezzo" name="prezzo" required>
            </div>
            
             <div class="form-group">
                <label for="quantita">Quantita</label>
                <input type="text" class="form-control" id="quantita" name="quantita" required>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Aggiungi</button>
                <a href="admin-farmaci" class="btn btn-secondary">Annulla</a>
            </div>
        </form>
    </div>
</body>
</html>