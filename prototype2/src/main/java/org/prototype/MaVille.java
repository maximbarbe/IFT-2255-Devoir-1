package org.prototype;


import org.prototype.Controllers.UtilisateurController;
import org.prototype.Models.Utilisateur;
import org.prototype.Views.UnauthenticatedView;

public class MaVille {
    
    private static Utilisateur curUser;

    public static void setCurUser(Utilisateur u) {
        curUser = u;
    }

    public static Utilisateur getCurUser(Utilisateur u) {
        return curUser;
    }
    public static void main(String[] args) {
        Routes.setUp();
        UnauthenticatedView app = new UnauthenticatedView();
        app.startApplication();

    }


}