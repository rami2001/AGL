<%@ page import="model.Bibliothecaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.Livre" %><%--
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
    <link rel="stylesheet" href="styles/accueil.css">
    <script src="../jquery-3.7.1.js" ></script>
</head>
<body>
    <nav class="nav">
        <a href="" nav-item = "Ajouter" class="active">
            <i class="bi bi-plus-circle"></i>
        </a>
        <a href="/Bibliothecaire/livres" nav-item = "Livres">
            <i class="bi bi-book"></i>
        </a>
        <a href="/Bibliothecaire/emprunts" nav-item = "Emprunts">
            <i class="bi bi-bookmark"></i>
        </a>
        <a href="/Bibliothecaire/demandes" nav-item = "Demandes">
            <i class="bi bi-bookmark-plus"></i>
        </a>
        <a href="/Bibliothecaire/historique" nav-item = "Historique">
            <i class="bi bi-clock-history"></i>
        </a>
        <a href="/deconnexion" nav-item = "Déconnexion">
            <i class="bi bi-box-arrow-left"></i>
        </a>
    </nav>

    <%
        Bibliothecaire bibliothecaire = (Bibliothecaire) Session.getUtilisateur();
        bibliothecaire.getMail();

        Livre livre = (Livre) request.getAttribute("livre");
    %>

    <main class="page">

        <h1>Modification du livre (<%= livre.getIsbn()%>).</h1>

        <hr>
        <section class="dashboard">
            <h1>Ajouter un livre : </h1>
            <form id="formAjouter" class="form" action="/Bibliothecaire/modifier" method="post">
                <div class = "formGroup">
                    <input type="text" name="isbn" value="<%= livre.getIsbn() %>" readonly>
                    <input type="text" placeholder="Titre" name="titre" value="<%= livre.getTitre() %>" required>
                </div>
                <div class="formGroup">
                    <textarea rows="7" cols="50" name="description" placeholder="Description" required><%= livre.getDescription() %></textarea>
                </div>
                <div class="formGroup">
                    <input type="number" placeholder="Quantité" name="quantite" value="<%= livre.getQuantite() %>" required>
                    <input type="submit" value="Modifier">
                </div>
            </form>
        </section>
    </main>

    <script>
        $(document).ready(function() {
            // Écoute de la soumission du formulaire
            $("#formAjouter").submit(function(event) {
                // Empêche le comportement par défaut du formulaire (rechargement de la page)
                event.preventDefault();

                var quantite = $("input[name='quantite']").val();
                if (quantite < 1) {
                    alert("La quantité doit être strictement supérieure à 0 !");
                    return;
                }

                // Envoie de la requête POST avec les données du formulaire
                $.post("/Bibliothecaire/accueil", $(this).serialize(), function(data) {
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
