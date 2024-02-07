<!DOCTYPE html>
<html>
<head>
    <title>Registrazione Utente</title>
      <link rel="stylesheet" href="Style.css">
</head>
<body>
    <h1>Registrazione Utente</h1>
    <form action="register" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="cellulare">Cellulare:</label>
        <input type="text" id="cellulare" name="cellulare" required><br>

        <label for="data_di_nascita">Data di Nascita:</label>
        <input type="text" id="data_di_nascita" name="data_di_nascita" required><br>

        

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Registrati">
    </form>
</body>
</html>