package org.prototype.Models;

import java.util.ArrayList;

public class Travail {
    private String id;
    private String titre;
    private String description;
    private ArrayList<String> quartiers;
    private ArrayList<String> ruesAffectees;

    private String dateDebut;

    private String dateFin;

    private StatutProjet status = StatutProjet.PREVU;

    private String identifiantIntervenant;

    public Travail(String id, String titre, String description, ArrayList<String> quartiers, ArrayList<String> ruesAffectees, String dateDebut, String dateFin, String identifiantIntervenant) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.quartiers = quartiers;
        this.ruesAffectees = ruesAffectees;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.identifiantIntervenant = identifiantIntervenant;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setRuesAffectees(ArrayList<String> ruesAffectees) {
        this.ruesAffectees = ruesAffectees;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdentifiantIntervenant(String identifiantIntervenant) {
        this.identifiantIntervenant = identifiantIntervenant;
    }

    public void setQuartiers(ArrayList<String> quartiers) {
        this.quartiers = quartiers;
    }

    public void setStatus(StatutProjet status) {
        this.status = status;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentifiantIntervenant() {
        return identifiantIntervenant;
    }

    public String getTitre() {
        return titre;
    }

    public StatutProjet getStatus() {
        return status;
    }
    public String getStatusAsString() {
        return status.toString();
    }

    public ArrayList<String> getQuartiers() {
        return quartiers;
    }

    public ArrayList<String> getRuesAffectees() {
        return ruesAffectees;
    }
}
