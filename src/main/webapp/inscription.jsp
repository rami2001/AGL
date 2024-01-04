<%--
  Created by IntelliJ IDEA.
  User: Rami
  Date: 04/01/2024
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BibTarga</title>

    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/inscription.css">

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const motDePasse = document.querySelector('input[name="motDePasse"]');
            const confirmationMotDePasse = document.querySelector('input[name="confirmationMotDePasse"]');

            confirmationMotDePasse.addEventListener('input', function() {
                if (confirmationMotDePasse.value !== motDePasse.value) {
                    confirmationMotDePasse.style.backgroundColor = '#fd7fba';
                    confirmationMotDePasse.style.color = 'red';
                    confirmationMotDePasse.style.borderColor = 'red';
                    confirmationMotDePasse.style.opacity = '0.6';
                } else {
                    confirmationMotDePasse.style.backgroundColor = '';
                    confirmationMotDePasse.style.color = '';
                    confirmationMotDePasse.style.borderColor = '';
                    confirmationMotDePasse.style.color = '1';
                }
            });
        });

        document.addEventListener("DOMContentLoaded", function() {
            const form = document.querySelector('.form');
            const emailField = form.querySelector('input[name="mail"]');
            const nomField = form.querySelector('input[name="nom"]');
            const prenomField = form.querySelector('input[name="prenom"]');

            form.addEventListener('submit', function(event) {
                if (!validateEmail(emailField.value)) {
                    event.preventDefault();
                    alert('Adresse email invalide');
                }

                if (!validateName(nomField.value)) {
                    event.preventDefault();
                    alert('Nom invalide');
                }

                if (!validateName(prenomField.value)) {
                    event.preventDefault();
                    alert('Prénom invalide');
                }
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
        <form class="form" action="">
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

                <input type="radio" value="enseignant" id="enseignant" name="type"/>
                <label for="enseignant">Enseignant</label>

                <input type="radio" value="etudiant" id="etudiant" name="type"/>
                <label for="etudiant">Etudiant</label>

                <input type="radio" value="etudiantExterne" id="etudiantExterne" name="type"/>
                <label for="etudiantExterne">Etudiant externe</label>
            </div>
            <input type="submit" value="S'inscrire">
        </form>
    </main>
</body>
</html>
