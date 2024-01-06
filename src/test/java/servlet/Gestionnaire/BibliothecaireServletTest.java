package servlet.Gestionnaire;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class BibliothecaireServletTest {

    private BibliothecaireServlet bibliothecaireServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        bibliothecaireServlet = new BibliothecaireServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
    }

    @Test
    void doGet_shouldHandleGetRequest() throws IOException, ServletException {
        when(request.getRequestDispatcher("Gestionnaire/bibliothecaires.jsp")).thenReturn(dispatcher);

        bibliothecaireServlet.doGet(request, response);

        Mockito.verify(dispatcher, Mockito.times(1)).forward(request, response);
    }

    @Test
    void doPost_shouldHandlePostRequestForNewBibliothecaire() throws IOException, ServletException {
        when(request.getParameter("mail")).thenReturn("new@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getRequestDispatcher("bibliothecaires")).thenReturn(dispatcher);

        bibliothecaireServlet.doPost(request, response);

        Mockito.verify(dispatcher, Mockito.times(1)).forward(request, response);
    }

    @Test
    void doPost_shouldHandlePostRequestForExistingBibliothecaire() throws IOException, ServletException, SQLException {
        when(request.getParameter("mail")).thenReturn("existing@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getRequestDispatcher("Gestionnaire/bibliothecaires")).thenReturn(dispatcher);

        bibliothecaireServlet.doPost(request, response);

        Mockito.verify(request, Mockito.times(1)).setAttribute(eq("errorMessage"), anyString());
        Mockito.verify(dispatcher, Mockito.times(1)).forward(request, response);
    }
}