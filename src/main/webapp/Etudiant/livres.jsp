<%@ page import="model.EtudiantInterne" %>
<%@ page import="util.Session" %>
<%@ page import="model.Livre" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 08/01/2024
  Time: 02:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BibTarga</title>

    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/livres.css">
</head>
<body>
<nav class="nav">
    <a href="/Etudiant/accueil"nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="" class="active" nav-item = "Livres">
        <i class="bi bi-book"></i>
    </a>
    <a href="/Etudiant/emprunts" nav-item = "Emprunts">
        <i class="bi bi-bookmark"></i>
    </a>
    <a href="/Etudiant/historique" nav-item = "Historique">
        <i class="bi bi-clock-history"></i>
    </a>
    <a href="/deconnexion" nav-item = "Déconnexion">
        <i class="bi bi-box-arrow-left"></i>
    </a>
</nav>

<main class="page">
    <%
        EtudiantInterne etudiant = (EtudiantInterne) Session.getUtilisateur();
        etudiant.getNom();

        List<Livre> livres = (List<Livre>) request.getAttribute("livres");
    %>
    <h1 class="titre">Livres disponibles.</h1>
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
                <a href="/Etudiant/consulter?isbn=<%= livre.getIsbn() %>" class="btn">Consulter</a>
            </div>
            <% }
            } %>
        </div>
    </section>
</main>
</body>
</html>

