package org.prototype.Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import com.password4j.*;
import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Classe qui s'occupe des opérations concernant les résidents
 */
public class ResidentController{


    private static String residentFile = "src/residents.csv";

    public static String getResidentFile() {
        return residentFile;
    }

    public static void setResidentFile(String file) {
        residentFile = file;
    }

    private static HashMap<String, String> convertPostalCode = new HashMap<>();


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

    private static boolean saveResident(Resident r) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(residentFile, true));
            writer.append(r.getNomComplet() + ";"+r.getAdresseCourriel()+";"+r.getMotDePasse()+";"+r.getDateDeNaissance()+";"+r.getNumTelephone()+";"+r.getAdresseResidentielle()+";"+r.getQuartier());
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;

    }


    private static LocalDate isDateFormatValid(String dateDeNaissance) {
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(dateDeNaissance);
        } catch (Exception e) {
            return null;
        }
        return birthday;
    }

    private static boolean isAtLeastSixteen(LocalDate birthday) {
        LocalDate cur = LocalDate.now();
        if (birthday.until(cur, ChronoUnit.YEARS) >= 16) {
            return true;
        } else return false;
    }

    private static boolean isPostalCodeFormatValid(String postalCode) {
        Pattern postalCodePattern = Pattern.compile("^[a-zA-Z0-9]{3} [a-zA-Z0-9]{3}$");
        if (postalCodePattern.matcher(postalCode).matches()) {
            return true;
        } else return false;
    }


    /**
     *
     * @param nomComplet
     * @param dateDeNaissance
     * @param adresseCourriel
     * @param motDePasse
     * @param adresseResidentielle
     * @param numTelephone
     * @return
     */
    public static int createResident(String nomComplet, String dateDeNaissance, String adresseCourriel, String motDePasse, String adresseResidentielle, String numTelephone, String postalCode) {
        if (doesEmailExist(adresseCourriel)) {
            return 1;
        }
        if (!isEmailFormatValid(adresseCourriel)) {
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
        if (!saveResident(new Resident(nomComplet, adresseCourriel, hashed_password, dateDeNaissance, numTelephone, adresseResidentielle, quartier))) {
            return 7;
        }

        return 0;
    }

    /**
     * Fetch la liste des résidents à partir d'un fichier prédéfini
     * @return - La liste des résidents
     */
    public static ArrayList<Resident> getResidents(){
        try {
            ArrayList<Resident> residents = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(residentFile));
            String line;
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(";");
                residents.add(new Resident(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
            }
            reader.close();
            return residents;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static boolean isEmailFormatValid(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+$");
        if (emailPattern.matcher(email).matches()) {
            return true;
        } else return false;
    }

    private static boolean doesEmailExist(String adresseCourriel) {
        ArrayList<Utilisateur> utilisateurs = UtilisateurController.getUtilisateurs();
        for (Utilisateur u:utilisateurs) {
            if (u.getAdresseCourriel().toLowerCase().equals(adresseCourriel.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Resident> getResidentsByQuartier(String quartier) {
        ArrayList<Resident> residents = getResidents();
        residents.removeIf(r -> r.getQuartier().toLowerCase().equals(quartier.toLowerCase()) == false);
        return residents;
    }

}
