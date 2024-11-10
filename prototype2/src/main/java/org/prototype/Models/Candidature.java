package org.prototype.Models;

public class Candidature {
    private final int requeteID;
    private final String intervenant;
    private final String dateDebut;
    private final String dateFin;

    public Candidature(int requeteID, String intervenant, String dateDebut, String dateFin) {
        this.requeteID = requeteID;
        this.intervenant = intervenant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public int getRequeteID() {
        return requeteID;
    }

    public String getIntervenant() {
        return intervenant;
    }
}
