package org.prototype.Models;


/**
 * Classe abstraite qui représente un utilisateur quelconque.
 * Cette classe contient les informations de base pour tous les types d'utilisateurs.
 */
public abstract class Utilisateur {

    /** Le nom complet de l'utilisateur. */ 
    private String nomComplet;

    /** L'adresse courriel de l'utilisateur. */ 
    private String adresseCourriel;

    /** Le mot de passe de l'utilisateur (hashé). */ 
    private String motDePasse;
    

    /**
     * Constructeur pour créer un utilisateur avec les informations spécifiées.
     *
     * @param nomComplet      Le nom complet de l'utilisateur.
     * @param adresseCourriel L'adresse courriel de l'utilisateur.
     * @param motDePasse      Le mot de passe de l'utilisateur (hashé).
     */
    public Utilisateur(String nomComplet, String adresseCourriel, String motDePasse) {
        this.nomComplet = nomComplet;
        this.adresseCourriel = adresseCourriel;
        this.motDePasse = motDePasse;
    }

   /**
     * Retourne le nom complet de l'utilisateur.
     *
     * @return Le nom complet de l'utilisateur.
     */ 
    public String getNomComplet() {
        return nomComplet;
    }

   /**
     * Retourne l'adresse courriel de l'utilisateur.
     *
     * @return L'adresse courriel de l'utilisateur.
     */ 
    public String getAdresseCourriel() {
        return adresseCourriel;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur (hashé).
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Modifie l'adresse courriel de l'utilisateur.
     *
     * @param adresseCourriel La nouvelle adresse courriel de l'utilisateur.
     */
    public void setAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     *
     * @param motDePasse Le nouveau mot de passe de l'utilisateur (hashé).
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    
   /**
     * Modifie le nom complet de l'utilisateur.
     *
     * @param nomComplet Le nouveau nom complet de l'utilisateur.
     */ 
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }



}
