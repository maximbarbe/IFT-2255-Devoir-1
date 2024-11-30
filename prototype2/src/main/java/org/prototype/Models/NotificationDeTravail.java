package org.prototype.Models;

import java.util.ArrayList;

public class NotificationDeTravail extends Notification{
    

    private final String travailID;
    
    

    public NotificationDeTravail(String titre, String description, String intervenant, String travailID, String date, String notificationID) {
        super(titre, description, intervenant, notificationID, date);
        this.travailID = travailID;
    }


    public String getTravailID() {
        return this.travailID;
    }


}
