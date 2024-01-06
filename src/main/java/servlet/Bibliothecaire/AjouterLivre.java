package servlet.Bibliothecaire;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.BDD;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AjouterLivreServlet", urlPatterns = "/Bibliothecaire/livre")
public class AjouterLivre extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        req.getRequestDispatcher("/Bibliothecaire/AjoutLivre.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String titre = req.getParameter("titre");
        String description=req.getParameter("description");
        String isbn=req.getParameter("isbn");
        int quantite= Integer.parseInt(req.getParameter("quantite"));
        int quantiteDispo= Integer.parseInt(req.getParameter("quantiteDispo"));

        System.out.println(titre);
        System.out.println(description);

//        BDD.initialisation();


    }

}
