package servlet.Bibliothecaire;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bibliothecaire;
import model.Emprunt;
import model.Livre;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BibliothecaireRefuserServlet", urlPatterns = "/Bibliothecaire/refuser")
public class BibliothecaireRefuserServlet extends HttpServlet
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
            DAO.Emprunt.deleteById(id);

            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/Bibliothecaire/emprunts");
    }

}
