package servlet;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import util.BDD;
import util.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeconnexionServlet", urlPatterns = "/deconnexion")
public class DeconnexionServlet extends HttpServlet
{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Session.deconnexion();
        System.out.println(Session.getUtilisateur());
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}
