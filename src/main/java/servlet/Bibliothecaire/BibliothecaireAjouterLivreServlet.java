package servlet.Bibliothecaire;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Livre;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "BibliothecaireAjouterLivreServlet", urlPatterns = "/Bibliothecaire/accueil")
public class BibliothecaireAjouterLivreServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/Bibliothecaire/accueil.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String titre = req.getParameter("titre");
        String description=req.getParameter("description");
        String isbn=req.getParameter("isbn");
        int quantite= Integer.parseInt(req.getParameter("quantite"));
        Livre livre = new Livre(titre, description, isbn, quantite, quantite);

        BDD.initialisation();
        try
        {
            DAO.Livre.createOrUpdate(livre);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/accueil");
    }

}
