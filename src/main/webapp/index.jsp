<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BibTarga</title>

    <link rel="stylesheet" href="styles/all.css">
    <link rel="stylesheet" href="styles/index.css">
    <script>
        var errorMessage = '<%= request.getAttribute("errorMessage") %>';

        if (!(errorMessage == 'null')) {
            alert(errorMessage);
        }
    </script>
</head>

<body>
    <main class="main">
    <form class="form" action="connexion" method="post">
        <input type="text" placeholder="Adresse e-mail" name="mail" required>
        <input type="password" placeholder="Mot de passe" name="motDePasse" required>
        <input type="submit" value="Se connecter">
    </form>
    <div>
        <p>Pas de compte ? </p>
        <a href="inscription">Inscrivez-vous.</a>
    </div>
</main>
</body>
</html>
