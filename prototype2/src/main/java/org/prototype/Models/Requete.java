package org.prototype.Models;

/**
 * Classe représentant une requête de travail que les résidents peuvent envoyer aux intervenants.
 */
public class Requete {

    /** L'identifiant unique de la requête. */
    private final int requeteId;

    /** Le titre de la requête. */
    private String titre;

    /** La description détaillée de la requête. */
    private String description;

    /** Le type de travail demandé. */
    private String type;

    /** La date de début espérée pour le travail au format ISO (yyyy-MM-dd). */
    private String dateDebutEspere;

    /** Le quartier concerné par la requête. */
    private String quartier;

   /** Le statut actuel de la requête (par défaut à {@link RequeteStatut#OUVERTE}). */ 
    private RequeteStatut statut = RequeteStatut.OUVERTE;

    /** L'identifiant de l'utilisateur (résident) qui a créé la requête. */
    private final String userID;

    /**
     * Constructeur pour créer une nouvelle requête de travail avec les informations spécifiées.
     *
     * @param requeteId        L'identifiant unique de la requête.
     * @param titre            Le titre de la requête.
     * @param description      La description détaillée de la requête.
     * @param type             Le type de travail demandé.
     * @param dateDebutEspere  La date de début espérée pour le travail.
     * @param userID           L'identifiant de l'utilisateur ayant créé la requête.
     * @param quartier         Le quartier concerné par la requête.
     */
    public Requete(int requeteId, String titre, String description, String type, String dateDebutEspere, String userID, String quartier) {
        this.requeteId = requeteId;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.dateDebutEspere = dateDebutEspere;
        this.userID = userID;
        this.quartier = quartier;

    }

    /**
     * Retourne le quartier concerné par la requête.
     *
     * @return Le quartier de la requête.
     */
    public String getQuartier() {
        return quartier;
    }

    /**
     * Retourne l'identifiant unique de la requête.
     *
     * @return L'identifiant de la requête.
     */
    public int getRequeteId() {
        return requeteId;
    }

    /**
     * Retourne l'identifiant de l'utilisateur ayant créé la requête.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Retourne la description de la requête.
     *
     * @return La description de la requête.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le type de travail demandé.
     *
     * @return Le type de travail.
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne le titre de la requête.
     *
     * @return Le titre de la requête.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne la date de début espérée pour le travail.
     *
     * @return La date de début espérée au format ISO (yyyy-MM-dd).
     */
    public String getDateDebutEspere() {
        return dateDebutEspere;
    }

    /**
     * Modifie la description de la requête.
     *
     * @param description La nouvelle description de la requête.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Modifie le type de travail demandé.
     *
     * @param type Le nouveau type de travail.
     */ 
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Modifie le titre de la requête.
     *
     * @param titre Le nouveau titre de la requête.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Modifie la date de début espérée pour le travail.
     *
     * @param dateDebutEspere La nouvelle date de début espérée au format ISO (yyyy-MM-dd).
     */
    public void setDateDebutEspere(String dateDebutEspere) {
        this.dateDebutEspere = dateDebutEspere;
    }

    /**
     * Modifie le statut de la requête.
     *
     * @param statut Le nouveau statut de la requête ({@link RequeteStatut}).
     */
    public void setStatut(RequeteStatut statut) {
        this.statut = statut;
    }

    /**
     * Retourne le statut actuel de la requête.
     *
     * @return Le statut de la requête ({@link RequeteStatut}).
     */
    public RequeteStatut getStatut() {
        return statut;
    }
}
