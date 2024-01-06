package servlet.Gestionnaire;

import com.j256.ormlite.stmt.QueryBuilder;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import model.Enseignant;
import model.EtudiantExterne;
import model.EtudiantInterne;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AccueilServle", urlPatterns = "/Gestionnaire/accueil")
public class AccueilServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BDD.initialisation();

        try
        {
            List<EtudiantInterne> etudiantsInternes = DAO.EtudiantInterne.queryForAll();
            request.setAttribute("etudiantsInternes", etudiantsInternes);

            List<EtudiantExterne> etudiantsExternes = DAO.EtudiantExterne.queryForAll();
            request.setAttribute("etudiantsExternes", etudiantsExternes);

            List<Enseignant> enseignants = DAO.Enseignant.queryForAll();
            request.setAttribute("enseignants", enseignants);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/Gestionnaire/accueil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}