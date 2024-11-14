package org.prototype.Views;

/**
 * Interface définissant les méthodes que l'on retrouve dans la vue pour résident et la vue pour intervenant
 */
public interface ConnectedView {

    /**
     * Affiche le menu principal et permet d'accéder aux différentes fonctionnalités de l'application.
     */
    public void menuPrincipal();

    /**
     * Affiche le profil et permet d'accéder aux fonctionnalités reliées au profil.
     */
    public void afficherProfil();
}
