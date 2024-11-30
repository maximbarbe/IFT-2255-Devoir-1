package org.prototype.Models;

import java.util.ArrayList;

/**
 * Classe représentant une notification créé lors de la modification de projet et qui est recue par les résidents
 */
public abstract class Notification {
    private final String notificationID;
    private final String titre;
    private final String description;

    private final String date;
    private final String intervenant;

    public Notification(String titre, String description, String intervenant, String notificationID, String date) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.intervenant = intervenant;
        this.notificationID = notificationID;
    }

    public String getIntervenant() {
        return intervenant;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public String getDate() {
        return date;
    }
}
