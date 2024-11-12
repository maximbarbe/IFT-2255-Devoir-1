package org.prototype.Models;

public class Requete {
    private final int requeteId;
    private String titre;
    private String description;

    private String type;

    private String dateDebutEspere;

    private String quartier;
    private RequeteStatut statut = RequeteStatut.OUVERTE;

    private final String userID;
    public Requete(int requeteId, String titre, String description, String type, String dateDebutEspere, String userID, String quartier) {
        this.requeteId = requeteId;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.dateDebutEspere = dateDebutEspere;
        this.userID = userID;
        this.quartier = quartier;

    }


    public String getQuartier() {
        return quartier;
    }
    public int getRequeteId() {
        return requeteId;
    }
    public String getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getTitre() {
        return titre;
    }

    public String getDateDebutEspere() {
        return dateDebutEspere;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateDebutEspere(String dateDebutEspere) {
        this.dateDebutEspere = dateDebutEspere;
    }

    public void setStatut(RequeteStatut statut) {
        this.statut = statut;
    }

    public RequeteStatut getStatut() {
        return statut;
    }
}
