package org.prototype;


import org.prototype.Models.Utilisateur;
import org.prototype.Views.UnauthenticatedView;

/**
 * Classe principale du programme, c'est cette classe qui lance l'application.
 */
public class MaVille {
    
    // On stocke l'utilisateur courant.
    private static Utilisateur curUser;

    /**
     * Permet de set l'utilisateur courant.
     * @param u - L'utilisateur
     */
    public static void setCurUser(Utilisateur u) {
        curUser = u;
    }

    /**
     * Permet d'obtenir l'utilisateur courant
     * @return L'utilisateur courant
     */
    public static Utilisateur getCurUser() {
        return curUser;
    }
    public static void main(String[] args) {
        UnauthenticatedView app = new UnauthenticatedView();
        // Commence l'application
        app.startApplication();

    }


}