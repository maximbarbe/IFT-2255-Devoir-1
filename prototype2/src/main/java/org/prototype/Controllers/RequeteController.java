package org.prototype.Controllers;


import org.prototype.Models.Requete;
import org.prototype.Models.RequeteStatut;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Classe qui s'occupe des opérations sur les requêtes, c'est-à-dire le fetching, la création, etc.
 */
public class RequeteController {


    private static String requetesFile ="src/requetes.csv";

    public static String getRequetesFile() {
        return requetesFile;
    }

    public static void setRequetesFile(String file) {
        requetesFile = file;
    }

    /**
     * Change le statut de la requête à <code>RequeteStatut.FERMEE</code>
     * @param r - La requête spécifique
     */
    public static void closeRequete(Requete r) {
        r.setStatut(RequeteStatut.FERMEE);
    }

    /**
     * Vérifie si une date
     * @param date - la date sous format de string
     * @return - <code>true</code> si la date est valide, sinon <code>false</code>
     */
    private static boolean isDateValid(String date) {
        try {
            // Si la date n'est pas du bon format, une exception sera lancée et on retournera faux.
            LocalDate dateEsperee = LocalDate.parse(date);
            LocalDate now = LocalDate.now();
            // Si la date espérée est avant la date d'aujourd'hui, la date n'est pas valide
            if (now.until(dateEsperee, ChronoUnit.DAYS) < 0) {
                return false;

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sauvegarde une requête en l'écrivant dans un fichier requetes.csv pré-existant
     * @param r - La requête à sauvegarder dans un fichier
     */
    private static void saveRequete(Requete r) throws Exception{

        BufferedWriter writer = new BufferedWriter(new FileWriter(requetesFile, true));
        writer.append(r.getRequeteId() + ","+r.getTitre()+","+r.getDescription()+","+r.getType().toString()+","+r.getDateDebutEspere()+","+r.getStatut().toString()+","+r.getUserID()+","+r.getQuartier()+"\n");
        writer.close();

    }

    /**
     * Crée une requête à partir des informations fournies
     * @param titre - Le titre de la requête
     * @param description - La description de la requête fourni par le résident
     * @param typeRequete - Le type de requête entré par le résident
     * @param dateDebutEspere - La date de début espéré sous format de string
     * @param userID - L'adresse email du résident ayant soumis la requête
     * @param quartier - Le quartier du résident ayant soumis la requête
     * @return - <code>true</code> si la requête a été créée avec succès, <code>false</code> sinon
     */
    public static boolean creerRequete(String titre, String description, String typeRequete, String dateDebutEspere, String userID, String quartier){

        String type = typeRequete;
        if (!isDateValid(dateDebutEspere)) {
            return false;

        } else {
            try {
                // L'appel à getRequetes() est uniquement utilisé pour déterminer le ID de notre nouvelle requête
                ArrayList<Requete> requetes = RequeteController.getRequetes(false);
                int id = 0;
                for (Requete r:requetes) {
                    id = Integer.max(id, r.getRequeteId() + 1);
                }
                saveRequete(new Requete(id, titre, description, type, dateDebutEspere, userID, quartier));

            } catch (Exception e) {
                return false;
            }

            return true;
        }


    }
    /**
     * Fetch la liste des requêtes à partir d'un fichier de données prédéfini
     * @return - La liste des requêtes
     */
    public static ArrayList<Requete> getRequetes(boolean removeClosed){
        ArrayList<Requete> requetes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(requetesFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String titre = data[1];
                String description = data[2];
                String type = data[3];
                String dateDebutEspere = data[4];
                RequeteStatut statut = data[5].equals(RequeteStatut.OUVERTE.toString())?RequeteStatut.OUVERTE:RequeteStatut.FERMEE;
                String userID = data[6];
                String quartier = data[7];
                requetes.add(new Requete(id, titre, description, type, dateDebutEspere, userID, quartier));
                requetes.get(requetes.size() - 1).setStatut(statut);

            }
        } catch (Exception e) {};
        // On enlève les requêtes fermées.
        if (removeClosed) {requetes.removeIf(r -> r.getStatut().equals(RequeteStatut.FERMEE));}

        return requetes;
    }

    /**
     * Fetch les requêtes et filtre la liste par un certain type.
     * @param type - Le type de requête par lequel filtrer
     * @return - La liste de requêtes filtrées
     */
    public static ArrayList<Requete> getRequeteByType(String type) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes(true)) {
            if (r.getType().toString().equals(type)) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }
    /**
     * Fetch les requêtes et filtre la liste par un certain quartier.
     * @param quartier - Le quartier de la requête par lequel filtrer
     * @return - La liste de requêtes filtrées
     */    
    public static ArrayList<Requete> getRequeteByQuartier(String quartier) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes(true)) {
            if (r.getQuartier().toLowerCase().equals(quartier.toLowerCase())) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }

    /**
     * Fetch les requêtes et filtre la liste par une certaine date.
     * @param date - Le date de requête par laquelle filtrer
     * @return - La liste de requêtes filtrées
     */
    public static ArrayList<Requete> getRequeteByDate(String date) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes(true)) {
            if (r.getDateDebutEspere().toLowerCase().equals(date.toLowerCase())) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }

    public static ArrayList<Requete> getRequeteByUser(String userID) {
        ArrayList<Requete> requetes = getRequetes(true);
        requetes.removeIf(r -> !r.getUserID().equals(userID));
        return requetes;
    }

    public static void fermerRequete(Requete requete) {
        ArrayList<Requete> requetes = getRequetes(false);
        requetes.removeIf(r-> r.getRequeteId() == requete.getRequeteId());
        requete.setStatut(RequeteStatut.FERMEE);
        requetes.add(requete);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(requetesFile));
            ArrayList<String> lines = new ArrayList<>();
            for (Requete r:requetes) {
                lines.add(r.getRequeteId() + ","+r.getTitre()+","+r.getDescription()+","+r.getType().toString()+","+r.getDateDebutEspere()+","+r.getStatut().toString()+","+r.getUserID()+","+r.getQuartier());
            }
            if (lines.size() != 0) {
                writer.write(String.join("\n", lines) + "\n");
            }
            writer.close();
        } catch (Exception e){};
    }
}
