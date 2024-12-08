package org.prototype;


import org.prototype.Controllers.ResidentController;
import org.prototype.Models.Utilisateur;
import org.prototype.Views.UnauthenticatedView;

/**
 * Classe principale du programme, responsable du lancement de l'application.
 */
public class MaVille {
    
    /** Utilisateur actuellement authentifié dans l'application. */
    private static Utilisateur curUser;

    /**
     * Définit l'utilisateur actuellement authentifié.
     *
     * @param u L'utilisateur à définir comme utilisateur courant.
     */
    public static void setCurUser(Utilisateur u) {
        curUser = u;
    }

    /**
     * Retourne l'utilisateur actuellement authentifié.
     *
     * @return L'utilisateur courant ou {@code null} si aucun utilisateur n'est authentifié.
     */
    public static Utilisateur getCurUser() {
        return curUser;
    }

    /**
     * Point d'entrée principal du programme. 
     * Initialise l'application et démarre l'interface utilisateur non authentifiée.
     *
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {

        // Crée une instance de l'interface pour les utilisateurs non authentifiés.
        UnauthenticatedView app = new UnauthenticatedView();

       // Initialise la carte des codes postaux pour les résidents. 
        ResidentController.initHashMap();


        // Commence l'application
        app.startApplication();

    }


}