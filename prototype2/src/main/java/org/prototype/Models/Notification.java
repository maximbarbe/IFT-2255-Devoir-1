package org.prototype.Models;

import java.util.ArrayList;

public class Notification {
    private final String titre;
    private final String description;
    private final ArrayList<String> quartiersAffectes;


    public Notification(String titre, String description, ArrayList<String> quartiersAffectes) {
        this.titre = titre;
        this.description = description;
        this.quartiersAffectes = quartiersAffectes;
    }

}
