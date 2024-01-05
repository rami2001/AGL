package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "consultationServlet", value = "/consultation-servlet")
public class ConsultationServlet extends HttpServlet {
    public ConsultationServlet() {
    }

    public void init() {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{

    }
    public void destroy() {
    }
}
