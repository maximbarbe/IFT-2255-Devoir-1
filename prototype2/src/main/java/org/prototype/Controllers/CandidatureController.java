package org.prototype.Controllers;

import org.prototype.Models.Candidature;
import org.prototype.Models.StatutCandidature;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * Contrôleur responsable de la création, de la modification, et de la récupération des candidatures.
 */
public class CandidatureController {

    /**
     * Chemin du fichier contenant les candidatures.
     */
    private static String candidaturesFile = "src/candidatures.csv";

    /**
     * @return - Le chemin du fichier contenant les candidatures.
     * */
    public static String getCandidaturesFile() {
        return candidaturesFile;
    }

    /**
     * Change le chemin vers le fichier des candidatures
     * @param newFile Le chemin du nouveau fichier des candidatures
     * */
    public static void setCandidaturesFile(String newFile) {
        candidaturesFile = newFile;
    }

    /**
     * Récupère toutes les candidatures depuis le fichier CSV.
     *
     * @return Une liste de {@link Candidature}.
     */
    private static ArrayList<Candidature> getCandidatures(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(candidaturesFile));
            ArrayList<Candidature> candidatures = new ArrayList<>();
            String line;
            while ((line=reader.readLine()) != null) {
                String[] data = line.split(",");
                StatutCandidature statut = StatutCandidature.EN_ATTENTE;
                for (StatutCandidature s:StatutCandidature.values()) {
                    if (data[4].equals(s.toString())) {
                        statut = s;
                        break;
                    }
                }
                if (data.length == 5) {
                    candidatures.add(new Candidature(data[0], data[3], data[1], data[2], ""));
                } else {
                    candidatures.add(new Candidature(data[0], data[3], data[1], data[2], data[5]));
                }

                candidatures.get(candidatures.size() - 1).setStatut(statut);
            }
            return candidatures;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * Récupère les candidatures associées à un intervenant spécifique.
     *
     * @param intervenant Le nom de l'intervenant.
     * @return Une liste de {@link Candidature} correspondant à l'intervenant.
     */
    public static ArrayList<Candidature> getCandidaturesByIntervenant(String intervenant) {
        ArrayList<Candidature> candidatures = getCandidatures();
        candidatures.removeIf(c -> !c.getIntervenant().equals(intervenant));
        return candidatures;
    };




    /**
     * Vérifie si les dates de début et de fin sont valides.
     *
     * @param startDate La date de début au format {@link String}.
     * @param endDate   La date de fin au format {@link String}.
     * @return {@code true} si les dates sont valides, sinon {@code false}.
     */
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


    /**
     * Vérifie si une candidature peut être envoyée pour une requête donnée par un intervenant spécifique.
     *
     * @param requeteID   L'identifiant de la requête.
     * @param intervenant Le nom de l'intervenant.
     * @return {@code true} si la candidature peut être envoyée, sinon {@code false}.
     */
    private static boolean canSendCandidature(String requeteID, String intervenant) {
        ArrayList<Candidature> candidature = getCandidaturesByIntervenant(intervenant);
        for (Candidature c:candidature) {
            if (c.getRequeteID().equals(requeteID)) {
                return false;
            }
        }
        return true;
    }




    /**
     * Crée une nouvelle candidature avec le statut par défaut.
     *
     * @param requeteID   L'identifiant de la requête.
     * @param dateDebut   La date de début.
     * @param dateFin     La date de fin.
     * @param intervenant Le nom de l'intervenant.
     * @param msg         Le message de la candidature.
     * @return Un code d'état : 0 si succès, 1 si les dates sont invalides, 2 si la candidature existe déjà, 3 si l'enregistrement échoue.
     */
    public static int createCandidature(String requeteID, String dateDebut, String dateFin, String intervenant, String msg) {
        if (!areDatesValid(dateDebut, dateFin)) {
            return 1;
        }
        if (!canSendCandidature(requeteID, intervenant)) {
            return 2;
        }

        if (!saveCandidature(new Candidature(requeteID, intervenant, dateDebut, dateFin, msg))) {
            return 3;
        } else {
            return 0;
        }
    }



    /**
     * Supprime une candidature spécifique.
     *
     * @param c La candidature à supprimer.
     */
    public static void removeCandidature(Candidature c) {
        ArrayList<Candidature> candidatures = getCandidatures();
        candidatures.removeIf(candidature -> candidature.getRequeteID().equals(c.getRequeteID()) && candidature.getIntervenant().equals(c.getIntervenant()));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile));
            for (Candidature candidature:candidatures) {
                saveCandidature(candidature);
            }
        } catch (Exception e) {}
    }




    /**
     * Enregistre une candidature dans le fichier CSV.
     *
     * @param candidature La candidature à enregistrer.
     * @return {@code true} si l'enregistrement réussit, sinon {@code false}.
     */
    private static boolean saveCandidature(Candidature candidature) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile, true));

            writer.append(candidature.getRequeteID() + ","+candidature.getDateDebut()+","+candidature.getDateFin() + ","+candidature.getIntervenant() + ","+candidature.getStatut().toString() + "," + candidature.getMessage().replace(",","")+"\n");
            writer.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }




   /**
     * Récupère les candidatures associées à une requête spécifique.
     *
     * @param requeteID L'identifiant de la requête.
     * @return Une liste de {@link Candidature} correspondant à la requête.
     */ 
    public static ArrayList<Candidature> getCandidaturesByRequete(String requeteID) {
        ArrayList<Candidature> candidatures = getCandidatures();
        candidatures.removeIf(c -> !c.getRequeteID().equals(requeteID));
        return candidatures;
    }

    
     /**
     * Met à jour une candidature existante dans le fichier CSV.
     *
     * @param c La candidature mise à jour.
     */
    public static void updateCandidature(Candidature c) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(candidaturesFile));
            String line;
            ArrayList<String> lines = new ArrayList<>();
            boolean found = false;
            while ((line= reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(c.getRequeteID()) && data[3].equals(c.getIntervenant())) {
                    found=true;
                    continue;
                } else {
                    lines.add(line);
                }
            }
            if (!found) {
                return;
            }
            lines.add(c.getRequeteID() + ","+c.getDateDebut()+","+c.getDateFin() + ","+c.getIntervenant() + ","+c.getStatut().toString() + "," + c.getMessage());
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile));
            writer.write(String.join("\n", lines) + "\n");
            writer.close();
        } catch (Exception e) {return;}
    }


}
