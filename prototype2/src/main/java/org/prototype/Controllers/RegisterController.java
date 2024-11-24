package org.prototype.Controllers;

import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterController {
    protected static boolean isEmailFormatValid(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$");
        if (emailPattern.matcher(email).matches()) {
            return true;
        } else return false;
    }

    protected static boolean doesEmailExist(String adresseCourriel) {
        ArrayList<Utilisateur> utilisateurs = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:utilisateurs) {
            if (u.getAdresseCourriel().toLowerCase().equals(adresseCourriel.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
