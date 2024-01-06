<%--
  Created by IntelliJ IDEA.
  User: seylitanez
  Date: 2024-01-06
  Time: 05:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BibTarga</title>
    <link rel="stylesheet" href="../styles/all.css">
    <script src="../jquery-3.7.1.js" ></script>
</head>
<body>
    <nav class="nav">
        <a href="accueil.jsp" nav-item = "Compte">
            <i class="bi bi-person"></i>
        </a>
        <a href="/Bibliothecaire/livre/ajouter" class="active" nav-item = "Bibliothécaires">
            <i class="bi bi-person-add"></i>
        </a>
        <a href="inscriptions.jsp" nav-item = "Inscriptions">
            <i class="bi bi-person-check"></i>
        </a>
        <a href="renouvellements.jsp" nav-item = "Renouvellements">
            <i class="bi bi-coin"></i>
        </a>
        <a href="penalisations.jsp" nav-item = "Pénalisations">
            <i class="bi bi-exclamation-triangle"></i>
        </a>
        <a href="/deconnexion" nav-item = "Déconnexion">
            <i class="bi bi-box-arrow-left"></i>
        </a>
    </nav>
    <h1>ajout livre page</h1>

    <form id="formAjouter" class="form" action="ajouter" method="post">
        <div class = "formGroup">
            <input type="text" placeholder="Adresse e-mail" name="titre" required>
            <input type="text" placeholder="Adresse e-mail" name="description" required>
            <input type="text" placeholder="Adresse e-mail" name="isbn" required>
            <input type="number" placeholder="Adresse e-mail" name="quantite" required>
            <input type="number" placeholder="Adresse e-mail" name="quantiteDispo" required>
            <input type="submit" value="Ajouter">
        </div>
    </form>
    <script>
        $(document).ready(function() {
            // Écoute de la soumission du formulaire
            $("#formAjouter").submit(function(event) {
                // Empêche le comportement par défaut du formulaire (rechargement de la page)
                event.preventDefault();

                // Envoie de la requête POST avec les données du formulaire
                $.post("/Bibliothecaire/livre", $(this).serialize(), function(data) {
                    // Traitement à effectuer lorsque la requête est réussie
                    console.log("Réponse du serveur :", data);
                }).fail(function() {
                    // Traitement en cas d'échec de la requête
                    console.error("Échec de la requête AJAX");
                });
            });
        });
    </script>
</body>
</html>
