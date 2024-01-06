<%@ page import="model.Gestionnaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.EtudiantInterne" %>
<%@ page import="java.util.List" %>
<%@ page import="model.EtudiantExterne" %>
<%@ page import="util.BDD" %><%--
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
    <a href="/Gestionnaire/accueil" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="/Gestionnaire/bibliothecaires" nav-item = "Bibliothécaires">
        <i class="bi bi-person-add"></i>
    </a>
    <a href="" class="active" nav-item = "Inscriptions">
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
        List<EtudiantInterne> etudiantsInternes = (List<EtudiantInterne>) request.getAttribute("etudiantsInternes");
        List<EtudiantExterne> etudiantsExternes = (List<EtudiantExterne>) request.getAttribute("etudiantsExternes");
    %>
    <h1 class="titre">Gestion des inscriptions.</h1>
    <hr>
    <section class="dashboard">
        <h1>Etudiants : </h1>
        <div id="etudiants">
            <table class="table">
                <tbody id="etudiantsTable" class="collapse">
                <%  if (etudiantsInternes != null) {
                    for (EtudiantInterne etudiant : etudiantsInternes) { %>
                <tr>
                    <td><%= etudiant.getMail() %></td>
                    <td><%= etudiant.getNom() %></td>
                    <td><%= etudiant.getPrenom() %></td>
                    <td><%= BDD.getDate(etudiant.getDateInscription()) %></td>
                    <td>
                        <form method="post" action="inscriptions">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <button type="submit">
                                <i class="bi bi-check-circle"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>
        </div>
        <hr>
        <h1>Etudiants externes : </h1>
        <div id="externes">
            <table class="table">
                <tbody id="externesTable" class="collapse">
                <%  if (etudiantsExternes != null) {
                    for (EtudiantExterne etudiant : etudiantsExternes) { %>
                <tr>
                    <td><%= etudiant.getMail() %></td>
                    <td><%= etudiant.getNom() %></td>
                    <td><%= etudiant.getPrenom() %></td>
                    <td><%= BDD.getDate(etudiant.getDateInscription()) %></td>
                    <td>
                        <form method="post" action="inscriptions">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <button type="submit">
                                <i class="bi bi-check-circle"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
