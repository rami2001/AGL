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
    <link rel="stylesheet" href="styles/bibliothecaires.css">

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
    <a href="/Gestionnaire/accueil" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="" class="active" nav-item = "Bibliothécaires">
        <i class="bi bi-person-add"></i>
    </a>
    <a href="/Gestionnaire/inscriptions" nav-item = "Inscriptions">
        <i class="bi bi-person-check"></i>
    </a>
    <a href="/Gestionnaire/renouvellements" nav-item = "Renouvellements">
        <i class="bi bi-coin"></i>
    </a>
    <a href="/deconnexion" nav-item = "Déconnexion">
        <i class="bi bi-box-arrow-left"></i>
    </a>
</nav>

<main class="page">
    <%
        Gestionnaire gestionnaire = (Gestionnaire) Session.getUtilisateur();
        String mail = gestionnaire.getMail();
    %>
    <h1 class="titre">Espace bibliothécaires.</h1>

    <hr>

    <section class="dashboard">
        <h2>Ajouter un bibliothécaire : </h2>
        <form class="form" action="bibliothecaires" method="post">
            <div class = "formGroup">
                <input type="text" placeholder="Adresse e-mail" name="mail" required>
                <input type="password" placeholder="Mot de passe" name="motDePasse" required>
                <input type="submit" value="Ajouter">
            </div>
        </form>

        <hr>

        <h1>Bibliothécaires : </h1>

        <table>
            <tbody>
            <%
                List<Bibliothecaire> bibliothecaires = (List<Bibliothecaire>) request.getAttribute("bibliothecaires");
                if (bibliothecaires != null) {
                    for (Bibliothecaire bibliothecaire : bibliothecaires) {
            %>
            <tr>
                <td><%= bibliothecaire.getMail() %></td>
                <td>
                    <form method="post" action="supprimer">
                        <input type="hidden" name="mail" value="<%= bibliothecaire.getMail() %>">
                        <button type="submit" class="btn btn-link p-0">
                            <i class="bi bi-person-x"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </section>
</main>
</body>
</html>
