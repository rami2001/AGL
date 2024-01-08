<%@ page import="util.Session" %>
<%@ page import="model.EtudiantInterne" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="util.BDD" %><%--
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
    <a href="" class="active" nav-item = "Compte">
        <i class="bi bi-person"></i>
    </a>
    <a href="/Etudiant/livres" nav-item = "Livres">
        <i class="bi bi-book"></i>
    </a>
    <a href="/Etudiant/emprunts" nav-item = "Emprunts">
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
        String nom = etudiant.getNom();
        String prenom = etudiant.getPrenom();
        Date dateInscription = etudiant.getDateInscription();
        boolean aPaye = etudiant.aPaye();
        boolean estPenalise = etudiant.estPenalise();
        Date dernierPaiement = etudiant.getDernierPaiement();
    %>
    <h1 class="titre">Bienvenue, <%= nom %> <%= prenom %>.</h1>
    <hr>
    <section class="dashboard">
        <div class="item">
            <h2>
                <p>Date d'inscription : </p>
                <br>
                <%= BDD.getDate(dateInscription) %>
            </h2>
        </div>
        <div class="item statut">
            <h2>
                <p>Statut : </p>
                <br>
                <span
                        <%
                            String texte = "Non penalisé";
                            if(estPenalise)
                            {
                                out.print("class = penalise");
                                texte = "Penalisé";
                            }
                        %>>

                <%= texte %>
                </span>
            </h2>
        </div>
        <div class="item">
            <h2>
                <p>Mon inscription : </p>
                <br>
                <%
                    texte = "Votre inscription n'a pas encore été validée";
                    if(dernierPaiement != null)
                    {
                        texte = "Votre inscription a été validée. Elle est valable jusqu'au "
                        + BDD.getDate(BDD.plusAnnees(dernierPaiement, 1));
                    }

                    out.println(texte);
                %>
            </h2>
        </div>
        <div class="item">
            <h2>
                <p>Mon abonnement : </p>
                <br>
                <%
                    texte = "Vous n'êtes pas abonné car votre inscription n'a pas encore été validée";
                    if(dernierPaiement != null)
                    {
                        if (aPaye)
                        {
                            texte = "Vous pouvez effectuer des emprunts jusqu'au " +
                                    BDD.getDate(BDD.plusAnnees(dernierPaiement, 1));
                        }
                        else
                        {
                            texte = "Votre abonnement a expiré le " + BDD.getDate(BDD.plusAnnees(dernierPaiement, 1)) +
                            ", vous devez le renouveller auprès du gestionnaire";
                        }
                    }

                    out.println(texte);
                %>
            </h2>
        </div>
    </section>
</main>
</body>
</html>
