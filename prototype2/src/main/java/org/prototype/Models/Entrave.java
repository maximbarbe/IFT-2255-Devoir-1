package org.prototype.Models;

/**
 * Classe représentant les entraves causées par les travaux sur une rue spécifique.
 */
public class Entrave {
     /** L'identifiant du travail associé à l'entrave. */
    private String travailId;

    /** L'identifiant de la rue affectée par l'entrave. */ 
    private String streetId;

     /** La description de l'impact de l'entrave sur la rue. */
    private String streetImpact;

    /**
     * Constructeur pour créer une nouvelle entrave.
     *
     * @param travailId    L'identifiant du travail associé.
     * @param streetId     L'identifiant de la rue affectée.
     * @param streetImpact La description de l'impact sur la rue.
     */
    public Entrave(String travailId, String streetId, String streetImpact) {
        this.travailId = travailId;
        this.streetId = streetId;
        this.streetImpact = streetImpact;
    }

    /**
     * Retourne l'identifiant de la rue affectée.
     *
     * @return L'identifiant de la rue.
     */
    public String getStreetId() {
        return streetId;
    }

    /**
     * Retourne la description de l'impact de l'entrave sur la rue.
     *
     * @return La description de l'impact.
     */
    public String getStreetImpact() {
        return streetImpact;
    }

    /**
     * Retourne l'identifiant du travail associé à l'entrave.
     *
     * @return L'identifiant du travail.
     */
    public String getTravailId() {
        return travailId;
    }


    /**
     * Modifie l'identifiant de la rue affectée par l'entrave.
     *
     * @param streetId Le nouvel identifiant de la rue.
     */
    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

     /**
     * Modifie la description de l'impact de l'entrave sur la rue.
     *
     * @param streetImpact La nouvelle description de l'impact.
     */
    public void setStreetImpact(String streetImpact) {
        this.streetImpact = streetImpact;
    }

    /**
     * Modifie l'identifiant du travail associé à l'entrave.
     *
     * @param travailId Le nouvel identifiant du travail.
     */
    public void setTravailId(String travailId) {
        this.travailId = travailId;
    }
}
