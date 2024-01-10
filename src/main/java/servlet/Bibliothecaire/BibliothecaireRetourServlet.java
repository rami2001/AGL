package servlet.Bibliothecaire;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import model.Emprunt;
import model.Livre;
import model.Retour;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "BibliothecaireRetourServlet", urlPatterns = "/Bibliothecaire/retour")
public class BibliothecaireRetourServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Long id = Long.parseLong(req.getParameter("id"));

        BDD.initialisation();
        try
        {
            Emprunt emprunt = DAO.Emprunt.queryForId(id);
            Retour retour = new Retour(emprunt.estAccepte(), emprunt.getDateEmprunt(), emprunt.getDateRetour(), new Date());
            Livre livre = emprunt.getLivre();

            livre.setQuantiteDisponible(livre.getQuantiteDisponible() + 1);

            DAO.Retour.create(retour);
            DAO.Emprunt.deleteById(id);
            DAO.Livre.update(livre);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/emprunts");
    }

}
