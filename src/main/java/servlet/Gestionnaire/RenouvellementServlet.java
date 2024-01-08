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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RenouvellementServlet", urlPatterns = "/Gestionnaire/renouvellements")
public class RenouvellementServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        BDD.initialisation();

        try {
            List<EtudiantInterne> etudiantsInternes =
                        DAO.EtudiantInterne.queryBuilder()
                        .where().eq("aPaye", true)
                        .query();

            List<EtudiantInterne> etudiantsInternesEnRetard = new ArrayList<EtudiantInterne>();

            for (EtudiantInterne etudiant : etudiantsInternes)
            {
                LocalDateTime ld = LocalDateTime.now();

                Date maintenant = Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());
                Date date = etudiant.getDernierPaiement();
                Date datePaiement = BDD.plusAnnees(date, 1);

                if (maintenant.after(datePaiement))
                {
                    etudiantsInternesEnRetard.add(etudiant);
                }
            }

            request.setAttribute("etudiantsInternes", etudiantsInternesEnRetard);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/Gestionnaire/renouvellements.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();

        try
        {
            EtudiantInterne etudiantInterne = DAO.EtudiantInterne.queryForId(mail);

            boolean paiement = etudiantInterne.aPaye();

            LocalDateTime ld = LocalDateTime.now();
            Date date = Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());

            etudiantInterne.aPaye(!paiement);
            etudiantInterne.setDernierPaiement(date);

            DAO.EtudiantInterne.update(etudiantInterne);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

}
