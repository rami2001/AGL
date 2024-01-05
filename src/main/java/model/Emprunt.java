package model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "emprunt")
public class Emprunt
{
    
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private long id;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Abonne abonne;
    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Livre livre;
    @DatabaseField(dataType = DataType.BOOLEAN, canBeNull = false)
    private boolean estAccepte;
    @DatabaseField(dataType = DataType.DATE, canBeNull = true)
    protected Date dateEmprunt = null;
    @DatabaseField(dataType = DataType.DATE, canBeNull = true)
    protected Date dateRetour = null;
    
    public Emprunt()
    {

    }
    
    public Emprunt(boolean estAccepte, Date dateEmprunt, Date dateRetour)
    {
        this.estAccepte = estAccepte;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public Abonne getAbonne()
    {
        return abonne;
    }
    
    public void setAbonne(Abonne abonne)
    {
        this.abonne = abonne;
    }
    
    public Livre getLivre()
    {
        return livre;
    }
    
    public void setLivre(Livre livre)
    {
        this.livre = livre;
    }
    
    public boolean estAccepte()
    {
        return estAccepte;
    }
    
    public void estAccepte(boolean estAccepte)
    {
        this.estAccepte = estAccepte;
    }
    
    public Date getDateEmprunt()
    {
        return dateEmprunt;
    }
    
    public void setDateEmprunt(Date dateEmprunt)
    {
        this.dateEmprunt = dateEmprunt;
    }
    
    public Date getDateRetour()
    {
        return dateRetour;
    }
    
    public void setDateRetour(Date dateRetour)
    {
        this.dateRetour = dateRetour;
    }
    
}
