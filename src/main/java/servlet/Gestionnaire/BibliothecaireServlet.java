package servlet.Gestionnaire;

import dao.DAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BibliothecaireServlet", urlPatterns = "/Gestionnaire/bibliothecaires")
public class BibliothecaireServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BDD.initialisation();

        try {
            List<Bibliothecaire> bibliothecaires = DAO.Bibliothecaire.queryForAll();

            request.setAttribute("bibliothecaires", bibliothecaires);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/Gestionnaire/bibliothecaires.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try
        {
            if (DAO.Bibliothecaire.idExists(mail) || DAO.EtudiantInterne.idExists(mail)
                    || DAO.EtudiantExterne.idExists(mail) || DAO.Enseignant.idExists(mail))
            {
                String errorMessage = "Un compte avec cette adresse mail existe déjà !";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Gestionnaire/accueil");
                dispatcher.forward(request, response);

                BDD.fermeture();
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        String motDePasse = request.getParameter("motDePasse");

        Bibliothecaire bibliothecaire = new Bibliothecaire(mail, motDePasse);
        try
        {
            DAO.Bibliothecaire.create(bibliothecaire);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/Gestionnaire/bibliothecaires");
    }

}
