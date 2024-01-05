package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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

    
    
}
