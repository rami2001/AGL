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

@WebServlet(name = "BibliothecaireServlet", urlPatterns = "Gestionnaire/bibliotheciares")
public class BibliothecaireServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        BDD.initialisation();

        try {
            List<Bibliothecaire> bibliothecaires = DAO.Bibliothecaire.queryForAll();
            req.setAttribute("listeBibliothecaires", bibliothecaires);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("Gestionnaire/bibliothecaires.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try
        {
            if (DAO.Bibliothecaire.idExists(mail))
            {
                String errorMessage = "Un bibliothécaire avec cette adresse mail existe déjà !";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Gestionnaire/bibliothecaires");
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("bibliothecaires");
        dispatcher.forward(request, response);
    }

}
