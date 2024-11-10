package org.prototype.Controllers;


import org.prototype.Models.Requete;
import org.prototype.Models.RequeteStatut;
import org.prototype.Models.TypeTravail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RequeteController {

    public static void closeRequete(Requete r) {
        r.setStatut(RequeteStatut.FERMEE);
    }

    private static boolean isDateValid(String date) {
        try {
            LocalDate dateEsperee = LocalDate.parse(date);
            LocalDate now = LocalDate.now();
            if (now.until(dateEsperee, ChronoUnit.DAYS) < 0) {
                return false;

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void saveRequete(Requete r) throws Exception{

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/requetes.csv", true));
        writer.append(r.getRequeteId() + ","+r.getTitre()+","+r.getDescription()+","+r.getType().toString()+","+r.getDateDebutEspere()+","+r.getStatut().toString()+","+r.getUserID()+","+r.getQuartier()+"\n");
        writer.close();

    }

    public static boolean creerRequete(String titre, String description, String typeRequete, String dateDebutEspere, String userID, String quartier){

        TypeTravail type = null;
        for (TypeTravail t:TypeTravail.values()) {
            if (typeRequete.equals(t.toString())) {
                type = t;
                break;
            }
        }
        if (type == null || !isDateValid(dateDebutEspere)) {
            return false;

        } else {
            try {
                ArrayList<Requete> requetes = RequeteController.getRequetes();
                if (requetes.size() == 0) {
                    saveRequete(new Requete(0, titre, description, type, dateDebutEspere, userID, quartier));
                } else {
                    saveRequete(new Requete(requetes.get(requetes.size() -1).getRequeteId() + 1, titre, description, type, dateDebutEspere, userID, quartier));
                }

            } catch (Exception e) {
                return false;
            }

            return true;
        }


    }
    public static ArrayList<Requete> getRequetes(){
        ArrayList<Requete> requetes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/requetes.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String titre = data[1];
                String description = data[2];
                TypeTravail type = null;
                for (TypeTravail t:TypeTravail.values()) {
                    if (data[3].equals(t.toString())) {
                        type = t;
                        break;
                    }
                }
                String dateDebutEspere = data[4];
                RequeteStatut statut = data[5].equals(RequeteStatut.OUVERTE.toString())?RequeteStatut.OUVERTE:RequeteStatut.FERMEE;
                String userID = data[6];
                String quartier = data[7];
                requetes.add(new Requete(id, titre, description, type, dateDebutEspere, userID, quartier));
                requetes.get(requetes.size() - 1).setStatut(statut);

            }
        } catch (Exception e) {};

        return requetes;
    }

    public static ArrayList<Requete> getRequeteByType(String type) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes()) {
            if (r.getType().toString().equals(type)) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }
    public static ArrayList<Requete> getRequeteByQuartier(String quartier) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes()) {
            if (r.getQuartier().toLowerCase().equals(quartier.toLowerCase())) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }

    public static ArrayList<Requete> getRequeteByDate(String date) {
        ArrayList<Requete> requetesFiltrees = new ArrayList<>();
        for (Requete r:getRequetes()) {
            if (r.getDateDebutEspere().toLowerCase().equals(date.toLowerCase())) {
                requetesFiltrees.add(r);
            }
        }
        return requetesFiltrees;
    }
}
