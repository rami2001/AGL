<%@ page import="util.Session" %>
<%@ page import="model.EtudiantInterne" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="util.BDD" %>
<%@ page import="model.Emprunt" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 05/01/2024
  Time: 03:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BibTarga</title>

    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/emprunts.css">
</head>
<body>
<nav class="nav">
    <a href="/Etudiant/accueil" class="active" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="/Etudiant/livres" nav-item = "Livres">
        <i class="bi bi-book"></i>
    </a>
    <a href="" nav-item = "Emprunts">
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
        etudiant.checkDernierPaiement();

        List<Emprunt> emprunts = (List<Emprunt>) request.getAttribute("emprunts");
    %>
    <h1 class="titre">Liste des emprunts.</h1>
    <hr>
    <section class="dashboard">
        <table class="table">
            <tbody>
            <% if (emprunts != null)
            {
                for (Emprunt emprunt : emprunts) { %>
            <tr>
                <td><%= emprunt.getId() %></td>
                <td><%= emprunt.getLivre().getIsbn() %></td>
                <td><%= emprunt.getLivre().getTitre() %></td>
                <td><%= (emprunt.getDateEmprunt() != null) ? "Accepté" : "En attente" %></td>
                <td><%= (emprunt.getDateEmprunt() != null) ? BDD.getDate(emprunt.getDateEmprunt()) : "N\\A" %></td>
                <td><%= (emprunt.getDateRetour() != null) ? BDD.getDate(emprunt.getDateRetour()) : "N\\A" %></td>
                <td>
                    <form method="post" action="/Etudiant/emprunts">
                        <input type="hidden" name="id" value="<%= emprunt.getId() %>">
                        <button type="submit">
                            <i class="bi bi-x-circle"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <% }
            } %>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>
