package org.prototype.Models;

import java.util.ArrayList;

public class NotificationDeTravail extends Notification{
    

    private final ArrayList<String> quartiersAffectes;
    private final ArrayList<String> ruesAffectees;
    
    

    public NotificationDeTravail(String titre, String description, ArrayList<String> quartiersAffectes, ArrayList<String> ruesAffectees) {
        super(titre, description);
        this.quartiersAffectes = quartiersAffectes;
        this.ruesAffectees = ruesAffectees;
    }


    public ArrayList<String> quartiersAffectes() {
        return this.quartiersAffectes;
    }

    public ArrayList<String> ruesAffectees() {
        return this.ruesAffectees;
    }
}
