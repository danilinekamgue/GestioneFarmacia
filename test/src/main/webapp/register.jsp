<!DOCTYPE html>
<html>
<head>
    <title>Registrazione Utente</title>
</head>
<body>
    <h1>Modulo di Registrazione</h1>
    <form  action="register" method="post">
        <label for="name">Nome:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Registra</button>
    </form>
</body>
</html>