package org.prototype.Controllers;

import org.prototype.Models.Candidature;
import org.prototype.Models.Requete;
import org.prototype.Models.StatutCandidature;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * Classe qui s'occupe de contrôler la création, la modification et le "fetching" des candidatures
 */
public class CandidatureController {


    private static String candidaturesFile = "src/candidatures.csv";

    /**
     * Fetch les candidatures dans le fichier csv "candidatures.csv".
     * @return - La liste de toutes les candidatures
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

    public static ArrayList<Candidature> getCandidaturesByIntervenant(String intervenant) {
        ArrayList<Candidature> candidatures = getCandidatures();
        candidatures.removeIf(c -> !c.getIntervenant().equals(intervenant));
        return candidatures;
    };



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

    private static boolean canSendCandidature(String requeteID, String intervenant) {
        ArrayList<Candidature> candidature = getCandidaturesByIntervenant(intervenant);
        for (Candidature c:candidature) {
            if (c.getRequeteID().equals(requeteID)) {
                return false;
            }
        }
        return true;
    }
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


    public static void removeCandidature(Candidature c) {
        ArrayList<Candidature> candidatures = getCandidatures();
        candidatures.removeIf(candidature -> candidature.getRequeteID().equals(c.getRequeteID()));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile));
            for (Candidature candidature:candidatures) {
                saveCandidature(candidature);
            }
        } catch (Exception e) {}
    }

    private static boolean saveCandidature(Candidature candidature) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile, true));

            writer.append(candidature.getRequeteID() + ","+candidature.getDateDebut()+","+candidature.getDateFin() + ","+candidature.getIntervenant() + ","+candidature.getStatut().toString() + "," + candidature.getMessage()+"\n");
            writer.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
