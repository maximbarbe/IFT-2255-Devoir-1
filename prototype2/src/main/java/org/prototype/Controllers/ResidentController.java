package org.prototype.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import com.password4j.*;
import org.prototype.MaVille;
import org.prototype.Models.Notification;
import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * Classe responsable des opérations liées aux résidents, telles que la création,
 * la mise à jour et la récupération des informations des résidents.
 */
public class ResidentController{
    
    /**
     * Chemin du fichier contenant les informations des résidents.
     */
    private static String residentFile = "src/residents.csv";

    /**
     * Obtient le chemin du fichier des résidents.
     *
     * @return Le chemin du fichier des résidents.
     */
    public static String getResidentFile() {
        return residentFile;
    }

    /**
     * Définit le chemin du fichier des résidents.
     *
     * @param file Le nouveau chemin du fichier des résidents.
     */
    public static void setResidentFile(String file) {
        residentFile = file;
    }

    private static HashMap<String, String> convertPostalCode = new HashMap<>();


    /**
     * Initialise la table de hachage pour la conversion des codes postaux en quartiers.
     */
    public static void initHashMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/codesPostaux.csv"));
            String line;
            while ((line=reader.readLine()) != null) {
                String[] data = line.split(",");
                convertPostalCode.put(data[0].toLowerCase(), data[1].toLowerCase());
            }
        } catch (Exception e) {
            return;
        }
    }



 /**
 * Sauvegarde un résident dans le fichier des résidents.
 *
 * @param r Le résident à sauvegarder.
 * @return {@code true} si le résident a été sauvegardé avec succès, sinon {@code false}.
 */
    private static boolean saveResident(Resident r) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(residentFile, true));
            writer.append(r.getNomComplet() + ";"+r.getAdresseCourriel()+";"+r.getMotDePasse()+";"+r.getDateDeNaissance()+";"+r.getNumTelephone()+";"+r.getAdresseResidentielle()+";"+r.getQuartier()+";"+ String.join(",", r.getSeenNotifications()) + ";" + r.getCreationDate() + "\n");
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;

    }



/**
 * Vérifie si une chaîne de caractères représente une date valide au format ISO (yyyy-MM-dd).
 *
 * @param dateDeNaissance La date de naissance sous forme de chaîne de caractères.
 * @return Un objet {@link LocalDate} si le format est valide, sinon {@code null}.
 */
    private static LocalDate isDateFormatValid(String dateDeNaissance) {
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(dateDeNaissance);
        } catch (Exception e) {
            return null;
        }
        return birthday;
    }


/**
 * Vérifie si la date de naissance correspond à un âge d'au moins 16 ans.
 *
 * @param birthday La date de naissance sous forme d'objet {@link LocalDate}.
 * @return {@code true} si l'âge est d'au moins 16 ans, sinon {@code false}.
 */
    private static boolean isAtLeastSixteen(LocalDate birthday) {
        LocalDate cur = LocalDate.now();
        if (birthday.until(cur, ChronoUnit.YEARS) >= 16) {
            return true;
        } else return false;
    }


