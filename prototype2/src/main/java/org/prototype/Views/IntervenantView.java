package org.prototype.Views;

public class IntervenantView extends View implements ConnectedView{

    @Override
    public void menuPrincipal() {
        while (true) {
            clearConsole();
            println("Menu Principal: \n");
            println("1) Soumettre un nouveau projet de travail.");
            println("2) Consulter les requêtes de travail");
            println("3) Mettre à jour les informations sur un chantier");
            println("4) Profil");
            println("5) Quitter");
            print("Votre choix > ");
            String code = reader.nextLine();
        }
    }

    @Override
    public void afficherProfil(){};
}
