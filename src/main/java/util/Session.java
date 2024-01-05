package util;

import model.Utilisateur;

public class Session
{
    private static Utilisateur utilisateur = null;

    public static void connexion(Utilisateur u)
    {
        utilisateur = u;
    }

    public static boolean estActive()
    {
        return utilisateur != null;
    }

    public static void deconnexion()
    {
        utilisateur = null;
    }

    public static Utilisateur getUtilisateur()
    {
        return utilisateur;
    }
}
