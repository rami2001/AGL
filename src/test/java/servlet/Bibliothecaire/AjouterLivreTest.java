package servlet.Bibliothecaire;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AjouterLivreTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher requestDispatcher;

    AjouterLivre servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new AjouterLivre();
    }

    @Test
    public void doGet_ForwardsToCorrectPage() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Bibliothecaire/AjoutLivre.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_AddsBookSuccessfully() throws ServletException, IOException {
        when(request.getParameter("titre")).thenReturn("Test Book");
        when(request.getParameter("description")).thenReturn("Test Description");
        when(request.getParameter("isbn")).thenReturn("1234567890");
        when(request.getParameter("quantite")).thenReturn("10");
        when(request.getParameter("quantiteDispo")).thenReturn("10");

        servlet.doPost(request, response);

        // Verify that the book was added successfully
        // This will depend on your implementation of DAO.Livre.createIfNotExists
    }

    @Test
    public void doPost_ThrowsException() throws ServletException, IOException {
        when(request.getParameter("titre")).thenReturn("Test Book");
        when(request.getParameter("description")).thenReturn("Test Description");
        when(request.getParameter("isbn")).thenReturn("1234567890");
        when(request.getParameter("quantite")).thenReturn("10");
        when(request.getParameter("quantiteDispo")).thenReturn("10");

        // Simulate an exception being thrown when adding a book
        // This will depend on your implementation of DAO.Livre.createIfNotExists

        servlet.doPost(request, response);

        // Verify that the correct error handling was performed
        // This will depend on your implementation
    }
}