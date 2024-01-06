package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class DeconnexionServletTest {

    private DeconnexionServlet deconnexionServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        deconnexionServlet = new DeconnexionServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void doGet_shouldHandleDisconnection() throws IOException {
        deconnexionServlet.doGet(request, response);

        Mockito.verify(response, Mockito.times(1)).sendRedirect("index.jsp");
    }
}