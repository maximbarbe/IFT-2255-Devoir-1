package org.prototype;


import org.prototype.Models.Utilisateur;
import org.prototype.Views.UnauthenticatedView;

public class MaVille {
    
    private static Utilisateur curUser;

    public static void setCurUser(Utilisateur u) {
        curUser = u;
    }

    public static Utilisateur getCurUser() {
        return curUser;
    }
    public static void main(String[] args) {
        UnauthenticatedView app = new UnauthenticatedView();
        app.startApplication();

    }


}