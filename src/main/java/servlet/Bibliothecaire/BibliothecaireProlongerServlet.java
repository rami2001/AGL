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
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BibliothecaireProlongerServlet", urlPatterns = "/Bibliothecaire/prolonger")
public class BibliothecaireProlongerServlet extends HttpServlet
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
            Bibliothecaire bibliothecaire = new Bibliothecaire();

            bibliothecaire.prolongerEmprunt(emprunt);

            UpdateBuilder<Emprunt, Long> update = DAO.Emprunt.updateBuilder();

            update.where().eq("id", id);

            update.updateColumnValue("dateRetour", emprunt.getDateRetour())
                  .update();

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/emprunts");
    }

}
