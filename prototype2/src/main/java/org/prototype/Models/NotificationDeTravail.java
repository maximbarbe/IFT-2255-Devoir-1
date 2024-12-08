package org.prototype.Models;

import java.util.ArrayList;

/**
 * Classe représentant une notification spécifique liée à un travail.
 * Hérite de la classe {@link Notification}.
 */
public class NotificationDeTravail extends Notification{
    
     /** L'identifiant du travail associé à cette notification. */
    private final String travailID;
    
    
     /**
     * Constructeur pour créer une notification de travail avec les informations spécifiées.
     *
     * @param titre          Le titre de la notification.
     * @param description    La description détaillée de la notification.
     * @param intervenant    L'identifiant de l'intervenant responsable du travail.
     * @param travailID      L'identifiant du travail associé à cette notification.
     * @param date           La date de création de la notification au format ISO (yyyy-MM-dd).
     * @param notificationID L'identifiant unique de la notification.
     */
    public NotificationDeTravail(String titre, String description, String intervenant, String travailID, String date, String notificationID) {
        super(titre, description, intervenant, notificationID, date);
        this.travailID = travailID;
    }

    /**
     * Retourne l'identifiant du travail associé à cette notification.
     *
     * @return L'identifiant du travail.
     */
    public String getTravailID() {
        return this.travailID;
    }


}
