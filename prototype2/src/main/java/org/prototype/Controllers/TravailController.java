package org.prototype.Controllers;

import org.prototype.API.ApiCaller;
import org.prototype.MaVille;
import org.prototype.Models.StatutProjet;
import org.prototype.Models.Travail;
import org.json.*;
import org.prototype.Models.TypeTravail;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.FileWriter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Classe qui gère toutes les opérations reliées aux travaux.
 */
public class TravailController {


    // Mapper qui servira de transformer la réponse de l'API en type de travail que notre programme peut reconnaître 
    private static HashMap<String, TypeTravail> constructionTypeMapper = null;
    private static String travauxFile = "src/travaux.csv";
    private static String quartiersFile = "src/codesPostaux.csv";
    public static String getTravauxFile() {
        return travauxFile;
    }

    public static void setTravauxFile(String file) {
        travauxFile = file;
    }

    /**
     * Initialise notre mapper.
     */
    private static void initHashMap(){
        if (constructionTypeMapper != null) {
            return;
        }
        constructionTypeMapper = new HashMap<>();
        constructionTypeMapper.put("Construction/rénovation sans excavation", TypeTravail.CONSTRUCTION_OU_RENOVATION);
        constructionTypeMapper.put("Autre", TypeTravail.AUTRE);
        constructionTypeMapper.put("S-3 Infrastructure souterraine majeure - Massifs et conduits", TypeTravail.SOUTERRAINS);
        constructionTypeMapper.put("Construction/rénovation avec excavation", TypeTravail.CONSTRUCTION_OU_RENOVATION);
        constructionTypeMapper.put("Égouts et aqueducs - Excavation", TypeTravail.SOUTERRAINS);
        constructionTypeMapper.put("Égouts et aqueducs - Réhabilitation", TypeTravail.ENTRETIEN_URBAIN);
        constructionTypeMapper.put("Entretien", TypeTravail.ENTRETIEN_URBAIN);
        constructionTypeMapper.put("S-3 Infrastructure souterraine majeure - Puits d'accès", TypeTravail.SOUTERRAINS);
        constructionTypeMapper.put("Égouts et aqueducs - Inspection et nettoyage", TypeTravail.ENTRETIEN_URBAIN);
        constructionTypeMapper.put("AS-2 Réseau aérosouterrain existant", TypeTravail.ENTRETIEN_DES_RESEAUX_DE_TELECOMMUNICATION);
        constructionTypeMapper.put("Réseaux routier - Réfection et travaux corrélatifs", TypeTravail.ROUTIERS);
        constructionTypeMapper.put("S-2 Infrastructure souterraine mineure ou équipement hors-sol - Réseaux électriques, télécommunications ou câbles des distributions ", TypeTravail.SOUTERRAINS);
        constructionTypeMapper.put("S-4 Déblocage de conduits souterrains", TypeTravail.SOUTERRAINS);


    }

    /**
     * Détermine le status du projet, dépend de la date. Si la date de fin est avant la date courante, alors le projet est terminé.
     * Si la date de début est après la date courante, alors le projet est prévu. Dans les autres cas, le projet est en cours.
     * @param date1 - La date du début du projet
     * @param date2 - La date de fin du projet
     * @return - Le statut du projet
     */
    private static StatutProjet determineProjectStatus(String date1, String date2) {
        LocalDate cur = LocalDate.now();
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        if (d2.isBefore(cur)) {
            return StatutProjet.TERMINE;
        } else if (d1.isAfter(cur)) {
            return StatutProjet.PREVU;
        } else {
            return StatutProjet.EN_COURS;
        }
    }

    /**
     * Parse la réponse obtenue à l'appel au service de la ville de Montréal
     * @param response - La réponse sous format de String
     * @return - La liste des travaux extraits
     */
    private static ArrayList<Travail> parseTravailApiCall(String response) {
        // La procédure du parsing de JSON est basé sur:
        // Source: obataku. (2012, 9 août). See my comment. You need to include the full org.json library when running as android.jar only contains stubs to compile [Commentaire sur le post de forum en ligne Parsing JSON string in Java.]. StackOverflow. https://stackoverflow.com/a/11875002.
        // Ce commentaire a essentiellement été utilisé juste pour apprendre comment utiliser la librairie en pratique.
        initHashMap();
        ArrayList<Travail> apiTravaux = new ArrayList<>();
        JSONObject res = new JSONObject(response);
        JSONArray travaux = res.getJSONObject("result").getJSONArray("records");
        for (int i =0; i< travaux.length();i ++) {
            JSONObject travail = travaux.getJSONObject(i);
            ArrayList<String> quartiers = new ArrayList<>();
            quartiers.add(travail.getString("boroughid").toLowerCase());
            Travail cur = new Travail(travail.getString("id"), travail.getString("reason_category"), travail.getString("occupancy_name"), quartiers, null, travail.getString("duration_start_date").split("T")[0], travail.getString("duration_end_date").split("T")[0], travail.get("organizationname").toString(), constructionTypeMapper.putIfAbsent(travail.getString("reason_category"), TypeTravail.AUTRE));
            cur.setStatus(determineProjectStatus(cur.getDateDebut(), cur.getDateFin()));
            apiTravaux.add(cur);
        }
        return apiTravaux;
    }

