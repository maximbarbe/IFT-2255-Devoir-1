package org.prototype.Controllers;


import org.prototype.Models.Utilisateur;
import java.util.ArrayList;
import com.password4j.*;


/**
 * Classe qui s'occupe des opérations concernant les utilisateurs
 */
public class UtilisateurController {

    /**
     * Fetch tous les utilisateurs
     * @return - La liste de tous les utilisateurs 
     */
    public static ArrayList<Utilisateur> getUtilisateurs() {
        ArrayList<Utilisateur> users = new ArrayList<>();
        users.addAll(ResidentController.getResidents());
        users.addAll(IntervenantController.getIntervenants());
        return users;

    }

    /**
     * Fetch un utilisateur en particulier des fichiers de données
     * @param email - Le email de l'utilisateur
     * @param password - Le mot de passe de l'utilisateur
     * @return - L'utilisateur avec le email et le mot de passe passés
     */
    public static Utilisateur getUtilisateur(String email, String password){
        ArrayList<Utilisateur> users = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:users) {
            // La méthode pour checker les passwords est prise de la documentation officielle de password4j:
            // https://github.com/Password4j/password4j
            // Source: Bertoldi, D. (2024, 31 juillet). password4j. GitHub. https://github.com/Password4j/password4j.
            if (u.getAdresseCourriel().toLowerCase().equals(email.toLowerCase()) && Password.check(password, u.getMotDePasse()).withArgon2()) {
                return u;
            }
        }
        return null;
    }

}
