package org.prototype.Models;


import java.util.ArrayList;

public class Resident extends Utilisateur{
    private String dateDeNaissance;
    private String numTelephone;
    private String adresseResidentielle;


    public Resident(String nomComplet, String adresseCourriel, String motDePasse, String dateDeNaissance, String numTelephone, String adresseResidentielle) {
        super(nomComplet, adresseCourriel, motDePasse);
        this.dateDeNaissance = dateDeNaissance;
        this.numTelephone = numTelephone;
        this.adresseResidentielle = adresseResidentielle;
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
