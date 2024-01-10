package model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "retour")
public class Retour extends Emprunt
{
    @DatabaseField(dataType = DataType.DATE, canBeNull = false)
    Date date = null;

    public Retour()
    {

    }

    public Retour(boolean estAccepte, Date dateEmprunt, Date dateRetour, Date date)
    {
        super(estAccepte, dateEmprunt, dateRetour);
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}
