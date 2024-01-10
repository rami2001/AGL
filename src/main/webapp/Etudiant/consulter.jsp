<%@ page import="model.Bibliothecaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.Livre" %>
<%@ page import="model.EtudiantInterne" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 06/01/2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    EtudiantInterne etudiant = (EtudiantInterne) Session.getUtilisateur();
    etudiant.checkDernierPaiement();

    Livre livre = (Livre) request.getAttribute("livre");
%>

<html>
<head>
    <title>BibTarga</title>
    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/consulter.css">
    <script src="../jquery-3.7.1.js" ></script>
</head>
<body>
<nav class="nav">
    <a href="/Etudiant/accueil" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="" class="active" nav-item = "Livres">
        <i class="bi bi-book"></i>
    </a>
    <a href="/Etudiant/emprunts?mail=<%= etudiant.getMail() %>" nav-item = "Emprunts">
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

    <h1>ISBN : <%= livre.getIsbn() %></h1>

    <hr>
    <section class="dashboard">
        <div class="info">
            <h1><%= livre.getTitre() %></h1>

            <br>

            <h3>Quantité : <%= livre.getQuantite() %></h3>
            <h3>Quantité disponible : <%= livre.getQuantiteDisponible() %></h3>

            <br>

            <div class="action">
                <form method="post" action="/Etudiant/consulter">
                    <input type="hidden" name="isbn" value="<%= livre.getIsbn() %>">
                    <input type="hidden" name="mail" value="<%= etudiant.getMail() %>">
                    <input type="submit" value="Emprunter"
                        <% if(!etudiant.aPaye() || etudiant.estPenalise()) {
                            out.println("disabled");
                            out.println("class = \"disabled\"");
                        }%>
                    >
                </form>
                <br>
                <p>
                    <% if(!etudiant.aPaye()) out.println("Vous devez avoir un abonnement valide pour effectuer un emprunt."); %>
                </p>
                <p>
                    <% if(etudiant.estPenalise()) out.println("Vous êtes pénalisé et ne pouvez donc pas effectuer d'emprunts."); %>
                </p>
            </div>
        </div>
        <div class="description">
            <h1>Description : </h1>
            <br>
            <h3><%= livre.getDescription() %></h3>
        </div>
    </section>
</main>

</body>
</html>
