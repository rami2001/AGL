package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import dao.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class Etudiant extends Abonne
{
    @DatabaseField(dataType = DataType.BOOLEAN, canBeNull = false)
    protected boolean aPaye = false;
    @DatabaseField(dataType = DataType.BOOLEAN, canBeNull = false)
    protected boolean estPenalise = false;
    
    public Etudiant()
    {

    }
    
    public Etudiant(String mail, String motDePasse, String nom, String prenom, Date dateInscription, boolean aPaye, boolean estPenalise)
    {
        super(mail, motDePasse, nom, prenom, dateInscription);
        this.aPaye = aPaye;
        this.estPenalise = estPenalise;
    }

    public boolean aPaye()
    {
        return aPaye;
    }

    public void aPaye(boolean aPaye)
    {
        this.aPaye = aPaye;
    }

    public boolean estPenalise() { return estPenalise; }
    public void estPenalise(boolean estPenalise)
    {
        this.estPenalise = estPenalise;
    }

    public List<Emprunt> getEmprunts()
    {
        try
        {
            return DAO.Emprunt.queryBuilder()
                    .where()
                    .eq("mail", this.getMail())
                    .query();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
