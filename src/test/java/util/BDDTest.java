package util;

import dao.DAO;
import model.Gestionnaire;
import model.Utilisateur;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BDDTest
{

    @Test
    void testInitialisation(){
        assertDoesNotThrow(()->{
            BDD.initialisation();
        },"Initialization bdd should not throw SQLException.");
    }
    @Test
    void testFermeture(){
        assertDoesNotThrow(()->{
            BDD.initialisation();//init the database first to avoid null pointer exception
            BDD.fermeture();
        },"fermeture bdd should not throw SQLException.");
    }


    @Test
    void testAdminExistance() throws SQLException
    {
        BDD.initialisation();

        List<Gestionnaire> gestionnaire=DAO.Gestionnaire.queryBuilder()
                .where()
                .eq("mail", "admin@bibtarga.dz")
                .query();
        assertFalse(gestionnaire.isEmpty());//assert that the user exist

        BDD.fermeture();
    }
    @Test
    void hashAlgorithmIsCorrect() throws SQLException
    {
        BDD.initialisation();
        List<Gestionnaire> gestionnaire=DAO.Gestionnaire.queryBuilder()
                .where()
                .eq("mail", "admin@bibtarga.dz")
                .query();

        String hashedPassword= BDD.HashMotDePasse("admin");
        assertEquals(hashedPassword,gestionnaire.get(0).getMotDePasse());

        BDD.fermeture();
    }

    @Test
    void hashMotDePasseForDifferentInputShouldNotBeEqual(){
        String hashedPasswordForInput1= BDD.HashMotDePasse("admin");
        String hashedPasswordForInput2= BDD.HashMotDePasse("test");
        assertNotEquals(hashedPasswordForInput2,hashedPasswordForInput1);
    }



}