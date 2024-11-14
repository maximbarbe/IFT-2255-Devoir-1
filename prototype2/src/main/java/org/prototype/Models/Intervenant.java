package org.prototype.Models;

/**
 * Classe représentant un intervenant qui utilise l'application, contient toutes les caractéristiques/méthodes propres aux intervenants.
 */
public class Intervenant extends Utilisateur{


    private TypeIntervenant type;
    private String identifiantVille;

    public Intervenant(String nomComplet, String adresseCourriel, String motDePasse, TypeIntervenant type, String identifiantVille) {
        super(nomComplet, adresseCourriel, motDePasse);
        this.type = type;
        this.identifiantVille = identifiantVille;
    }

    public TypeIntervenant getType() {
        return type;
    }

    public String getIdentifiantVille() {
        return identifiantVille;
    }

    public void setIdentifiantVille(String identifiantVille) {
        this.identifiantVille = identifiantVille;
    }

    public void setType(TypeIntervenant type) {
        this.type = type;
    }
}
