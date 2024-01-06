package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

class InscriptionServletTest {

    private InscriptionServlet inscriptionServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        inscriptionServlet = new InscriptionServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    void doPost_shouldHandleRegistrationForEnseignant() throws IOException, ServletException {
        when(request.getParameter("mail")).thenReturn("enseignant@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getParameter("nom")).thenReturn("nom");
        when(request.getParameter("prenom")).thenReturn("prenom");
        when(request.getParameter("type")).thenReturn("enseignant");

        inscriptionServlet.doPost(request, response);

        Mockito.verify(response, Mockito.times(1)).sendRedirect("/Enseignant/accueil.jsp");
    }

    @Test
    void doPost_shouldHandleRegistrationForEtudiantInterne() throws IOException, ServletException {
        when(request.getParameter("mail")).thenReturn("etudiant@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getParameter("nom")).thenReturn("nom");
        when(request.getParameter("prenom")).thenReturn("prenom");
        when(request.getParameter("type")).thenReturn("etudiant");

        inscriptionServlet.doPost(request, response);

        Mockito.verify(response, Mockito.times(1)).sendRedirect("/Etudiant/accueil.jsp");
    }

    @Test
    void doPost_shouldHandleRegistrationForEtudiantExterne() throws IOException, ServletException {
        when(request.getParameter("mail")).thenReturn("etudiantExterne@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getParameter("nom")).thenReturn("nom");
        when(request.getParameter("prenom")).thenReturn("prenom");
        when(request.getParameter("type")).thenReturn("etudiantExterne");

        inscriptionServlet.doPost(request, response);

        Mockito.verify(response, Mockito.times(1)).sendRedirect("/EtudiantExterne/accueil.jsp");
    }

    @Test
    void doPost_shouldHandleExistingMail() throws IOException, ServletException {
        when(request.getParameter("mail")).thenReturn("existing@mail.com");
        when(request.getParameter("motDePasse")).thenReturn("password");
        when(request.getParameter("nom")).thenReturn("nom");
        when(request.getParameter("prenom")).thenReturn("prenom");
        when(request.getParameter("type")).thenReturn("enseignant");

        inscriptionServlet.doPost(request, response);

        Mockito.verify(request, Mockito.times(1)).setAttribute(eq("errorMessage"), anyString());
        Mockito.verify(request, Mockito.times(1)).getRequestDispatcher("inscription.jsp");
    }
}