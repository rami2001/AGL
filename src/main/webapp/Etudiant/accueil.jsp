<%@ page import="util.Session" %>
<%@ page import="model.EtudiantInterne" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
    <link rel="stylesheet" href="styles/accueil.css">
</head>
<body>
<nav class="nav">
    <a href="" class="active" nav-item = "Mon compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="" nav-item = "Livres">
        <i class="bi bi-book-half"></i>
    </a>
    <a href="" nav-item = "Emprunts">
        <i class="bi bi-bookmark"></i>
    </a>
    <a href="" nav-item = "Historique">
        <i class="bi bi-clock-history"></i>
    </a>
    <a href="/deconnexion" nav-item = "Déconnexion">
        <i class="bi bi-box-arrow-left"></i>
    </a>
</nav>

<main class="page">
    <%
        EtudiantInterne etudiant = (EtudiantInterne) Session.getUtilisateur();
        String nom = etudiant.getNom();
        String prenom = etudiant.getPrenom();
        Date dateInscription = etudiant.getDateInscription();
        boolean aPaye = etudiant.aPaye();
        boolean estPenalise = etudiant.estPenalise();
        Date dernierPaiement = etudiant.getDernierPaiement();
    %>
    <h1 class="titre">Bienvenue, <% out.println(nom + " " + prenom + "."); %></h1>
    <hr>
    <section class="dashboard">
        <div class="item">
            <h2>
                <p>Date d'inscription : </p>
                <%
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

                    // Utilisez la méthode format pour obtenir la date formatée en tant que chaîne
                    String dateFormatee = format.format(dateInscription);

                    // Affichez la date formatée
                    out.println(dateFormatee);
                %>

            </h2>
        </div>
        <div class="item statut">
            <h2>
                <p>Statut : </p>
                <span
                        <%
                            String texte = "Non penalisé";
                            if(estPenalise)
                            {
                                out.print("class = penalise");
                                texte = "Penalisé";
                            }
                        %>>

                <% out.println(texte); %>
                </span>
            </h2>
        </div>
        <div class="item">
            <h2>
                <p>Mon inscription : </p>
                <%
                    texte = "Votre inscription n'a pas encore été validée";
                    if(dernierPaiement != null)
                    {
                        texte = "Vous êtes abonné.";
                    }

                    out.println(texte);
                %>
            </h2>
        </div>
    </section>
</main>
</body>
</html>
