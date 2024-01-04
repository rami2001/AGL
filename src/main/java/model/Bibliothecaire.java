package model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

import bdd.ENV;

@DatabaseTable(tableName = "bibliothecaire")
public class Bibliothecaire extends Utilisateur
{
    
    public Bibliothecaire()
    {

    }
    
    public Bibliothecaire(String mail, String motDePasse)
    {
	super(mail, motDePasse);
    }
    
    public void confirmerEmprunt(Emprunt emprunt)
    {
	emprunt.estAccepte(true);
	LocalDate ld = LocalDate.now();
	
	Date dateEmprunt = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	emprunt.setDateEmprunt(dateEmprunt);
	
	ld.plusMonths(ENV.TEPMS_EMPRUNT);
	
	Date dateRetour = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	emprunt.setDateRetour(dateRetour);
    }
    
    public void prolongerEmprunt(Emprunt emprunt, int mois)
    {		
	Date dateRetour = emprunt.getDateRetour();
	
	LocalDate ld = Instant.ofEpochMilli(dateRetour.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	
	ld.plusMonths(mois);
	
	dateRetour = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	emprunt.setDateRetour(dateRetour);
    }
    
    public void prolongerEmprunt(Emprunt emprunt)
    {		
	Date dateRetour = emprunt.getDateRetour();
	
	LocalDate ld = Instant.ofEpochMilli(dateRetour.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	
	ld.plusMonths(ENV.PROLONGEMENT_EMPRUNT);
	
	dateRetour = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	emprunt.setDateRetour(dateRetour);
    }
    
}
