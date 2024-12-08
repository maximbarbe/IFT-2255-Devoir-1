package org.prototype.Models;
/**
 * Classe représentant une notification spécifique liée à une candidature.
 * Hérite de la classe {@link Notification}.
 */
public class NotificationDeCandidature extends Notification{
    
    /** L'identifiant de la requête associée à cette candidature. */
    private final String requeteID;

     /**
     * Constructeur pour créer une notification de candidature avec les informations spécifiées.
     *
     * @param titre          Le titre de la notification.
     * @param description    La description de la notification.
     * @param intervenant    L'identifiant de l'intervenant responsable de la candidature.
     * @param requete        L'identifiant de la requête associée à cette candidature.
     * @param date           La date de création de la notification au format ISO (yyyy-MM-dd).
     * @param notificationID L'identifiant unique de la notification.
     */
    public NotificationDeCandidature(String titre, String description, String intervenant, String requete, String date, String notificationID) {
        super(titre, description, intervenant, notificationID, date);
        this.requeteID = requete;
    }


    /**
     * Retourne l'identifiant de la requête associée à cette candidature.
     *
     * @return L'identifiant de la requête.
     */
    public String getRequeteID() {
        return this.requeteID;
    }
}
