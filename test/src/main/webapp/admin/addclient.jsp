

<!DOCTYPE html>
<html lang="en">
<%@ include file="../bootstrap.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Cliente</title>
</head>
<body>
   <form action="addclient" method="post">
    <div class="container mt-5">
        <h2>Aggiungi Cliente</h2>
        <form action="addclient" method="post">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            
           <div class="form-group">
         <label for="role">Role</label>
         <select class="form-control" id="role" name="role" required>
        <option value="" disabled selected>Seleziona un ruolo</option>
        <option value="admin">Admin</option>
        <option value="client">Client</option>
       
    </select>
</div>
            

            <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Aggiungi</button>
                <a href="admin-user" class="btn btn-secondary">Annulla</a>
            </div>
        </form>
    </div>
</body>
</html>