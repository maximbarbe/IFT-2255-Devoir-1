package org.prototype.Models;

/**
 * Classe représentant l'horaire de disponibilité des résidents ou l'horaire pendant lequel les travaux seront entrepris
 */
public class PlageHoraire {

     /**
     * Retourne une représentation sous forme de chaîne de la plage horaire.
     * Affiche "null" pour les jours sans horaire défini.
     *
     * @return Une chaîne représentant les horaires pour chaque jour de la semaine.
     */
    @Override
    public String toString() {
        String lundi = (getLundi()[0] == getLundi()[1])? "null":getLundi()[0]+"-"+getLundi()[1];
        String mardi = (getMardi()[0] == getMardi()[1])? "null":getMardi()[0]+"-"+getMardi()[1];
        String mercredi = (getMercredi()[0] == getMercredi()[1])? "null":getMercredi()[0]+"-"+getMercredi()[1];
        String jeudi = (getJeudi()[0] == getJeudi()[1])? "null":getJeudi()[0]+"-"+getJeudi()[1];
        String vendredi = (getVendredi()[0] == getVendredi()[1])? "null":getVendredi()[0]+"-"+getVendredi()[1];
        String samedi = (getSamedi()[0] == getSamedi()[1])? "null":getSamedi()[0]+"-"+getSamedi()[1];
        String dimanche = (getDimanche()[0] == getDimanche()[1])? "null":getDimanche()[0]+"-"+getDimanche()[1];
        return String.join(",",new String[]{id, lundi, mardi, mercredi, jeudi, vendredi,samedi,dimanche});
    }

    /** L'identifiant associé à cette plage horaire. */
    private String id;

    /** La plage horaire pour le lundi (en minutes depuis minuit). */
    private int[] lundi = {0,0};

   /** La plage horaire pour le mardi (en minutes depuis minuit). */ 
    private int[] mardi = {0,0};

    /** La plage horaire pour le mercredi (en minutes depuis minuit). */
    private int[] mercredi = {0,0};

    /** La plage horaire pour le jeudi (en minutes depuis minuit). */
    private int[] jeudi = {0,0};

    /** La plage horaire pour le vendredi (en minutes depuis minuit). */
    private int[] vendredi = {0,0};

    /** La plage horaire pour le samedi (en minutes depuis minuit). */
    private int[] samedi = {0,0};

   /** La plage horaire pour le dimanche (en minutes depuis minuit). */ 
    private int[] dimanche = {0,0};


    /**
     * Retourne l'identifiant de la plage horaire.
     *
     * @return L'identifiant de la plage horaire.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la plage horaire.
     *
     * @param id Le nouvel identifiant de la plage horaire.
     */
    public void setId(String id) {
        this.id = id;
    }

    public int[] getDimanche() {
        return dimanche;
    }

    public int[] getJeudi() {
        return jeudi;
    }

    /**
     * Retourne la plage horaire du lundi.
     *
     * @return Un tableau contenant l'heure de début et l'heure de fin pour le lundi.
     */
    public int[] getLundi() {
        return lundi;
    }

    /**
     * Définit la plage horaire du lundi.
     *
     * @param lundi Un tableau contenant l'heure de début et l'heure de fin pour le lundi.
     */
    public void setLundi(int[] lundi) {
        this.lundi = lundi;
    }

    // Méthodes similaires pour chaque jour de la semaine
    public int[] getMardi() {
        return mardi;
    }

    public int[] getMercredi() {
        return mercredi;
    }

    public int[] getSamedi() {
        return samedi;
    }

    public int[] getVendredi() {
        return vendredi;
    }

    public void setDimanche(int[] dimanche) {
        this.dimanche = dimanche;
    }

    public void setJeudi(int[] jeudi) {
        this.jeudi = jeudi;
    }

    public void setMardi(int[] mardi) {
        this.mardi = mardi;
    }

    public void setMercredi(int[] mercredi) {
        this.mercredi = mercredi;
    }

    public void setSamedi(int[] samedi) {
        this.samedi = samedi;
    }

    public void setVendredi(int[] vendredi) {
        this.vendredi = vendredi;
    }

    /**
     * Constructeur pour initialiser une plage horaire avec des horaires spécifiques pour chaque jour de la semaine.
     *
     * @param id            L'identifiant de la plage horaire.
     * @param lundiStart    Heure de début du lundi (en minutes depuis minuit).
     * @param lundiEnd      Heure de fin du lundi (en minutes depuis minuit).
     * @param mardiStart    Heure de début du mardi (en minutes depuis minuit).
     * @param mardiEnd      Heure de fin du mardi (en minutes depuis minuit).
     * @param mercrediStart Heure de début du mercredi (en minutes depuis minuit).
     * @param mercrediEnd   Heure de fin du mercredi (en minutes depuis minuit).
     * @param jeudiStart    Heure de début du jeudi (en minutes depuis minuit).
     * @param jeudiEnd      Heure de fin du jeudi (en minutes depuis minuit).
     * @param vendrediStart Heure de début du vendredi (en minutes depuis minuit).
     * @param vendrediEnd   Heure de fin du vendredi (en minutes depuis minuit).
     * @param samediStart   Heure de début du samedi (en minutes depuis minuit).
     * @param samediEnd     Heure de fin du samedi (en minutes depuis minuit).
     * @param dimancheStart Heure de début du dimanche (en minutes depuis minuit).
     * @param dimancheEnd   Heure de fin du dimanche (en minutes depuis minuit).
     */
    public PlageHoraire(String id, int lundiStart, int lundiEnd, int mardiStart, int mardiEnd, int mercrediStart, int mercrediEnd, int jeudiStart, int jeudiEnd, int vendrediStart, int vendrediEnd, int samediStart, int samediEnd, int dimancheStart, int dimancheEnd) {
        this.id = id;
        this.lundi = new int[]{lundiStart, lundiEnd};
        this.mardi = new int[]{mardiStart, mardiEnd};
        this.mercredi = new int[]{mercrediStart, mercrediEnd};
        this.jeudi = new int[]{jeudiStart, jeudiEnd};
        this.vendredi = new int[]{vendrediStart, vendrediEnd};
        this.samedi = new int[]{samediStart, samediEnd};
        this.dimanche = new int[]{dimancheStart, dimancheEnd};
    }
}
