package org.prototype.Views;

public class ResidentView extends View implements ConnectedView{

    @Override
    public void menuPrincipal() {
        while (true) {
            clearConsole();
            println("Menu principal:\n");
            println("1) Travaux");
            println("2) Profil");
            println("3) Notifications");
            println("4) Quitter");
            print("Votre choix > ");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    pageTravail();
                    continue;
                case "2":
                    afficherProfil();
                    continue;
                case "3":
                    pageNotifications();
                    continue;
                case "4":
                    System.exit(0);
                default:
                    println("Mauvais choix, veuillez réessayer");
            }
        }
    }
    /**
     * Affiche le sous-menu du travail et permet aux résidents d'accéder à toutes les fonctionnalités reliées au travail.
     */
    public void pageTravail() {
        while (true) {
            clearConsole();
            println("1) Consulter les travaux en cours ou à venir");
            println("2) Consulter les entraves routières");
            println("3) Soumettre une requête de travail");
            println("4) Plages horaires");
            println("5) Revenir");
            print("Votre choix > ");
            while (true) {
                switch (reader.nextLine()) {
                    case "1":
                        consulterTravaux();
                        break;
                    case "2":
                        break;
                    case "3":
                        soumettreRequeteTravail();
                        break;
                    case "4":
                        plagesHoraires();
                        break;
                    case "5":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }


        }
    }


    public void consulterTravaux(){};

    public void soumettreRequeteTravail(){};

    public void plagesHoraires(){};
    public void pageNotifications(){};
    @Override
    public void afficherProfil(){};
}
