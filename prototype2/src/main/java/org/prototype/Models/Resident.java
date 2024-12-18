package org.prototype.Models;

import java.util.HashSet;

/**
 * Classe représentant un résident qui utilise l'application, contient toutes les caractéristiques/méthodes propres aux résidents.
 */
public class Resident extends Utilisateur{

    /** La date de naissance du résident au format ISO (yyyy-MM-dd). */
    private String dateDeNaissance;

    /** Le numéro de téléphone du résident. */
    private String numTelephone;

    /** L'adresse résidentielle du résident. */
    private String adresseResidentielle;

    /** Les identifiants des notifications déjà vues par le résident. */
    private HashSet<String> seenNotifications;

    /** La date de création du compte du résident au format ISO (yyyy-MM-dd). */
    private String creationDate;

   /** Le quartier dans lequel réside le résident. */ 
    private String quartier;


    /**
     * Constructeur pour créer un résident avec les informations spécifiées.
     *
     * @param nomComplet           Le nom complet du résident.
     * @param adresseCourriel      L'adresse courriel du résident.
     * @param motDePasse           Le mot de passe du résident (hashé).
     * @param dateDeNaissance      La date de naissance du résident au format ISO (yyyy-MM-dd).
     * @param numTelephone         Le numéro de téléphone du résident.
     * @param adresseResidentielle L'adresse résidentielle du résident.
     * @param quartier             Le quartier du résident.
     * @param notifications        Les identifiants des notifications déjà vues par le résident.
     * @param creationDate         La date de création du compte au format ISO (yyyy-MM-dd).
     */ 
    public Resident(String nomComplet, String adresseCourriel, String motDePasse, String dateDeNaissance, String numTelephone, String adresseResidentielle, String quartier, HashSet<String> notifications, String creationDate) {
        super(nomComplet, adresseCourriel, motDePasse);
        this.dateDeNaissance = dateDeNaissance;
        this.numTelephone = numTelephone;
        this.adresseResidentielle = adresseResidentielle;
        this.quartier = quartier;
        this.seenNotifications = notifications;
        this.creationDate = creationDate;
    }


    /**
     * Modifie la date de création du compte du résident.
     *
     * @param creationDate La nouvelle date de création au format ISO (yyyy-MM-dd).
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Retourne la date de création du compte du résident.
     *
     * @return La date de création au format ISO (yyyy-MM-dd).
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Retourne les identifiants des notifications déjà vues par le résident.
     *
     * @return Une copie des identifiants des notifications vues.
     */
    public HashSet<String> getSeenNotifications() {
        return (HashSet<String>) this.seenNotifications.clone();
    }

    /**
     * Modifie les identifiants des notifications déjà vues par le résident.
     *
     * @param notifications La nouvelle liste des identifiants de notifications vues.
     */
    public void setSeenNotifications(HashSet<String> notifications) {
        this.seenNotifications = notifications;  
    }

    /**
     * Retourne le quartier du résident.
     *
     * @return Le quartier du résident.
     */
    public String getQuartier() {
        return quartier;
    }


    /**
     * Modifie le quartier du résident.
     *
     * @param quartier Le nouveau quartier du résident.
     */ 
    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    /**
     * Modifie la date de naissance du résident.
     *
     * @param dateDeNaissance La nouvelle date de naissance au format ISO (yyyy-MM-dd).
     */
    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * Modifie le numéro de téléphone du résident.
     *
     * @param numTelephone Le nouveau numéro de téléphone.
     */
    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    /**
     * Modifie l'adresse résidentielle du résident.
     *
     * @param adresseResidentielle La nouvelle adresse résidentielle.
     */
    public void setAdresseResidentielle(String adresseResidentielle) {
        this.adresseResidentielle = adresseResidentielle;
    }
 
    /**
     * Retourne la date de naissance du résident.
     *
     * @return La date de naissance au format ISO (yyyy-MM-dd).
     */
    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * Retourne l'adresse résidentielle du résident.
     *
     * @return L'adresse résidentielle.
     */
    public String getAdresseResidentielle() {
        return adresseResidentielle;
    }

    /**
     * Retourne le numéro de téléphone du résident.
     *
     * @return Le numéro de téléphone du résident.
     */
    public String getNumTelephone() {
        return numTelephone;
    }
}
