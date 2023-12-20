
package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import utilites.BDD;

public abstract class Utilisateur
{
    
    @DatabaseField(id = true)
    protected String mail = "N\\A";
    @DatabaseField(canBeNull = false, dataType = DataType.LONG_STRING)
    protected String motDePasse = "N\\A";

    public Utilisateur()
    {
	
    }
    
    public Utilisateur(String mail, String motDePasse)
    {
	this.mail = mail;
	this.motDePasse = BDD.HashMotDePasse(motDePasse);
    }

    public String getMail()
    {
	return mail;
    }

    public String getMotDePasse()
    {
	return motDePasse;
    }

    public void setMail(String mail)
    {
	this.mail = mail;
    }

    public void setMotDePasse(String motDePasse)
    {
	this.motDePasse = BDD.HashMotDePasse(motDePasse);
    }
    
}
