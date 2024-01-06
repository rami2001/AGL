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
import java.util.List;

@WebServlet(name = "BibliothecaireModifierServlet", urlPatterns = "/Bibliothecaire/modifier")
public class BibliothecaireModifierServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String isbn = req.getParameter("isbn");

        BDD.initialisation();

        try {
            Livre livre = DAO.Livre.queryForId(isbn);

            req.setAttribute("livre", livre);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/Bibliothecaire/modifier.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String titre = req.getParameter("titre");
        String description=req.getParameter("description");
        String isbn=req.getParameter("isbn");
        int quantite= Integer.parseInt(req.getParameter("quantite"));

        BDD.initialisation();
        try
        {
            Livre livre = DAO.Livre.queryForId(isbn);
            Livre nouveauLivre = new Livre(titre, description, isbn, quantite, livre.getQuantiteDisponible());
            DAO.Livre.update(nouveauLivre);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/accueil");
    }

}
