package servlet.Etudiant;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Emprunt;
import model.EtudiantInterne;
import model.Livre;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EtudiantConsulterServlet", urlPatterns = "/Etudiant/consulter")
public class EtudiantConsulterServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String isbn = request.getParameter("isbn");

        BDD.initialisation();
        try
        {
            Livre livre = DAO.Livre.queryForId(isbn);
            BDD.fermeture();

            request.setAttribute("livre", livre);
            request.getRequestDispatcher("/Etudiant/consulter.jsp").forward(request, response);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String isbn = req.getParameter("isbn");
        String mail = req.getParameter("mail");

        BDD.initialisation();
        try
        {
            Livre livre = DAO.Livre.queryForId(isbn);
            EtudiantInterne etudiantInterne = DAO.EtudiantInterne.queryForId(mail);

            if (livre.getQuantite() > 0 && etudiantInterne.getEmprunts().size() < 3)
            {
                Emprunt emprunt = new Emprunt();

                DAO.Livre.update(livre);

                emprunt.setLivre(livre);
                emprunt.setAbonne(etudiantInterne);

                DAO.Emprunt.createOrUpdate(emprunt);
            }

            BDD.fermeture();
        } catch (SQLException e)
        {
            //
        }

        resp.sendRedirect("/Etudiant/accueil");
    }

}