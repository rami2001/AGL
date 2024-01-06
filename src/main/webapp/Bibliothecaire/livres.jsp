<%@ page import="model.Bibliothecaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.Livre" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 06/01/2024
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BibTarga</title>
    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/livres.css">
    <script src="../jquery-3.7.1.js" ></script>
</head>
<body>
<nav class="nav">
    <a href="/Bibliothecaire/accueil" nav-item = "Ajouter">
        <i class="bi bi-plus-circle"></i>
    </a>
    <a href="/Bibliothecaire/livres" nav-item = "Livres" class="active">
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

    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>

<main class="page">

    <h1>Espace bibliothécaire.</h1>

    <hr>
    <section class="dashboard">
        <div class="card-container">
            <% if(livres != null)
            {
                for (Livre livre : livres) { %>
                    <div class="card">
                            <h2 class="card-title"><%= livre.getTitre() %></h2>
                            <p class="card-text">ISBN: <%= livre.getIsbn() %></p>
                            <p class="card-text">Quantité: <%= livre.getQuantite() %></p>
                            <p class="card-text">Disponible: <%= livre.getQuantiteDisponible() %></p>
                            <a href="/Bibliothecaire/consulter?isbn=<%= livre.getIsbn() %>" class="btn">Consulter</a>
                    </div>
            <% }
            } %>
        </div>
    </section>
</main>

</body>
</html>

