package model;

import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "etudiantExterne")
public class EtudiantExterne extends Etudiant
{

    public EtudiantExterne()
    {

    }
    
    public EtudiantExterne(String mail, String motDePasse, String nom, String prenom, Date dateInscription, boolean aPaye)
    {
	super(mail, motDePasse, nom, prenom, dateInscription, aPaye);
    }
    
}
