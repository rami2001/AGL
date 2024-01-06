package servlet.Gestionnaire;

import dao.DAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SuppressionServlet", urlPatterns = "/Gestionnaire/supprimer")
public class SuppressionServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String mail = request.getParameter("mail");

        BDD.initialisation();
        try {
            DAO.Bibliothecaire.deleteById(mail);
            DAO.EtudiantInterne.deleteById(mail);
            DAO.EtudiantExterne.deleteById(mail);
            DAO.Enseignant.deleteById(mail);

            BDD.fermeture();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/Gestionnaire/accueil");
    }

}
