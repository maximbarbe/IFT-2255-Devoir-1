package org.prototype.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.prototype.Models.Intervenant;
import org.prototype.Models.TypeIntervenant;

/**
 * Classe qui s'occupe des opérations concernant l'objet <code>Intervenant</code> (i.e. la création, la modification ou sauvegarder dans un fichier)
 */
public class IntervenantController {

    /**
     * Fetch la liste des intervenants à partir d'un fichier de données prédéfini
     * @return - La liste des intervenants
     */
    public static ArrayList<Intervenant> getIntervenants() {
        try {
            ArrayList<Intervenant> intervenants = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/intervenants.csv"));
            String line;
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
                intervenants.add(new Intervenant(data[0], data[1], data[2], type, data[4]));
            }
            reader.close();   
            return intervenants;         
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
