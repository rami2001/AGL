package servlet;

import jakarta.servlet.RequestDispatcher;
import model.Enseignant;
import model.EtudiantExterne;
import model.EtudiantInterne;
import util.BDD;
import dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.Session;

import java.io.IOException;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@WebServlet(name = "InscriptionServlet", urlPatterns = "/inscription")
public class InscriptionServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try
        {
            if (DAO.Enseignant.idExists(mail) || DAO.EtudiantInterne.idExists(mail) || DAO.EtudiantExterne.idExists(mail))
            {
                String errorMessage = "Un compte avec cette adresse mail existe déjà !";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
                dispatcher.forward(request, response);

                BDD.fermeture();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        String motDePasse = request.getParameter("motDePasse");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String type = request.getParameter("type");

        LocalDateTime ld = LocalDateTime.now();

        Date date = Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());

        if (type != null) {
            switch (type) {
                case "enseignant":
                    Enseignant enseignant = new Enseignant(mail, motDePasse, nom, prenom, date);
                    try
                    {
                        DAO.Enseignant.create(enseignant);
                        Session.connexion(enseignant);
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }

                    BDD.fermeture();
                    break;
                case "etudiant":
                    EtudiantInterne etudiantInterne = new EtudiantInterne(mail, motDePasse, nom, prenom,
                                                        date, false, false, null);
                    try
                    {
                        DAO.EtudiantInterne.create(etudiantInterne);
                        Session.connexion(etudiantInterne);
                        response.sendRedirect("Etudiant/accueil.jsp");
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }

                    BDD.fermeture();
                    break;
                case "etudiantExterne":
                    EtudiantExterne etudiantExterne = new EtudiantExterne(mail, motDePasse, nom, prenom,
                                                        date, false, false);
                    try
                    {
                        DAO.EtudiantExterne.create(etudiantExterne);
                        Session.connexion(etudiantExterne);
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }

                    BDD.fermeture();
                    break;
                default:
                    break;
            }
        }
    }

}
