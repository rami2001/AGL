package servlet.Gestionnaire;
import dao.DAO;
import jakarta.servlet.RequestDispatcher;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class InscriptionsServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher requestDispatcher;

    InscriptionsServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new InscriptionsServlet();
    }

    @Test
    public void doGet_ForwardsToCorrectPage() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Gestionnaire/inscriptions.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doGet_SetsAttributes() throws ServletException, IOException, SQLException {
        List<EtudiantInterne> etudiantsInternes = Arrays.asList(new EtudiantInterne());
        List<EtudiantExterne> etudiantsExternes = Arrays.asList(new EtudiantExterne());

        when(DAO.EtudiantInterne.queryBuilder().where().eq("aPaye", false).query()).thenReturn(etudiantsInternes);
        when(DAO.EtudiantExterne.queryBuilder().where().eq("aPaye", false).query()).thenReturn(etudiantsExternes);

        servlet.doGet(request, response);

        verify(request).setAttribute("etudiantsInternes", etudiantsInternes);
        verify(request).setAttribute("etudiantsExternes", etudiantsExternes);
    }

    @Test
    public void doGet_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(DAO.EtudiantInterne.queryBuilder().where().eq("aPaye", false).query()).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
    }

    @Test
    public void doPost_UpdatesEtudiantInterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantInterne etudiantInterne = new EtudiantInterne();
        etudiantInterne.aPaye(false);

        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenReturn(etudiantInterne);

        servlet.doPost(request, response);

        assertTrue(etudiantInterne.aPaye());
    }

    @Test
    public void doPost_UpdatesEtudiantExterne() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        EtudiantExterne etudiantExterne = new EtudiantExterne();
        etudiantExterne.aPaye(false);

        when(DAO.EtudiantExterne.queryForId("test@mail.com")).thenReturn(etudiantExterne);

        servlet.doPost(request, response);

        assertTrue(etudiantExterne.aPaye());
    }

    @Test
    public void doPost_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(request.getParameter("mail")).thenReturn("test@mail.com");
        when(DAO.EtudiantInterne.queryForId("test@mail.com")).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doPost(request, response));
    }
}