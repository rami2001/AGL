package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class ConnexionServletTest {

    private ConnexionServlet connexionServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        connexionServlet = new ConnexionServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void doPost_shouldHandleLoginForGestionnaire() throws IOException, ServletException
    {
        when(request.getParameter("mail")).thenReturn("admin@bibtarga.dz");
        when(request.getParameter("motDePasse")).thenReturn("admin");

        connexionServlet.doPost(request, response);

        Mockito.verify(response, Mockito.times(1)).sendRedirect("/Gestionnaire/accueil.jsp");
    }
}