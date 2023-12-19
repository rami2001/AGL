package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gestionnaire")
public class Gestionnaire extends Utilisateur
{

    public Gestionnaire()
    {

    }

    public Gestionnaire(String mail, String motDePasse)
    {
	super(mail, motDePasse);
    }

    public void confirmerInscription(EtudiantExterne etudiant)
    {
	etudiant.aPaye(true);
    }
    
    public void confirmerInscription(EtudiantInterne etudiant)
    {
	etudiant.aPaye(true);
	
	LocalDate ld = LocalDate.now();
	
	Date dateRenouvellement = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	etudiant.setDernierPaiement(dateRenouvellement);
    }

}
