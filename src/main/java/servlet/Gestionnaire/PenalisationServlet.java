package servlet.Gestionnaire;

import dao.DAO;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "PenalisationServlet", urlPatterns = "/Gestionnaire/penaliser")
public class PenalisationServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try
        {
            EtudiantInterne etudiantInterne = DAO.EtudiantInterne.queryForId(mail);
            EtudiantExterne etudiantExterne = DAO.EtudiantExterne.queryForId(mail);

            if (etudiantInterne != null)
            {
                boolean penalisation = etudiantInterne.estPenalise();

                etudiantInterne.estPenalise(!penalisation);

                DAO.EtudiantInterne.update(etudiantInterne);
            }
            if (etudiantExterne != null)
            {
                boolean penalisation = etudiantExterne.estPenalise();

                etudiantExterne.estPenalise(!penalisation);

                DAO.EtudiantExterne.update(etudiantExterne);
            }

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/Gestionnaire/accueil");
    }

}
