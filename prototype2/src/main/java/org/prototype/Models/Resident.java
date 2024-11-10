package org.prototype.Models;

public class Resident extends Utilisateur{
    private String dateDeNaissance;
    private String numTelephone;
    private String adresseResidentielle;


    private String quartier;
    public Resident(String nomComplet, String adresseCourriel, String motDePasse, String dateDeNaissance, String numTelephone, String adresseResidentielle, String quartier) {
        super(nomComplet, adresseCourriel, motDePasse);
        this.dateDeNaissance = dateDeNaissance;
        this.numTelephone = numTelephone;
        this.adresseResidentielle = adresseResidentielle;
        this.quartier = quartier;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public void setAdresseResidentielle(String adresseResidentielle) {
        this.adresseResidentielle = adresseResidentielle;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getAdresseResidentielle() {
        return adresseResidentielle;
    }

    public String getNumTelephone() {
        return numTelephone;
    }
}
