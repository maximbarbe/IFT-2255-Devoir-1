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
            switch (code) {
                case "1":
                    soumettreNouveauTravail();
                    continue;
                case "2":
                    consulterRequetes();
                    continue;
                case "3":
                    afficherChantiers();
                    continue;
                case "4":
                    afficherProfil();
                    continue;
                case "5":
                    System.exit(0);
                default:
                    println("Mauvais choix, veuillez réessayer!");
                    return;
            }
        }
    }


    public void consulterRequetes() {

    }

    public void afficherChantiers() {
        while(true) {
            clearConsole();
            println("Vos chantiers: \n");
            println("\t - ID: 0187 - Rénovation de la maison d'un résident");
            println("\t - ID: 0188 - Installation de panneaux de circulation");
            println("\t - ID: 0199 - Construction d'une nouvelle ligne de la STM");
            while (true) {
                println("1) Modifier les informations d'un chantier; 2) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        modifierInfosChantiers();
                        break;
                    case "2":
                        return;

                    default:
                        println("Mauvais choixm veuillez réessayer");
                        continue;
                }
                break;
            }
        }

    }
    public void modifierInfosChantiers() {
        while (true) {
            clearConsole();
            println("Modifier des informations du chantier (Veuillez laisser blank si on ne veut pas modifier):\n");
            print("ID du chantier: ");
            String id = reader.nextLine();
            print("Description du projet: ");
            reader.nextLine();
            print("Date de fin prévue: ");
            reader.nextLine();
            print("Statut du projet: ");
            reader.nextLine();
            while (true) {
                println("1) Confirmer les changements; 2) Annuler");
                print("Votre choix > ");
                switch(reader.nextLine()) {
                    case "1":
                        println("Changements effectués");
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        return;
                    case "2":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                }
            }


        }
    }
    public void soumettreNouveauTravail() {
        clearConsole();
        println("Veuillez remplir le formulaire suivant pour soumettre un nouveau projet de travail: ");
        println("Titre: ");
        reader.nextLine();
        println("Description du projet: ");
        reader.nextLine();
        println("Quartiers affectés: ");
        reader.nextLine();
        println("Rues affectées: ");
        reader.nextLine();
        println("Date de début (YYYY-MM-DD): ");
        reader.nextLine();
        println("Date de fin (YYYY-MM-DD): ");
        reader.nextLine();
        println("Horaire des travaux: ");
        reader.nextLine();
        while (true) {
            println("1) Consulter les préférences; 2) Confirmer; 3) Annuler");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    println("Affichage de la page des préférences");
                    println("Appuyez sur n'importe quelle tâche pour continuer");
                    reader.nextLine();
                    continue;
                case "2":
                    println("Vous n'avez aucun conflit avec les préférences des résidents.");
                    println("Formulaire soumis avec succès");
                    println("Appuyez sur n'importe quelle tâche pour continuer");
                    reader.nextLine();
                    return;
                case "3":
                    println("Annulation du formulaire");
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer");
                    break;
            }
        }
    }
    @Override
    public void afficherProfil(){};
}
