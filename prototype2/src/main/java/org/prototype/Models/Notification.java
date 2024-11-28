package org.prototype.Models;

import java.util.ArrayList;

/**
 * Classe représentant une notification créé lors de la modification de projet et qui est recue par les résidents
 */
public abstract class Notification {
    private final String titre;
    private final String description;
 

    public Notification(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

}
