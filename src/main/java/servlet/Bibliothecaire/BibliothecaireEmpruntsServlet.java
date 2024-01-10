package servlet.Bibliothecaire;

import com.j256.ormlite.stmt.UpdateBuilder;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import model.Emprunt;
import model.EtudiantInterne;
import model.Livre;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BibliothecaireEmpruntsServlet", urlPatterns = "/Bibliothecaire/emprunts")
public class BibliothecaireEmpruntsServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BDD.initialisation();
        try
        {
            List<Emprunt> emprunts = DAO.Emprunt.queryForAll();

            request.setAttribute("emprunts", emprunts);
            request.getRequestDispatcher("/Bibliothecaire/emprunts.jsp").forward(request, response);

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Long id = Long.parseLong(req.getParameter("id"));

        BDD.initialisation();
        try
        {
            Emprunt emprunt = DAO.Emprunt.queryForId(id);

            Livre livre = emprunt.getLivre();
            Bibliothecaire bibliothecaire = new Bibliothecaire();

            bibliothecaire.confirmerEmprunt(emprunt);
            livre.setQuantiteDisponible(livre.getQuantiteDisponible() - 1);

            DAO.Livre.update(livre);

            UpdateBuilder<Emprunt, Long> update = DAO.Emprunt.updateBuilder();

            update.where().eq("id", id);

            update.updateColumnValue("estAccepte", emprunt.estAccepte())
                    .updateColumnValue("dateEmprunt", emprunt.getDateEmprunt())
                    .updateColumnValue("dateRetour", emprunt.getDateRetour())
                    .update();

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/emprunts");
    }

}
