package bdd;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import model.Bibliothecaire;
import model.Emprunt;
import model.Enseignant;
import model.EtudiantExterne;
import model.EtudiantInterne;
import model.Gestionnaire;
import model.Livre;

import java.util.logging.Level;
import java.util.logging.Logger;

import dao.*;

public class BDD
{
    private static JdbcPooledConnectionSource connexion = null;
    final static private String SALT = "RLYTA";

    public static void initialisation()
    {
        try
        {
            connexion = new JdbcPooledConnectionSource("jdbc:sqlite:" + ENV.NOM_BDD);

            initialisationTables();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void initialisationTables()
    {
        try
        {
            // Creation des DAO
            DAO.Gestionnaire = DaoManager.createDao(connexion, Gestionnaire.class);
            DAO.Bibliothecaire = DaoManager.createDao(connexion, Bibliothecaire.class);
            DAO.Enseignant = DaoManager.createDao(connexion, Enseignant.class);
            DAO.EtudiantInterne = DaoManager.createDao(connexion, EtudiantInterne.class);
            DAO.EtudiantExterne = DaoManager.createDao(connexion, EtudiantExterne.class);
            DAO.Livre = DaoManager.createDao(connexion, Livre.class);
            DAO.Emprunt = DaoManager.createDao(connexion, Emprunt.class);

            // Creation des tables
            TableUtils.createTableIfNotExists(connexion, Gestionnaire.class);
            TableUtils.createTableIfNotExists(connexion, Bibliothecaire.class);
            TableUtils.createTableIfNotExists(connexion, Enseignant.class);
            TableUtils.createTableIfNotExists(connexion, EtudiantInterne.class);
            TableUtils.createTableIfNotExists(connexion, EtudiantExterne.class);
            TableUtils.createTableIfNotExists(connexion, Livre.class);
            TableUtils.createTableIfNotExists(connexion, Emprunt.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void fermeture()
    {
        try
        {
            connexion.close();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String HashMotDePasse(String motDePasse)
    {
        motDePasse = motDePasse + SALT;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(motDePasse.getBytes());

            byte resultat[] = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : resultat)
            {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            Logger.getLogger("SHA-256").log(Level.SEVERE, null, e);
            return null;
        }
    }
}
