package org.prototype.Models;

import java.util.ArrayList;

/**
 * Classe représentant une notification créé lors de la modification de projet et qui est recue par les résidents
 */
public class Notification {
    private final String titre;
    private final String description;
    private final ArrayList<String> quartiersAffectes;
    private final ArrayList<String> ruesAffectees;    


    public Notification(String titre, String description, ArrayList<String> quartiersAffectes, ArrayList<String> ruesAffectees) {
        this.titre = titre;
        this.description = description;
        this.quartiersAffectes = quartiersAffectes;
        this.ruesAffectees = ruesAffectees;
    }

}
