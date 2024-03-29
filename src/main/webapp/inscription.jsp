<%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 04/01/2024
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BibTarga</title>
    <link rel="stylesheet" href="styles/all.css">
    <link rel="stylesheet" href="styles/inscription.css">

    <script>
        var errorMessage = '<%= request.getAttribute("errorMessage") %>';

        if (!(errorMessage == 'null')) {
            alert(errorMessage);
        }

        document.addEventListener("DOMContentLoaded", function() {
            const motDePasse = document.querySelector('input[name="motDePasse"]');
            const confirmationMotDePasse = document.querySelector('input[name="confirmationMotDePasse"]');

            confirmationMotDePasse.addEventListener('input', function() {
                if (confirmationMotDePasse.value !== motDePasse.value) {
                    confirmationMotDePasse.style.backgroundColor = 'var(--rose)';
                    confirmationMotDePasse.style.borderColor = 'red';
                } else {
                    confirmationMotDePasse.style.backgroundColor = '';
                    confirmationMotDePasse.style.borderColor = '';
                }
            });
        });

        document.addEventListener("DOMContentLoaded", function() {
            const form = document.querySelector('.form');
            const emailField = form.querySelector('input[name="mail"]');
            const nomField = form.querySelector('input[name="nom"]');
            const prenomField = form.querySelector('input[name="prenom"]');
            const erreur = form.querySelector('.probleme');

            form.addEventListener('submit', function(event) {
                if (!validateEmail(emailField.value)) {
                    event.preventDefault();
                    erreur.innerHTML = "Adresse mail invalide. ";
                    erreur.style.display = 'block';
                }

                if (!validateName(nomField.value)) {
                    event.preventDefault();
                    erreur.innerHTML = "Nom invalide. ";
                    erreur.style.display = 'block';
                }

                if (!validateName(prenomField.value)) {
                    event.preventDefault();
                    erreur.innerHTML = "Prénom invalide. ";
                    erreur.style.display = 'block';
                }

                erreut.stye.display = 'none';
            });

            function validateEmail(email) {
                const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return emailPattern.test(email);
            }

            function validateName(name) {
                const namePattern = /^[a-zA-ZÀ-ÿ-]+$/;
                return namePattern.test(name);
            }
        });
    </script>

</head>

<body>
    <main class="main">
        <form class="form" action="inscription" method="post">
            <div class="formGroup">
                <input type="text" name="mail" placeholder="Adresse e-mail" required>
            </div>
            <div class="formGroup">
                <input type="text" name="nom" placeholder="Nom" required>
                <input type="text" name="prenom" placeholder="Prenom" required>
            </div>
            <div class="formGroup">
                <input type="password" name="motDePasse" placeholder="Mot de passe" required>
                <input type="password" name="confirmationMotDePasse" placeholder="Confirmation du mot de passe" required>
            </div>
            <div class="formGroup">
                <p>Type de compte : </p>

                <input type="radio" value="enseignant" id="enseignant" name="type" required/>
                <label for="enseignant">Enseignant</label>

                <input type="radio" value="etudiant" id="etudiant" name="type"/>
                <label for="etudiant">Etudiant</label>

                <input type="radio" value="etudiantExterne" id="etudiantExterne" name="type"/>
                <label for="etudiantExterne">Etudiant externe</label>
            </div>
            <input type="submit" value="S'inscrire">

            <div class="probleme">
            </div>
        </form>
    </main>
</body>
</html>
