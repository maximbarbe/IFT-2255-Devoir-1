package org.prototype.Models;

/**
 * Classe représentant une candidature soumise par les intervenants pour répondre à une requête de travail.
 */
public class Candidature {
    /** L'identifiant de la requête associée à la candidature. */
    private final String requeteID;

    /** L'identifiant de l'intervenant qui a soumis la candidature. */
    private final String intervenant;

    /** La date de début proposée pour la réalisation du travail. */
    private final String dateDebut;

     /** La date de fin proposée pour la réalisation du travail. */
    private final String dateFin;
    
     /** Le statut de la candidature, initialisé à {@link StatutCandidature#EN_ATTENTE}. */
    private StatutCandidature statut = StatutCandidature.EN_ATTENTE;

    /** Le message associé à la candidature. */
    private String message;

    /**
     * Constructeur pour créer une candidature.
     *
     * @param requeteID   L'identifiant de la requête associée.
     * @param intervenant L'identifiant de l'intervenant.
     * @param dateDebut   La date de début au format ISO (yyyy-MM-dd).
     * @param dateFin     La date de fin au format ISO (yyyy-MM-dd).
     * @param message     Un message facultatif de l'intervenant.
     */ 
    public Candidature(String requeteID, String intervenant, String dateDebut, String dateFin, String message) {
        this.requeteID = requeteID;
        this.intervenant = intervenant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.message = message;
    }

    /**
     * Définit le message de la candidature.
     *
     * @param msg Le message à associer à la candidature.
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * Retourne le message associé à la candidature.
     *
     * @return Le message de la candidature.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Retourne la date de fin proposée pour le travail.
     *
     * @return La date de fin au format ISO (yyyy-MM-dd).
     */
    public String getDateFin() {
        return dateFin;
    }


    /**
     * Retourne la date de début proposée pour le travail.
     *
     * @return La date de début au format ISO (yyyy-MM-dd).
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Retourne l'identifiant de la requête associée à la candidature.
     *
     * @return L'identifiant de la requête.
     */
    public String getRequeteID() {
        return requeteID;
    }

    /**
     * Retourne l'identifiant de l'intervenant ayant soumis la candidature.
     *
     * @return L'identifiant de l'intervenant.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

     /**
     * Retourne le statut actuel de la candidature.
     *
     * @return Le statut de la candidature ({@link StatutCandidature}).
     */
    public StatutCandidature getStatut() {
        return this.statut;
    }

    /**
     * Définit le statut de la candidature.
     *
     * @param val Le nouveau statut de la candidature.
     */
    public void setStatut(StatutCandidature val) {
        this.statut = val;
    }
}
