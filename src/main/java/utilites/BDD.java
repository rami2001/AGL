package utilites;

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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.*;

public class BaseDeDonnees
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


    public static String HashMotDePasse(String motDePasse)
    {
        motDePasse = motDePasse + SALT;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(motDePasse.getBytes());

            byte resultat[] = md.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < resultat.length; i++)
            {
                sb.append(Integer.toString(
                        (resultat[i] & 0xff) + 0x100, 16).substring(1));

            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            Logger.getLogger("SHA-256").log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static void main(String[] args) throws SQLException
    {
        initialisation();

//	Gestionnaire g = new Gestionnaire("a", "MDP");
//	Bibliothecaire b = new Bibliothecaire("c", "MDP");
        Enseignant e = new Enseignant("d", "MDP", "TITOUN", "Rami", new Date(2023, 12, 15));
//	EtudiantInterne ei = new EtudiantInterne("e", "MDP", "TITOUN", "Rami", new Date(2023, 12, 15), false, new Date(2023, 12, 15));
//	EtudiantExterne ex = new EtudiantExterne("f", "MDP", "TITOUN", "Rami", new Date(2023, 12, 15), false);
//
//	DAO.Gestionnaire.create(g);
//	DAO.Bibliothecaire.create(b);
//	DAO.Enseignant.create(e);
//	DAO.EtudiantInterne.create(ei);
//	DAO.EtudiantExterne.create(ex);
//
//	System.out.println("Gestionnaire :");
//	Gestionnaire q1 = DAO.Gestionnaire.queryForFirst();
//	System.out.println(q1.getMail() + " " + q1.getMotDePasse());
//
//	System.out.println("Bibliothecaire :");
//	Bibliothecaire q2 = DAO.Bibliothecaire.queryForFirst();
//	System.out.println(q2.getMail() + " " + q2.getMotDePasse());
//
//	System.out.println("Enseignant:");
//	Enseignant q3 = DAO.Enseignant.queryForFirst();
//	System.out.println(q3.getMail() + " " + q3.getMotDePasse());
//
//	System.out.println("Etudiant :");
//	EtudiantInterne q4 = DAO.EtudiantInterne.queryForFirst();
//	System.out.println(q4.getMail() + " " + q4.getMotDePasse());
//
        Livre l = new Livre("a", "b", "ISBN", 0, 0);

        DAO.Livre.create(l);
        DAO.Enseignant.create(e);

        Emprunt emp = new Emprunt(true, new Date(2023, 12, 15), new Date(2023, 12, 15));
        emp.setAbonne(e);
        emp.setLivre(l);

        DAO.Emprunt.create(emp);
    }

}
