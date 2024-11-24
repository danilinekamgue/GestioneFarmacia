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
                <label for="quantit�">Quantit�</label>
                <input type="text" class="form-control" id="quantit�" name="quantit�" required>
            </div>
            <button type="submit" class="btn btn-primary">Aggiungi</button>
        </form>
    </div>
</body>
</html>