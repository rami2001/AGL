package servlet.Gestionnaire;
import dao.DAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Enseignant;
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
import static org.mockito.Mockito.*;

public class AccueilServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher requestDispatcher;

    AccueilServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new AccueilServlet();
    }

    @Test
    public void doGet_ForwardsToCorrectPage() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Gestionnaire/accueil.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doGet_SetsAttributes() throws ServletException, IOException, SQLException {
        List<EtudiantInterne> etudiantsInternes = Arrays.asList(new EtudiantInterne());
        List<EtudiantExterne> etudiantsExternes = Arrays.asList(new EtudiantExterne());
        List<Enseignant> enseignants = Arrays.asList(new Enseignant());

        when(DAO.EtudiantInterne.queryForAll()).thenReturn(etudiantsInternes);
        when(DAO.EtudiantExterne.queryForAll()).thenReturn(etudiantsExternes);
        when(DAO.Enseignant.queryForAll()).thenReturn(enseignants);

        servlet.doGet(request, response);

        verify(request).setAttribute("etudiantsInternes", etudiantsInternes);
        verify(request).setAttribute("etudiantsExternes", etudiantsExternes);
        verify(request).setAttribute("enseignants", enseignants);
    }

    @Test
    public void doGet_ThrowsSQLException() throws ServletException, IOException, SQLException {
        when(DAO.EtudiantInterne.queryForAll()).thenThrow(SQLException.class);

        assertThrows(RuntimeException.class, () -> servlet.doGet(request, response));
    }
}