package servlet.Gestionnaire;

import static org.junit.jupiter.api.Assertions.*;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.EtudiantInterne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RenouvellementServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    RenouvellementServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new RenouvellementServlet();
    }

    @Test
    public void doPost_RenewsEtudiantInterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantInterne etudiantInterne = new EtudiantInterne();
        etudiantInterne.aPaye(true);
        etudiantInterne.setDernierPaiement(Date.from(LocalDateTime.now().minusYears(1).atZone(ZoneId.systemDefault()).toInstant()));

        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenReturn(etudiantInterne);

        servlet.doPost(request, response);

        assertTrue(etudiantInterne.aPaye());
        assertTrue(etudiantInterne.getDernierPaiement().after(Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant())));
    }

    @Test
    public void doPost_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doPost(request, response));
    }
}