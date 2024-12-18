package org.prototype.Models;


/**
 * Classe abstraite représentant une notification créée lors de la modification d'un projet
 * et destinée aux résidents.
 */
public abstract class Notification {

     /** L'identifiant unique de la notification. */
    private final String notificationID;

     /** Le titre de la notification. */
    private final String titre;

     /** La description détaillée de la notification. */
    private final String description;

     /** La date de création de la notification au format ISO (yyyy-MM-dd). */
    private final String date;

    /** L'intervenant responsable de la notification. */
    private final String intervenant;

     /**
     * Constructeur pour initialiser une notification avec les informations spécifiées.
     *
     * @param titre          Le titre de la notification.
     * @param description    La description détaillée de la notification.
     * @param intervenant    L'identifiant de l'intervenant responsable.
     * @param notificationID L'identifiant unique de la notification.
     * @param date           La date de création de la notification au format ISO (yyyy-MM-dd).
     */
    public Notification(String titre, String description, String intervenant, String notificationID, String date) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.intervenant = intervenant;
        this.notificationID = notificationID;
    }

    /**
     * Retourne l'identifiant de l'intervenant responsable de la notification.
     *
     * @return L'identifiant de l'intervenant.
     */
    public String getIntervenant() {
        return intervenant;
    }

    /**
     * Retourne le titre de la notification.
     *
     * @return Le titre de la notification.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne la description détaillée de la notification.
     *
     * @return La description de la notification.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne l'identifiant unique de la notification.
     *
     * @return L'identifiant de la notification.
     */
    public String getNotificationID() {
        return notificationID;
    }

    /**
     * Retourne la date de création de la notification.
     *
     * @return La date de la notification au format ISO (yyyy-MM-dd).
     */
    public String getDate() {
        return date;
    }
}
