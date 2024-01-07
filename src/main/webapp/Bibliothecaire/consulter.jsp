<%@ page import="model.Bibliothecaire" %>
<%@ page import="util.Session" %>
<%@ page import="model.Livre" %><%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 06/01/2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BibTarga</title>
    <link rel="stylesheet" href="../styles/all.css">
    <link rel="stylesheet" href="styles/consulter.css">
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

    Livre livre = (Livre) request.getAttribute("livre");
%>

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
                <a href="/Bibliothecaire/modifier?isbn=<%= livre.getIsbn() %>" class="btn">
                    <i id="mod" class="bi bi-pencil-square"></i>
                </a>
                <form method="post" action="consulter">
                    <input type="hidden" name="isbn" value="<%= livre.getIsbn() %>">
                    <button type="submit" class="btn btn-link p-0">
                        <i id="supp" class="bi bi-file-earmark-x"></i>
                    </button>
                </form>
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
