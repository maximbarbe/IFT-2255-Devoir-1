package org.prototype.Models;

public class Requete {
    private String titre;
    private String description;

    private TypeTravail type;

    private String dateDebutEspere;

    private RequeteStatut statut = RequeteStatut.OUVERTE;

    public Requete(String titre, String description, TypeTravail type, String dateDebutEspere) {
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.dateDebutEspere = dateDebutEspere;

    }

    public String getDescription() {
        return description;
    }

    public TypeTravail getType() {
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

    public void setType(TypeTravail type) {
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
}
