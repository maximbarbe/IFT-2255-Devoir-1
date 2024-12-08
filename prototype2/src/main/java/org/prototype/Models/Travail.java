package org.prototype.Models;

import java.util.ArrayList;

/**
 * Classe représentant un projet de travail entrepris par les intervenants
 */
public class Travail {

    /** Identifiant unique du travail. */
    private String id;

    /** Titre du travail. */
    private String titre;

    /** Description détaillée du travail. */
    private String description;

   /** Liste des quartiers concernés par le travail. */ 
    private ArrayList<String> quartiers;

   /** Liste des rues affectées par le travail. */ 
    private ArrayList<String> ruesAffectees;
   /** Date de début du travail au format ISO (yyyy-MM-dd). */
    private String dateDebut;
   
    /** Date de fin du travail au format ISO (yyyy-MM-dd). */
    private String dateFin;

    /** Statut actuel du projet (par défaut à {@link StatutProjet#PREVU}). */
    private StatutProjet status = StatutProjet.PREVU;

   /** Identifiant de l'intervenant responsable du travail. */ 
    private String identifiantIntervenant;

   /** Type de travail entrepris. */ 
    private TypeTravail type;


   /**
     * Constructeur pour créer un nouveau travail avec les informations spécifiées.
     *
     * @param id                    L'identifiant unique du travail.
     * @param titre                 Le titre du travail.
     * @param description           La description détaillée du travail.
     * @param quartiers             La liste des quartiers concernés par le travail.
     * @param ruesAffectees         La liste des rues affectées par le travail.
     * @param dateDebut             La date de début du travail au format ISO (yyyy-MM-dd).
     * @param dateFin               La date de fin du travail au format ISO (yyyy-MM-dd).
     * @param identifiantIntervenant L'identifiant de l'intervenant responsable du travail.
     * @param type                  Le type de travail entrepris.
     */ 
    public Travail(String id, String titre, String description, ArrayList<String> quartiers, ArrayList<String> ruesAffectees, String dateDebut, String dateFin, String identifiantIntervenant, TypeTravail type) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.quartiers = quartiers;
        this.ruesAffectees = ruesAffectees;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.identifiantIntervenant = identifiantIntervenant;
        this.type = type;
    }

    /**
     * Modifie l'identifiant du travail.
     *
     * @param id Le nouvel identifiant du travail.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Modifie le type de travail.
     *
     * @param type Le nouveau type de travail.
     */ 
    public void setType(TypeTravail type) {
        this.type = type;
    }

    /**
     * Retourne le type de travail.
     *
     * @return Le type de travail.
     */ 
    public TypeTravail getType() {
        return type;
    }

    /**
     * Modifie le titre du travail.
     *
     * @param titre Le nouveau titre du travail.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

   /**
     * Modifie la date de début du travail.
     *
     * @param dateDebut La nouvelle date de début au format ISO (yyyy-MM-dd).
     */ 
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Modifie la liste des rues affectées par le travail.
     *
     * @param ruesAffectees La nouvelle liste des rues affectées.
     */
    public void setRuesAffectees(ArrayList<String> ruesAffectees) {
        this.ruesAffectees = ruesAffectees;
    }

    /**
     * Modifie la date de fin du travail.
     *
     * @param dateFin La nouvelle date de fin au format ISO (yyyy-MM-dd).
     */ 
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

   /**
     * Modifie la description du travail.
     *
     * @param description La nouvelle description du travail.
     */ 
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Modifie l'identifiant de l'intervenant responsable du travail.
     *
     * @param identifiantIntervenant Le nouvel identifiant de l'intervenant.
     */ 
    public void setIdentifiantIntervenant(String identifiantIntervenant) {
        this.identifiantIntervenant = identifiantIntervenant;
    }

   /**
     * Modifie la liste des quartiers concernés par le travail.
     *
     * @param quartiers La nouvelle liste des quartiers concernés.
     */ 
    public void setQuartiers(ArrayList<String> quartiers) {
        this.quartiers = quartiers;
    }

    /**
     * Modifie le statut du travail.
     *
     * @param status Le nouveau statut du travail.
     */
    public void setStatus(StatutProjet status) {
        this.status = status;
    }

    /**
     * Retourne la date de début du travail.
     *
     * @return La date de début au format ISO (yyyy-MM-dd).
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Retourne la date de fin du travail.
     *
     * @return La date de fin au format ISO (yyyy-MM-dd).
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Retourne l'identifiant du travail.
     *
     * @return L'identifiant du travail.
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne la description du travail.
     *
     * @return La description du travail.
     */
    public String getDescription() {
        return description;
    }

   /**
     * Retourne l'identifiant de l'intervenant responsable du travail.
     *
     * @return L'identifiant de l'intervenant.
     */ 
    public String getIdentifiantIntervenant() {
        return identifiantIntervenant;
    }


    /**
     * Retourne le titre du travail.
     *
     * @return Le titre du travail.
     */ 
    public String getTitre() {
        return titre;
    }


   /**
     * Retourne le statut du travail.
     *
     * @return Le statut actuel du travail.
     */ 
    public StatutProjet getStatus() {
        return status;
    }


    /**
     * Retourne la liste des quartiers concernés par le travail.
     *
     * @return La liste des quartiers concernés.
     */
    public ArrayList<String> getQuartiers() {
        return quartiers;
    }

    /**
     * Retourne la liste des rues affectées par le travail.
     *
     * @return La liste des rues affectées.
     */
    public ArrayList<String> getRuesAffectees() {
        return ruesAffectees;
    }
}
