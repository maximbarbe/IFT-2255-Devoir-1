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
    public void pageTravail(){};

    public void pageNotifications(){};
    @Override
    public void afficherProfil(){};
}
