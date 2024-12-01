package org.prototype.Models;

/**
 * Classe représentant une candidature soumise par les intervenants
 */
public class Candidature {
    private final String requeteID;
    private final String intervenant;
    private final String dateDebut;
    private final String dateFin;
    // La classe Boolean nous permet de définir
    // null = Aucune réponse
    // True = Acceptée
    // False = Refusée
    private Boolean acceptee = null;

    private String message;

    public Candidature(String requeteID, String intervenant, String dateDebut, String dateFin, String message) {
        this.requeteID = requeteID;
        this.intervenant = intervenant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.message = message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getRequeteID() {
        return requeteID;
    }

    public String getIntervenant() {
        return intervenant;
    }

    public Boolean getAcceptee() {
        return acceptee;    
    }

    public void setAcceptee(boolean val) {
        this.acceptee = val;
    }
}
