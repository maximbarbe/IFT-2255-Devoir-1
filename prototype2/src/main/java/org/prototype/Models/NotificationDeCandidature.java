package org.prototype.Models;

public class NotificationDeCandidature extends Notification{
    
    private final String intervenant;
    private final String requeteID;

    public NotificationDeCandidature(String titre, String description, String intervenant, String requete) {
        super(titre, description);
        this.intervenant = intervenant;
        this.requeteID = requete;
    }

    public String getIntervenant() {
        return this.intervenant;
    }

    public String getRequeteID() {
        return this.requeteID;
    }
}
