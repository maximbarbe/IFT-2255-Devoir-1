package org.prototype.Models;

public class NotificationDeCandidature extends Notification{
    

    private final String requeteID;

    public NotificationDeCandidature(String titre, String description, String intervenant, String requete, String date, String notificationID) {
        super(titre, description, intervenant, notificationID, date);
        this.requeteID = requete;
    }


    public String getRequeteID() {
        return this.requeteID;
    }
}
