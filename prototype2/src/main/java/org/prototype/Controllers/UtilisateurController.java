package org.prototype.Controllers;

import org.prototype.Models.Intervenant;
import org.prototype.Models.Resident;
import org.prototype.Models.TypeIntervenant;
import org.prototype.Models.Utilisateur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class UtilisateurController {

    private static ArrayList<Utilisateur> getUtilisateurs() throws IOException {
        ArrayList<Utilisateur> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/residents.csv"));
        String line;
        while ((line = reader.readLine())!=null) {
            String[] data = line.split(",");
            users.add(new Resident(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
        }
        reader.close();
        reader = new BufferedReader(new FileReader("src/intervenants.csv"));
        while ((line = reader.readLine())!=null) {
            TypeIntervenant type = null;
            String[] data = line.split(",");
            switch (data[4]) {
                case "entreprise_publique":
                    type = TypeIntervenant.ENTREPRISE_PUBLIQUE;
                    break;
                case "entrepreneur_prive":
                    type = TypeIntervenant.ENTREPRENEUR_PRIVE;
                    break;
                case "particulier":
                    type = TypeIntervenant.PARTICULIER;
                    break;
            }
            users.add(new Intervenant(data[0], data[1], data[2], type, data[4]));
        }
        reader.close();
        return users;
    }

    public static Utilisateur getUtilisateur(String email, String password) throws IOException{
        ArrayList<Utilisateur> users = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:users) {
            if (u.getAdresseCourriel().equals(email) && u.getMotDePasse().equals(password)) {
                return u;
            }
        }
        return null;
    }

}
