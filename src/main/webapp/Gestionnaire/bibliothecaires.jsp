<%@ page import="model.Gestionnaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.Bibliothecaire" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 05/01/2024
  Time: 03:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BibTarga</title>

    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/accueil.css">

    <script>
        var errorMessage = '<%= request.getAttribute("errorMessage") %>';

        if (!(errorMessage == 'null'))
        {
            alert(errorMessage);
        }
    </script>
</head>
<body>
<nav class="nav">
    <a href="accueil.jsp" nav-item = "Mon compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="" class="active" nav-item = "Bibliothécaires">
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

<main class="page">
    <%
        Gestionnaire gestionnaire = (Gestionnaire) Session.getUtilisateur();
    %>
    <h1 class="titre">Espace bibliothécaires.</h1>
    <hr>
    <section class="dashboard">
        <form class="form" action="Gestionnaire/bibliothecaires" method="post">
            <div class = "formGroup">
                <input type="text" placeholder="Adresse e-mail" name="mail" required>
                <input type="password" placeholder="Mot de passe" name="motDePasse" required>
                <input type="submit" value="Ajouter">
            </div>
        </form>
        <ul>
            <%
                List<Bibliothecaire> bibliothecaires = (List<Bibliothecaire>) request.getAttribute("bibliothecairesList");
                if (bibliothecaires != null) {
                    for (Bibliothecaire bibliothecaire : bibliothecaires) {
            %>
            <li>
                <p>E-Mail : <% out.println(bibliothecaire.getMail()); %></p>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </section>
</main>
</body>
</html>