    /**
     * Va chercher la liste des travaux qui sont stockés localement dans un fichier
     * @return - La liste des travaux
     */
    public static ArrayList<Travail> getTravauxFromFile() {
        ArrayList<Travail> travaux =  new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(travauxFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String titre = data[1];
                String desc = data[2];
                String[] quartiers = data[3].split(";");
                String[] ruesAffectees = data[4].split(";");
                String dateDebut = data[5];
                String dateFin = data[6];
                String status = data[7];
                String intervenant = data[8];
                String type = data[9];
                StatutProjet statusProjet = null;
                TypeTravail typeTravail = null;
                for (TypeTravail typeT:TypeTravail.values()) {
                    if (type.equals(typeT.toString())) {
                        typeTravail = typeT;
                        break;
                    }
                }
                for (StatutProjet s:StatutProjet.values()) {
                    if (status.equals(s.toString())) {
                        statusProjet = s;
                    }
                }
                ArrayList<String> quartiersAffectes = new ArrayList<>();
                for (String s:quartiers) {
                    quartiersAffectes.add(s);
                }
                ArrayList<String> rues = new ArrayList<>();
                for (String s:ruesAffectees) {
                    rues.add(s);
                }
            Travail travail = new Travail(id, titre, desc, quartiersAffectes, rues, dateDebut, dateFin, intervenant,typeTravail);
            travail.setStatus(statusProjet);

            travaux.add(travail);
            }
            return travaux;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Vérifie si une date de donnée se situe dans les prochains 90 jours.
     * @param date - La date sous format de String
     * @return <code>true</code> si la date est dans les prochains 90 jours, <code>false</code> sinon
     */
    private static boolean startsInNextThreeMonths(String date) {
        LocalDate d = LocalDate.parse(date);
        LocalDate now = LocalDate.now();
        if (now.until(d, ChronoUnit.DAYS) <= 90) {
            return true;
        }
        return false;
    }

    /**
     * Fetch la liste des travaux, ceci sera composée des travaux obtenus à partir de l'API et de ceux créés en utilisant l'application
     * @return - La liste des travaux brute, non filtrée
     */
    public static ArrayList<Travail> getTravaux() {
        String apiRes = ApiCaller.get("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b");
        ArrayList<Travail> travaux = new ArrayList<>();
        if (apiRes != null) {
            travaux = parseTravailApiCall(apiRes);
        }
        ArrayList<Travail> filtered = new ArrayList<>();
        travaux.addAll(getTravauxFromFile());
        for (Travail t:travaux) {
            if (t.getStatus().equals(StatutProjet.EN_COURS)) {
                filtered.add(t);
            } else if (t.getStatus().equals(StatutProjet.PREVU) && startsInNextThreeMonths(t.getDateDebut())) {
                filtered.add(t);
            }

        }
        return filtered;
    }


    /**
     * Fetch la liste des travaux qui possèdent un certain type donné
     * @param type - Le type par lequel filtrer les travaux
     * @return - La liste de travaux filtrée
     */
    public static ArrayList<Travail> getTravauxByType(String type) {
        TypeTravail t = null;
        switch (type.toLowerCase()) {
            case "routiers":
                t = TypeTravail.ROUTIERS;
                break;
            case "gaz_ou_electricite":
                t = TypeTravail.GAZ_OU_ELECTRICITE;
                break;
            case "construction_ou_renovation":
                t = TypeTravail.CONSTRUCTION_OU_RENOVATION;
                break;
            case "entretien_paysager":
                t = TypeTravail.ENTRETIEN_PAYSAGER;
                break;
            case "transports_en_commun":
                t = TypeTravail.TRANSPORTS_EN_COMMUN;
                break;
            case "signalisation_et_eclairage":
                t = TypeTravail.SIGNALISATION_ET_ECLAIRAGE;
                break;
            case "souterrains":
                t = TypeTravail.SOUTERRAINS;
                break;
            case "residentiels":
                t = TypeTravail.RESIDENTIELS;
                break;
            case "entretien_urbain":
                t = TypeTravail.ENTRETIEN_URBAIN;
                break;
            case "entretien_des_reseaux_de_telecommunication":
                t = TypeTravail.ENTRETIEN_DES_RESEAUX_DE_TELECOMMUNICATION;
                break;
            case "autre":
                t = TypeTravail.AUTRE;
                break;
            default:
                t = null;
                break;
        }
        ArrayList<Travail> travaux = getTravaux();
        ArrayList<Travail> filtered = new ArrayList<>();
        for (Travail tr:travaux) {
            if (tr.getType().equals(t)) {
                filtered.add(tr);
            }
        }
        return filtered;
    }

    /**
     * Fetch les travaux qui se passent dans un quartier en particulier
     * @param quartier - Le quartier par lequel filtrer
     * @return - La liste des travaux filtrée
     */
    public static ArrayList<Travail> getTravauxByQuartier(String quartier) {
        ArrayList<Travail> travaux = getTravaux();
        ArrayList<Travail> filtered = new ArrayList<>();

        for (Travail t:travaux) {
            for (String s:t.getQuartiers()) {
                if (s.contains(quartier.toLowerCase())) {
                    filtered.add(t);
                    break;
                }
            }
        }

        return filtered;
    }

    public static boolean isEndDateValid(String endDate) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate end = LocalDate.parse(endDate);
            if (now.until(end, ChronoUnit.DAYS) < 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean doQuartiersExist(String[] quartiers) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(quartiersFile));
            String line;
            HashSet<String> quarts = new HashSet<>();
            while ((line= reader.readLine()) != null) {
                quarts.add(line.split(",")[1].toLowerCase());
            }
            for (String q:quartiers) {
                if (!quarts.contains(q.toLowerCase())) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private static boolean areDatesValid(String startDate, String endDate) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (now.until(end, ChronoUnit.DAYS) < 0 || start.until(end, ChronoUnit.DAYS) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static Travail creerTravail(String titre, String description, TypeTravail type, String[] quartiersAffectes, String[] ruesAffectees, String dateDebut, String dateFin) {
        if (!areDatesValid(dateDebut, dateFin)) {
            return null;
        }
        ArrayList<Travail> travauxEntrepris = getTravauxFromFile();
        int id = 0;
        for (Travail t:travauxEntrepris) {
            id = Integer.max(id, Integer.parseInt(t.getId())+ 1);
        }
        ArrayList<String> quartiers = new ArrayList<>();
        ArrayList<String> rues = new ArrayList<>();
        for (String q:quartiersAffectes) {
            quartiers.add(q);
        }
        for (String r:ruesAffectees) {
            rues.add(r);
        }
        Travail travail = new Travail(String.valueOf(id), titre, description, quartiers, rues, dateDebut, dateFin, MaVille.getCurUser().getAdresseCourriel(), type);
        saveTravail(travail);
        return travail;
    }

    private static void saveTravail(Travail travail) {
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(travauxFile, true));
            writer.append(travail.getId() + ","+travail.getTitre() + ","+travail.getDescription()+","+String.join(";",travail.getQuartiers())+","+ String.join(";", travail.getRuesAffectees())+","+travail.getDateDebut() + "," + travail.getDateFin() + "," + travail.getStatus().toString() + "," + travail.getIdentifiantIntervenant() + "," + travail.getType().toString() + "\n");
            writer.close();
            return;
        } catch (Exception e) {
            return;
        }
    }

    public static ArrayList<Travail> getTravauxByIntervenant(String intervenant) {
        ArrayList<Travail> travaux = getTravauxFromFile();
        travaux.removeIf(t -> !t.getIdentifiantIntervenant().equals(intervenant));
        return travaux;
    }

    public static void updateTravail(Travail newTravail) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(travauxFile));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.split(",")[0].equals(newTravail.getId())) {
                    lines.add(line);
                }
            }
            lines.add(newTravail.getId() + ","+newTravail.getTitre() + ","+newTravail.getDescription()+","+String.join(";",newTravail.getQuartiers())+","+ String.join(";", newTravail.getRuesAffectees())+","+newTravail.getDateDebut() + "," + newTravail.getDateFin() + "," + newTravail.getStatus().toString() + "," + newTravail.getIdentifiantIntervenant() + "," + newTravail.getType().toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter(travauxFile));
            writer.write(String.join("\n", lines) + "\n");
            writer.close();
        } catch (Exception e) {
            return;
        }
    }


}
