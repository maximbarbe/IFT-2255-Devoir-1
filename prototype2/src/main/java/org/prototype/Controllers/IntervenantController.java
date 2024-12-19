package org.prototype.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.password4j.*;
import org.prototype.Models.Intervenant;
import org.prototype.Models.TypeIntervenant;
import org.prototype.Models.Utilisateur;

/**
 * Classe responsable des opérations sur les objets {@link Intervenant},
 * telles que la création, la modification et la sauvegarde des intervenants dans un fichier.
 */
public class IntervenantController{

     /**
     * Chemin du fichier contenant les informations des intervenants.
     */
    private static String intervenantFile ="src/intervenants.csv";


    /**
     * Obtient le chemin du fichier des intervenants.
     *
     * @return Le chemin du fichier des intervenants.
     */
    public static String getIntervenantFile() {
        return intervenantFile;
    }


     /**
     * Définit le chemin du fichier des intervenants.
     *
     * @param file Le nouveau chemin du fichier des intervenants.
     */
    public static void setIntervenantFile(String file) {
        intervenantFile = file;
    }



    /**
     * Récupère la liste des intervenants depuis le fichier CSV.
     *
     * @return Une liste de {@link Intervenant}.
     */
    public static ArrayList<Intervenant> getIntervenants() {
        try {
            ArrayList<Intervenant> intervenants = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(intervenantFile));
            String line;
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(";");
                TypeIntervenant type = determineIntervenantType(data[3]);
                intervenants.add(new Intervenant(data[0], data[1], data[2], type, data[4]));
            }
            reader.close();   
            return intervenants;         
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }



    /**
     * Vérifie si un identifiant de ville existe déjà parmi les intervenants.
     *
     * @param id L'identifiant de la ville à vérifier.
     * @return {@code true} si l'identifiant existe déjà, sinon {@code false}.
     */
    private static boolean doesIdentifiantVilleExist(String id) {
        ArrayList<Intervenant> intervenants = getIntervenants();
        for (Intervenant i:intervenants) {
            if (i.getIdentifiantVille().equals(id)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Détermine le type d'intervenant à partir d'une chaîne de caractères.
     *
     * @param type La chaîne représentant le type d'intervenant.
     * @return Le {@link TypeIntervenant} correspondant.
     */
    private static TypeIntervenant determineIntervenantType(String type) {
        switch (type.toLowerCase()) {
            case "entrepreneur_prive":
                return TypeIntervenant.ENTREPRENEUR_PRIVE;
            case "entreprise_publique":
                return TypeIntervenant.ENTREPRISE_PUBLIQUE;
            case "particulier":
                return TypeIntervenant.PARTICULIER;
            default:
                return TypeIntervenant.AUTRE;
        }
    }



     /**
     * Enregistre un intervenant dans le fichier CSV.
     *
     * @param i L'intervenant à enregistrer.
     * @return {@code true} si l'enregistrement réussit, sinon {@code false}.
     */
    private static boolean saveIntervenant(Intervenant i) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(intervenantFile, true));
            writer.append(i.getNomComplet() + ";"+i.getAdresseCourriel()+";"+i.getMotDePasse()+";"+i.getType().toString()+";"+i.getIdentifiantVille() + "\n");
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Vérifie si le format de l'identifiant de ville est valide (8 chiffres).
     *
     * @param id L'identifiant à vérifier.
     * @return {@code true} si le format est valide, sinon {@code false}.
     */
    private static boolean identifiantFormatValid(String id) {
        Pattern format = Pattern.compile("^[0-9]{8}$");
        if (format.matcher(id).matches()) {
            return true;
        }
        return false;
    }


    /**
     * Crée un nouvel intervenant.
     *
     * @param nom             Le nom complet de l'intervenant.
     * @param type            Le type d'intervenant.
     * @param adresseCourriel L'adresse courriel de l'intervenant.
     * @param motDePasse      Le mot de passe de l'intervenant.
     * @param identifiantVille L'identifiant de ville à 8 chiffres.
     * @return Un code d'état : 0 si succès, 1 si l'identifiant de ville existe déjà, 2 si le format de l'identifiant est invalide,
     *         3 si l'adresse courriel existe déjà, 4 si le format de l'adresse courriel est invalide ou le mot de passe est invalide, 5 si l'enregistrement échoue.
     */
    public static int createIntervenant(String nom, String type, String adresseCourriel, String motDePasse, String identifiantVille) {
        if (doesIdentifiantVilleExist(identifiantVille)) {
            return 1;
        }
        if (!identifiantFormatValid(identifiantVille)) {
            return 2;
        }
        if (doesEmailExist(adresseCourriel)) {
            return 3;
        }
        if (!isEmailFormatValid(adresseCourriel) || motDePasse.length() < 8 || motDePasse.contains(";")) {
            return 4;
        }

        // Initialement, je voulais utiliser la fonction withBcrypt fournie et utiliser une quantité de salt différente.
        // Toutefois, cela ne marchait pas, donc j'ai dû utiliser les paramètres dans l'exemple de la documentation.
        // C'est-à-dire, utiliser une longueur de salt de 12 et la fonction withArgon2()
        // La documentation peut être trouvée ici:
        // https://github.com/Password4j/password4j
        // Source: Bertoldi, D. (2024, 31 juillet). password4j. GitHub. https://github.com/Password4j/password4j.
        if (!saveIntervenant(new Intervenant(nom.replace(";", ""), adresseCourriel.replace(";",""), Password.hash(motDePasse).addRandomSalt(12).withArgon2().getResult(), determineIntervenantType(type), identifiantVille))) {
            return 5;
        }

        return 0;
    }


    /**
     * Vérifie si une adresse courriel existe déjà parmi les utilisateurs.
     *
     * @param adresseCourriel L'adresse courriel à vérifier.
     * @return {@code true} si l'adresse existe déjà, sinon {@code false}.
     */
    private static boolean doesEmailExist(String adresseCourriel) {
        ArrayList<Utilisateur> utilisateurs = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:utilisateurs) {
            if (u.getAdresseCourriel().toLowerCase().equals(adresseCourriel.toLowerCase())) {
                return true;
            }
        }
        return false;
    }



     /**
     * Vérifie si le format de l'adresse courriel est valide.
     *
     * @param email L'adresse courriel à vérifier.
     * @return {@code true} si le format est valide, sinon {@code false}.
     */
    private static boolean isEmailFormatValid(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$");
        if (emailPattern.matcher(email).matches()) {
            return true;
        } else return false;
    }



}
