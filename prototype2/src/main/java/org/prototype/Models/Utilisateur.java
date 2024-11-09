package org.prototype.Models;

import java.util.ArrayList;

public abstract class Utilisateur {
    private String nomComplet;
    private String adresseCourriel;
    private String motDePasse;

    


    public Utilisateur(String nomComplet, String adresseCourriel, String motDePasse) {
        this.nomComplet = nomComplet;
        this.adresseCourriel = adresseCourriel;
        this.motDePasse = motDePasse;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getAdresseCourriel() {
        return adresseCourriel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }



}
