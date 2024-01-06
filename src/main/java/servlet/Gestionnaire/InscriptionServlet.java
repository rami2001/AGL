package servlet.Gestionnaire;

import com.j256.ormlite.stmt.QueryBuilder;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import model.EtudiantExterne;
import model.EtudiantInterne;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InscriptionServlet", urlPatterns = "/Gestionnaire/inscription")
public class InscriptionServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BDD.initialisation();

        try {
            List<EtudiantInterne> etudiantsInternes =
                        DAO.EtudiantInterne.queryBuilder()
                        .where().eq("aPaye", false)
                        .query();

            List<EtudiantExterne> etudiantsExternes =
                    DAO.EtudiantExterne.queryBuilder()
                            .where().eq("aPaye", false)
                            .query();

            request.setAttribute("etudiantsInternes", etudiantsInternes);
            request.setAttribute("etudiantsExternes", etudiantsExternes);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/Gestionnaire/bibliothecaires.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}
