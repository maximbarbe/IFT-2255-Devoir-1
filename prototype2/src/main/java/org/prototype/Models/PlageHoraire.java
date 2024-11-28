package org.prototype.Models;

/**
 * Classe représentant l'horaire de disponibilité des résidents ou l'horaire pendant lequel les travaux seront entrepris
 */
public class PlageHoraire {

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
    private String id;

    private int[] lundi = {0,0};
    private int[] mardi = {0,0};
    private int[] mercredi = {0,0};
    private int[] jeudi = {0,0};
    private int[] vendredi = {0,0};
    private int[] samedi = {0,0};
    private int[] dimanche = {0,0};


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getDimanche() {
        return dimanche;
    }

    public int[] getJeudi() {
        return jeudi;
    }

    public int[] getLundi() {
        return lundi;
    }

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

    public void setLundi(int[] lundi) {
        this.lundi = lundi;
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
