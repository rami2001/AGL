package servlet.Etudiant;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Emprunt;
import model.EtudiantInterne;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EtudiantEmpruntsServlet", urlPatterns = "/Etudiant/emprunts")
public class EtudiantEmpruntsServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try
        {
            EtudiantInterne etudiantInterne = DAO.EtudiantInterne.queryForId(mail);

            List<Emprunt> emprunts = etudiantInterne.getEmprunts();

            request.setAttribute("emprunts", emprunts);
            request.getRequestDispatcher("/Etudiant/emprunts.jsp").forward(request, response);

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
            DAO.Emprunt.deleteById(id);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Etudiant/emprunts");
    }

}
