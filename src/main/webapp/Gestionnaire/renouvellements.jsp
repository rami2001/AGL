<%@ page import="model.Gestionnaire" %>
<%@ page import="util.Session" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 05/01/2024
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BibTarga</title>

    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/accueil.css">
</head>
<body>
<nav class="nav">
    <a href="accueil.jsp" nav-item = "Mon compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="bibliothecaires.jsp" nav-item = "Bibliothécaires">
        <i class="bi bi-person-add"></i>
    </a>
    <a href="inscriptions.jsp" nav-item = "Inscriptions">
        <i class="bi bi-person-check"></i>
    </a>
    <a href="" class="active" nav-item = "Renouvellements">
        <i class="bi bi-coin"></i>
    </a>
    <a href="penalisations.jsp" nav-item = "Pénalisations">
        <i class="bi bi-exclamation-triangle"></i>
    </a>
    <a href="/deconnexion" nav-item = "Déconnexion">
        <i class="bi bi-box-arrow-left"></i>
    </a>
</nav>

<main class="page">
    <%
        Gestionnaire gestionnaire = (Gestionnaire) Session.getUtilisateur();
    %>
    <h1 class="titre">Gestion des renouvellements.</h1>
    <hr>
    <section class="dashboard">
    </section>
</main>
</body>
</html>
