package org.prototype.Models;

public class Entrave {
    private String travailId;
    private String streetId;
    private String streetImpact;

    public Entrave(String travailId, String streetId, String streetImpact) {
        this.travailId = travailId;
        this.streetId = streetId;
        this.streetImpact = streetImpact;
    }

    public String getStreetId() {
        return streetId;
    }

    public String getStreetImpact() {
        return streetImpact;
    }

    public String getTravailId() {
        return travailId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public void setStreetImpact(String streetImpact) {
        this.streetImpact = streetImpact;
    }

    public void setTravailId(String travailId) {
        this.travailId = travailId;
    }
}
