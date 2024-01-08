package dao;

import com.j256.ormlite.dao.Dao;

import model.Bibliothecaire;
import model.Emprunt;
import model.Enseignant;
import model.EtudiantExterne;
import model.EtudiantInterne;
import model.Gestionnaire;
import model.Livre;
import model.Retour;

public class DAO
{

    public static Dao<Gestionnaire, String> Gestionnaire = null;
    public static Dao<Bibliothecaire, String> Bibliothecaire = null;
    public static Dao<Enseignant, String> Enseignant= null;
    public static Dao<EtudiantInterne, String> EtudiantInterne= null;
    public static Dao<EtudiantExterne, String> EtudiantExterne= null;
    public static Dao<Livre, String> Livre= null;
    public static Dao<Emprunt, Long> Emprunt = null;
    public static Dao<Retour, Long> Retour = null;

}
