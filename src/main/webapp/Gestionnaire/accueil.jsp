<%@ page import="model.Gestionnaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.EtudiantInterne" %>
<%@ page import="java.util.List" %>
<%@ page import="model.EtudiantExterne" %>
<%@ page import="model.Enseignant" %>
<%@ page import="util.BDD" %><%--
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
</head>
<body>
<nav class="nav">
    <a href="" class="active" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="/Gestionnaire/bibliothecaires" nav-item = "Bibliothécaires">
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
        gestionnaire.getMail();

        List<EtudiantInterne> etudiantsInternes = (List<EtudiantInterne>) request.getAttribute("etudiantsInternes");
        List<EtudiantExterne> etudiantsExternes = (List<EtudiantExterne>) request.getAttribute("etudiantsExternes");
        List<Enseignant> enseignants = (List<Enseignant>) request.getAttribute("enseignant");
    %>
    <h1 class="titre">Bienvenue à l'espace gestionnaire.</h1>
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
                    <td><%= (etudiant.getDernierPaiement() == null) ? "En attente" : "Inscrit"%></td>
                    <td><%= etudiant.aPaye() ? "Payé" : "Non payé" %></td>
                    <td><%= BDD.getDate(etudiant.getDateInscription()) %></td>
                    <td><%= (etudiant.getDernierPaiement() == null) ? "N\\A" : BDD.getDate(etudiant.getDernierPaiement()) %></td>
                    <td><%= etudiant.estPenalise() ? "Pénalisé" : "Non pénalisé" %></td>
                    <td>
                        <form method="post" action="penaliser">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <% if(etudiant.estPenalise()) { %>
                            <button type="submit">
                                <i class="bi bi-check-circle"></i>
                            </button>
                            <% } else { %>
                            <button type="submit">
                                <i class="bi bi-exclamation-triangle"></i>
                            </button>
                            <% } %>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="supprimer">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <button type="submit">
                                <i class="bi bi-person-x"></i>
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
                <% if (etudiantsExternes != null)
                {
                    for (EtudiantExterne etudiant : etudiantsExternes) { %>
                <tr>
                    <td><%= etudiant.getMail() %></td>
                    <td><%= etudiant.getNom() %></td>
                    <td><%= etudiant.getPrenom() %></td>
                    <td><%= etudiant.aPaye() ? "Inscris" : "En attente"%></td>
                    <td><%= BDD.getDate(etudiant.getDateInscription()) %></td>
                    <td>
                        <%= etudiant.estPenalise() ? "Pénalisé" : "Non pénalisé" %>
                    </td>
                    <td>
                        <form method="post" action="penaliser">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <% if(etudiant.estPenalise()) { %>
                            <button type="submit">
                                <i class="bi bi-check-circle"></i>
                            </button>
                            <% } else { %>
                            <button type="submit">
                                <i class="bi bi-exclamation-triangle"></i>
                            </button>
                            <% } %>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="supprimer">
                            <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                            <button type="submit">
                                <i class="bi bi-person-x"></i>
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
        <h1>Enseignants : </h1>
        <div id="enseignants">
            <table class="table">
                <tbody id="enseignantsTable" class="collapse">
                <% if (enseignants != null)
                {
                    for (Enseignant enseignant : enseignants) { %>
                <tr>
                    <td><%= enseignant.getMail() %></td>
                    <td><%= enseignant.getNom() %></td>
                    <td><%= enseignant.getPrenom() %></td>
                    <td><%= BDD.getDate(enseignant.getDateInscription()) %></td>
                    <td>
                        <form method="post" action="supprimer">
                            <input type="hidden" name="mail" value="<%= enseignant.getMail() %>">
                            <button type="submit">
                                <i class="bi bi-person-x"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <% } }%>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
