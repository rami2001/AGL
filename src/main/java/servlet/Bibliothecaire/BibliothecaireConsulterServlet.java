package servlet.Bibliothecaire;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Livre;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ConsulterServlet", urlPatterns = "/Bibliothecaire/consulter")
public class BibliothecaireConsulterServlet extends HttpServlet
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
            request.getRequestDispatcher("/Bibliothecaire/consulter.jsp").forward(request, response);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String isbn = req.getParameter("isbn");

        BDD.initialisation();
        try
        {
            DAO.Livre.deleteById(isbn);
            BDD.fermeture();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("redirect");
        resp.sendRedirect("/Bibliothecaire/accueil");
    }

}