package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import util.BDD;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@DatabaseTable(tableName = "etudiantInterne")
public class EtudiantInterne extends Etudiant
{
    @DatabaseField(dataType = DataType.DATE, canBeNull = true)
    private Date dernierPaiement = null;
    
    public EtudiantInterne()
    {

    }
    
    public EtudiantInterne(String mail, String motDePasse, String nom, String prenom, Date dateInscription, boolean aPaye, boolean estPenalise, Date dernierPaiement)
    {
        super(mail, motDePasse, nom, prenom, dateInscription, aPaye, estPenalise);
	
        this.dernierPaiement = dernierPaiement;
    }

    public Date getDernierPaiement()
    {
        return dernierPaiement;
    }

    public void setDernierPaiement(Date dernierPaiement)
    {
        this.dernierPaiement = dernierPaiement;
    }

    public void checkDernierPaiement()
    {
        if (this.aPaye)
        {
            LocalDateTime ld = LocalDateTime.now();

            Date maintenant = Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());
            Date date = this.getDernierPaiement();
            Date datePaiement = BDD.plusAnnees(date, 1);

            if (maintenant.after(datePaiement))
            {
                this.aPaye(false);
            }
        }
    }
    
}
