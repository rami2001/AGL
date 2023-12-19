package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public abstract class Etudiant extends Abonne
{
    @DatabaseField(dataType = DataType.BOOLEAN, canBeNull = false)
    protected boolean aPaye = false;
    
    public Etudiant()
    {

    }
    
    public Etudiant(String mail, String motDePasse, String nom, String prenom, Date dateInscription, boolean aPaye)
    {
	super(mail, motDePasse, nom, prenom, dateInscription);
	this.aPaye = aPaye;
    }

    public boolean aPaye()
    {
        return aPaye;
    }

    public void aPaye(boolean aPaye)
    {
        this.aPaye = aPaye;
    }

    
}
