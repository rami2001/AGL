package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "livre")
public class Livre
{
    @DatabaseField(id = true)
    private String isbn = "N\\A";
    @DatabaseField(canBeNull = false)
    private String titre = "N\\A";
    @DatabaseField(dataType = DataType.LONG_STRING, canBeNull = true)
    private String description = "N\\A";
    @DatabaseField(defaultValue = "0", canBeNull = false)
    private int quantite = 0;
    @DatabaseField(defaultValue = "0", canBeNull = false)
    private int quantiteDisponible = 0;

    public Livre()
    {
	
    }
    
    public Livre(String titre, String description, String isbn, int quantite, int quantiteDisponible)
    {
	this.titre = titre;
	this.description = description;
	this.isbn = isbn;
	this.quantite = quantite;
	this.quantiteDisponible = quantiteDisponible;
    }

    public String getTitre()
    {
	return titre;
    }

    public void setTitre(String titre)
    {
	this.titre = titre;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public String getIsbn()
    {
	return isbn;
    }

    public void setIsbn(String isbn)
    {
	this.isbn = isbn;
    }

    public int getQuantite()
    {
	return quantite;
    }

    public void setQuantite(int quantite)
    {
	this.quantite = quantite;
    }

    public int getQuantiteDisponible()
    {
	return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible)
    {
	this.quantiteDisponible = quantiteDisponible;
    }

}
