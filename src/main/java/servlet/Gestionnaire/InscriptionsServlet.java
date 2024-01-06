package servlet.Gestionnaire;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EtudiantExterne;
import model.EtudiantInterne;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet(name = "InscriptionsServlet", urlPatterns = "/Gestionnaire/inscriptions")
public class InscriptionsServlet extends HttpServlet
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

        request.getRequestDispatcher("/Gestionnaire/inscriptions.jsp").forward(request, response);
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
            boolean paiement;

            if (etudiantInterne != null)
            {
                paiement = etudiantInterne.aPaye();

                LocalDateTime ld = LocalDateTime.now();
                Date date = Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());

                etudiantInterne.aPaye(!paiement);
                etudiantInterne.setDernierPaiement(date);

                DAO.EtudiantInterne.update(etudiantInterne);
            }
            if (etudiantExterne != null)
            {
                paiement = etudiantExterne.aPaye();

                etudiantExterne.aPaye(!paiement);

                DAO.EtudiantExterne.update(etudiantExterne);
            }

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/Gestionnaire/inscriptions");
    }

}