/**
 * Vérifie si un code postal respecte le format valide (par exemple, "A1B 2C3").
 *
 * @param postalCode Le code postal à vérifier.
 * @return {@code true} si le format est valide, sinon {@code false}.
 */
    private static boolean isPostalCodeFormatValid(String postalCode) {
        Pattern postalCodePattern = Pattern.compile("^[a-zA-Z0-9]{3} [a-zA-Z0-9]{3}$");
        if (postalCodePattern.matcher(postalCode).matches()) {
            return true;
        } else return false;
    }

 /**
     * Crée un nouveau résident et l'enregistre dans le fichier des résidents.
     *
     * @param nomComplet            Le nom complet du résident.
     * @param dateDeNaissance       La date de naissance du résident au format {@link String}.
     * @param adresseCourriel       L'adresse courriel du résident.
     * @param motDePasse            Le mot de passe du résident.
     * @param adresseResidentielle  L'adresse résidentielle du résident.
     * @param numTelephone          Le numéro de téléphone du résident.
     * @param postalCode            Le code postal du résident.
     * @param creationDate          La date de création du compte.
     * @return Un code d'état : 0 si succès, 1 si l'email existe déjà, 2 si le format de l'email est invalide ou du password,
     * 3 si la date de naissance est invalide, 4 si l'âge est inférieur à 16 ans, 5 si le code postal est invalide,
     * 6 si le code postal ne correspond à aucun quartier, 7 si l'enregistrement échoue.
     */
    public static int createResident(String nomComplet, String dateDeNaissance, String adresseCourriel, String motDePasse, String adresseResidentielle, String numTelephone, String postalCode, String creationDate) {
        if (doesEmailExist(adresseCourriel)) {
            return 1;
        }
        if (!isEmailFormatValid(adresseCourriel) || motDePasse.length() < 8) {
            return 2;
        }
        LocalDate birthday = isDateFormatValid(dateDeNaissance);
        if (birthday == null) {
            return 3;
        }
        if (!isAtLeastSixteen(birthday)) {
            return 4;
        }

        if (!isPostalCodeFormatValid(postalCode)) {
            return 5;
        }

        String quartier;
        if ((quartier = convertPostalCode.getOrDefault(postalCode.split(" ")[0].toLowerCase(), null)) == null) {
            return 6;
        }
        // Initialement, je voulais utiliser la fonction withBcrypt fournie et utiliser une quantité de salt différente.
        // Toutefois, cela ne marchait pas, donc j'ai dû utiliser les paramètres dans l'exemple de la documentation.
        // C'est-à-dire, utiliser une longueur de salt de 12 et la fonction withArgon2()
        // La documentation peut être trouvée ici:
        // https://github.com/Password4j/password4j
        // Source: Bertoldi, D. (2024, 31 juillet). password4j. GitHub. https://github.com/Password4j/password4j.
        String hashed_password = Password.hash(motDePasse).addRandomSalt(12).withArgon2().getResult();
        if (!saveResident(new Resident(nomComplet, adresseCourriel, hashed_password, dateDeNaissance, numTelephone, adresseResidentielle, quartier, new HashSet<>(), creationDate))) {
            return 7;
        }

        return 0;
    }

    /**
     * Récupère la liste de tous les résidents depuis le fichier CSV.
     *
     * @return Une liste de {@link Resident}.
     */
    public static ArrayList<Resident> getResidents(){
        try {
            ArrayList<Resident> residents = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(residentFile));
            String line;
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(";");
                HashSet<String> seenNotifications = new HashSet<>();
                if (!data[7].equals("")){
                    String[] ids = data[7].split(",");
                    for (String id:ids) {seenNotifications.add(id);};
                }
                residents.add(new Resident(data[0], data[1], data[2], data[3], data[4], data[5], data[6], seenNotifications, data[8]));
            }
            reader.close();
            return residents;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

/**
 * Vérifie si une adresse courriel respecte le format valide.
 *
 * @param email L'adresse courriel à vérifier.
 * @return {@code true} si l'adresse courriel est valide, sinon {@code false}.
 */
    private static boolean isEmailFormatValid(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$");
        if (emailPattern.matcher(email).matches()) {
            return true;
        } else return false;
    }


/**
 * Vérifie si une adresse courriel existe déjà dans la liste des utilisateurs.
 *
 * @param adresseCourriel L'adresse courriel à vérifier.
 * @return {@code true} si l'adresse courriel existe déjà, sinon {@code false}.
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
 * Récupère une liste de résidents vivant dans un quartier spécifique.
 *
 * @param quartier Le nom du quartier pour lequel récupérer les résidents.
 * @return Une liste de {@link Resident} associés au quartier spécifié.
 */
    public static ArrayList<Resident> getResidentsByQuartier(String quartier) {
        ArrayList<Resident> residents = getResidents();
        residents.removeIf(r -> r.getQuartier().toLowerCase().equals(quartier.toLowerCase()) == false);
        return residents;
    }


     /**
     * Met à jour les informations d'un résident dans le fichier CSV.
     *
     * @param r Le {@link Resident} à mettre à jour.
     */
    public static void updateResident(Resident r) {
        try {
            ArrayList<String> residents = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(residentFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (!data[1].equals(r.getAdresseCourriel())) {
                    residents.add(line);
                }
            }
            residents.add(r.getNomComplet() + ";"+r.getAdresseCourriel()+";"+r.getMotDePasse()+";"+r.getDateDeNaissance()+";"+r.getNumTelephone()+";"+r.getAdresseResidentielle()+";"+r.getQuartier() + ";" + String.join(",", r.getSeenNotifications())+ ";" + r.getCreationDate()+ "\n");
            BufferedWriter writer = new BufferedWriter(new FileWriter(residentFile));
            writer.write(String.join("\n", residents));
            writer.close();
        } catch (Exception e) {

        }
    }

    /**
     * Met à jour les notifications vues pour le résident actuellement connecté.
     *
     * @param notifications Une liste de {@link Notification} à marquer comme vues.
     */
    public static void updateSeenNotifications(ArrayList<Notification> notifications) {
        if (notifications.size() == 0) {
            return;
        }
        Resident cur = (Resident) MaVille.getCurUser();
        HashSet<String> seenNotis = cur.getSeenNotifications();
        notifications.forEach(e -> seenNotis.add(e.getNotificationID()));
        cur.setSeenNotifications(seenNotis);
        updateResident(cur);
    }
}
