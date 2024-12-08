package org.prototype.Models;

/**
 * Classe représentant un intervenant qui utilise l'application.
 * Hérite des propriétés et méthodes de la classe {@link Utilisateur}.
 * Un intervenant possède un type spécifique et un identifiant de ville.
 */
public class Intervenant extends Utilisateur{

    /** Le type d'intervenant (ex : ENTREPRENEUR_PRIVE, ENTREPRISE_PUBLIQUE, etc.). */
    private TypeIntervenant type;

     /** L'identifiant de la ville de l'intervenant, représenté par un code à 8 chiffres. */
    private String identifiantVille;

    /**
     * Constructeur pour créer un nouvel intervenant avec les informations spécifiées.
     *
     * @param nomComplet       Le nom complet de l'intervenant.
     * @param adresseCourriel  L'adresse courriel de l'intervenant.
     * @param motDePasse       Le mot de passe de l'intervenant (généralement haché).
     * @param type             Le type de l'intervenant ({@link TypeIntervenant}).
     * @param identifiantVille L'identifiant de la ville (code à 8 chiffres).
     */
    public Intervenant(String nomComplet, String adresseCourriel, String motDePasse, TypeIntervenant type, String identifiantVille) {
        super(nomComplet, adresseCourriel, motDePasse);
        this.type = type;
        this.identifiantVille = identifiantVille;
    }

    /**
     * Retourne le type de l'intervenant.
     *
     * @return Le type de l'intervenant ({@link TypeIntervenant}).
     */
    public TypeIntervenant getType() {
        return type;
    }

     /**
     * Retourne l'identifiant de la ville de l'intervenant.
     *
     * @return L'identifiant de la ville sous forme de chaîne.
     */
    public String getIdentifiantVille() {
        return identifiantVille;
    }


    /**
     * Modifie l'identifiant de la ville de l'intervenant.
     *
     * @param identifiantVille Le nouvel identifiant de la ville.
     */    public void setIdentifiantVille(String identifiantVille) {
        this.identifiantVille = identifiantVille;
    }

    /**
     * Modifie le type de l'intervenant.
     *
     * @param type Le nouveau type de l'intervenant ({@link TypeIntervenant}).
     */
    public void setType(TypeIntervenant type) {
        this.type = type;
    }
}
