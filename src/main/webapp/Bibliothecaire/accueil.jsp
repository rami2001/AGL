<%@ page import="util.Session" %>
<%@ page import="model.Bibliothecaire" %><%--
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
</head>
<body>
<nav class="nav">
    <a href="accueil.jsp" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="/Bibliothecaire/livre" class="active" nav-item = "Bibliothécaires">
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

<%
    Bibliothecaire bibliothecaire = (Bibliothecaire) Session.getUtilisateur();
    String mail = bibliothecaire.getMail();
%>
<h1>
   hello,  <%=mail%>
</h1>

</body>
</html>
