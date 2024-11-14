package org.prototype.Controllers;


import org.prototype.Models.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;



/**
 * Classe qui s'occupe des opérations concernant les utilisateurs
 */
public class UtilisateurController {

    /**
     * Fetch tous les utilisateurs
     * @return - La liste de tous les utilisateurs 
     */
    private static ArrayList<Utilisateur> getUtilisateurs() {
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
     * @throws IOException - si on n'est pas capable de lire du fichier
     */
    public static Utilisateur getUtilisateur(String email, String password){
        ArrayList<Utilisateur> users = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:users) {
            if (u.getAdresseCourriel().equals(email) && u.getMotDePasse().equals(password)) {
                return u;
            }
        }
        return null;
    }

}
