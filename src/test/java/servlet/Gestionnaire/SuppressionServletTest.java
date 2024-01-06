package servlet.Gestionnaire;

import static org.junit.jupiter.api.Assertions.*;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SuppressionServletTest {

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new SuppressionServlet();
    }


    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    SuppressionServlet servlet;

    @BeforeEach
    public void doPost_DeletesUser() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");

        servlet.doPost(request, response);

        verify(DAO.Bibliothecaire).deleteById("test@mail.com");
        verify(DAO.EtudiantInterne).deleteById("test@mail.com");
        verify(DAO.EtudiantExterne).deleteById("test@mail.com");
        verify(DAO.Enseignant).deleteById("test@mail.com");
    }

    @Test
    public void doPost_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        when(DAO.Bibliothecaire.deleteById("test@mail.com")).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doPost(request, response));
    }
}