package org.prototype.Controllers;

import org.prototype.Models.Candidature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * Classe qui s'occupe de contrôler la création, la modification et le "fetching" des candidatures
 */
public class CandidatureController {


    private static String candidaturesFile = "src/candidatureNotifications.csv";

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
                Boolean accepte;
                if (data[4].equals("null")) {
                    accepte=null;
                } else if (data[4].equals("true")) {
                    accepte=true;
                } else {
                    accepte=false;
                }
                candidatures.add(new Candidature(data[0], data[1], data[2], data[3], data[5]));
                candidatures.get(candidatures.size() - 1).setAcceptee(accepte);
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

    public static boolean createCandidature(String requeteID, String dateDebut, String dateFin, String intervenant, String msg) {
        if (!areDatesValid(dateDebut, dateFin)) {
            return false;
        }

        return saveCandidature(new Candidature(requeteID, dateDebut, dateFin, intervenant, msg));
    }



    private static boolean saveCandidature(Candidature candidature) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidaturesFile, true));
            String status;
            if (candidature.getAcceptee() == null) {
                status = "null";
            } else {
                status = candidature.getAcceptee().toString();
            }
            writer.append(candidature.getRequeteID() + ","+candidature.getIntervenant() + ","+candidature.getDateDebut()+","+candidature.getDateFin() + ","+status + "," + candidature.getMessage()+"\n");
            writer.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
