package servlet.Gestionnaire;

import static org.junit.jupiter.api.Assertions.*;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EtudiantExterne;
import model.EtudiantInterne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PenalisationServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    PenalisationServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new PenalisationServlet();
    }

    @Test
    public void doPost_PenalisesEtudiantInterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantInterne etudiantInterne = new EtudiantInterne();
        etudiantInterne.estPenalise(false);

        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenReturn(etudiantInterne);

        servlet.doPost(request, response);

        assertTrue(etudiantInterne.estPenalise());
    }

    @Test
    public void doPost_UnpenalisesEtudiantInterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantInterne etudiantInterne = new EtudiantInterne();
        etudiantInterne.estPenalise(true);

        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenReturn(etudiantInterne);

        servlet.doPost(request, response);

        assertFalse(etudiantInterne.estPenalise());
    }

    @Test
    public void doPost_PenalisesEtudiantExterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantExterne etudiantExterne = new EtudiantExterne();
        etudiantExterne.estPenalise(false);

        when(DAO.EtudiantExterne.queryForId("test@mail.com")).thenReturn(etudiantExterne);

        servlet.doPost(request, response);

        assertTrue(etudiantExterne.estPenalise());
    }

    @Test
    public void doPost_UnpenalisesEtudiantExterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantExterne etudiantExterne = new EtudiantExterne();
        etudiantExterne.estPenalise(true);

        when(DAO.EtudiantExterne.queryForId("test@mail.com")).thenReturn(etudiantExterne);

        servlet.doPost(request, response);

        assertFalse(etudiantExterne.estPenalise());
    }

    @Test
    public void doPost_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doPost(request, response));
    }
}