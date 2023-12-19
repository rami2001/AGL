package model;

import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "enseignant")
public class Enseignant extends Abonne
{

    public Enseignant()
    {
	
    }
    
    public Enseignant(String mail, String motDePasse, String nom, String prenom, Date dateInscription)
    {
	super(mail, motDePasse, nom, prenom, dateInscription);
    }

}
