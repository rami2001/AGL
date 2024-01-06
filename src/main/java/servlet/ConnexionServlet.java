package servlet;

import dao.DAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import util.BDD;
import util.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ConnexionServlet", urlPatterns = "/connexion")
public class ConnexionServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");
        String motDePasse = request.getParameter("motDePasse");
        String hash = BDD.HashMotDePasse(motDePasse);
        String redirection = null;

        BDD.initialisation();
        try
        {
            List<Gestionnaire> gestionnaires = DAO.Gestionnaire.queryBuilder()
                    .where()
                    .eq("mail", mail)
                    .and()
                    .eq("motDePasse", hash)
                    .query();
            if (!gestionnaires.isEmpty())
            {
                Gestionnaire gestionnaire = gestionnaires.get(0);
                Session.connexion(gestionnaire);
                redirection = "/Gestionnaire/accueil";
            }

            List<Bibliothecaire> bibliothecaires = DAO.Bibliothecaire.queryBuilder()
                    .where()
                    .eq("mail", mail)
                    .and()
                    .eq("motDePasse", hash)
                    .query();
            if (!bibliothecaires.isEmpty())
            {
                Bibliothecaire bibliothecaire = bibliothecaires.get(0);
                Session.connexion(bibliothecaire);
                redirection = "/Bibliothecaire/accueil.jsp";
            }

            List<Enseignant> enseignants = DAO.Enseignant.queryBuilder()
                    .where()
                    .eq("mail", mail)
                    .and()
                    .eq("motDePasse", hash)
                    .query();
            if (!enseignants.isEmpty())
            {
                Enseignant enseignant = enseignants.get(0);
                Session.connexion(enseignant);
                redirection = "/Enseignant/accueil.jsp";
            }

            List<EtudiantInterne> etudiantsInternes = DAO.EtudiantInterne.queryBuilder()
                    .where()
                    .eq("mail", mail)
                    .and()
                    .eq("motDePasse", hash)
                    .query();
            if (!etudiantsInternes.isEmpty()) {
                EtudiantInterne etudiantInterne = etudiantsInternes.get(0);
                Session.connexion(etudiantInterne);
                redirection = "/Etudiant/accueil.jsp";
            }

            List<EtudiantExterne> etudiantsExternes = DAO.EtudiantExterne.queryBuilder()
                    .where()
                    .eq("mail", mail)
                    .and()
                    .eq("motDePasse", hash)
                    .query();
            if (!etudiantsExternes.isEmpty()) {
                EtudiantExterne etudiantExterne = etudiantsExternes.get(0);
                Session.connexion(etudiantExterne);
                redirection = "/Externe/accueil.jsp";
            }
            
            BDD.fermeture();

            if (redirection == null)
            {
                String errorMessage = "Compte introuvable !";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
            else
            {
                response.sendRedirect(redirection);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
