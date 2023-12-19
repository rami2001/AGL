package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Abonne extends Utilisateur
{
    @DatabaseField(canBeNull = false)
    protected String nom = "N\\A";
    @DatabaseField(canBeNull = false)
    protected String prenom = "N\\A";
    @DatabaseField(dataType = DataType.DATE, canBeNull = false)
    protected Date dateInscription = null;

    public Abonne()
    {
	
    }
    
    public Abonne(String mail, String motDePasse, String nom, String prenom, Date dateInscription)
    {
	super(mail, motDePasse);
	this.nom = nom;
	this.prenom = prenom;
	this.dateInscription = dateInscription;
    }

    public String getNom()
    {
	return nom;
    }

    public String getPrenom()
    {
	return prenom;
    }

    public Date getDateInscription()
    {
	return dateInscription;
    }

    public void setNom(String nom)
    {
	this.nom = nom;
    }

    public void setPrenom(String prenom)
    {
	this.prenom = prenom;
    }
    
    public void setDateInscription(Date dateInscription)
    {
	this.dateInscription = dateInscription;
    }
}
