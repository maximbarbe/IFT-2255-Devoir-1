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

/**
 * Classe qui s'occupe des opérations concernant l'objet <code>Intervenant</code> (i.e. la création, la modification ou sauvegarder dans un fichier)
 */
public class IntervenantController extends RegisterController{

    private static String intervenantFile ="src/intervenants.csv";
    /**
     * Fetch la liste des intervenants à partir d'un fichier de données prédéfini
     * @return - La liste des intervenants
     */

    public static String getIntervenantFile() {
        return intervenantFile;
    }


    public static void setIntervenantFile(String file) {
        intervenantFile = file;
    }
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

    private static boolean doesIdentifiantVilleExist(String id) {
        ArrayList<Intervenant> intervenants = getIntervenants();
        for (Intervenant i:intervenants) {
            if (i.getIdentifiantVille().equals(id)) {
                return true;
            }
        }
        return false;
    }

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


    private static boolean saveIntervenant(Intervenant i) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(intervenantFile, true));
            writer.append(i.getNomComplet() + ";"+i.getAdresseCourriel()+";"+i.getMotDePasse()+";"+i.getType().toString()+";"+i.getIdentifiantVille());
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean identifiantFormatValid(String id) {
        Pattern format = Pattern.compile("^[0-9]{8}$");
        if (format.matcher(id).matches()) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param nom
     * @param type
     * @param adresseCourriel
     * @param motDePasse
     * @param identifiantVille
     * @return
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
        if (!isEmailFormatValid(adresseCourriel)) {
            return 4;
        }

        // Initialement, je voulais utiliser la fonction withBcrypt fournie et utiliser une quantité de salt différente.
        // Toutefois, cela ne marchait pas, donc j'ai dû utiliser les paramètres dans l'exemple de la documentation.
        // C'est-à-dire, utiliser une longueur de salt de 12 et la fonction withArgon2()
        // La documentation peut être trouvée ici:
        // https://github.com/Password4j/password4j
        // Source: Bertoldi, D. (2024, 31 juillet). password4j. GitHub. https://github.com/Password4j/password4j.
        if (!saveIntervenant(new Intervenant(nom, adresseCourriel, Password.hash(motDePasse).addRandomSalt(12).withArgon2().getResult(), determineIntervenantType(type), identifiantVille))) {
            return 5;
        }

        return 0;
    }




}
