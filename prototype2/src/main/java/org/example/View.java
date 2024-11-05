package org.example;

import org.example.Controllers.ResidentController;


import java.util.Scanner;
public class View {

    private Scanner reader = new Scanner(System.in);

    private static void println(String msg) {
        System.out.println(msg);
    }

    private static void print(String msg) {
        System.out.print(msg);
    }

    private static void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void pageAccueil() {
        clearConsole();
        println("MaVille");
        println("\n");
        println("1) S'inscrire");
        println("2) Se connecter");
        while (true) {
            print("Votre choix: > ");
            String choice =reader.nextLine();
            switch (choice) {
                case "1":
                    pageInscription();
                    break;
                case "2":
                    pageConnexion();
                    break;
                default:
                    println("Mauvais choix, veuillez réessayer");
                    continue;
            }
            break;
        }

    }

    public void pageInscription(){
        while (true) {
            clearConsole();
            println("1) Résident");
            println("2) Intervenant");
            println("3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    inscriptionResident();
                    break;
                case "2":
                    inscriptionIntervenant();
                    break;
                case "3":
                    return;
                default:
                    continue;
            }
        }
    };


    private void inscriptionResident() {
        while (true) {
            clearConsole();
            println("Formulaire d'inscription pour résident:");
            print("Nom complet > ");
            String name = reader.nextLine();
            print("Date de naissance (Format:AAAA-MM-JJ) > ");
            String bday = reader.nextLine();
            print("Adresse courriel > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password =reader.nextLine();
            print("Téléphone (optionnel) > ");
            String telephone = reader.nextLine();
            print("Adresse résidentielle > ");
            String adresse = reader.nextLine();
            while (true) {
                println("1) Modifier; 2) Confirmer; 3) Annuler");

                switch (reader.nextLine()) {
                    case "1":
                        break;
                    case "2":
                        switch (ResidentController.createResident(name, bday, email, password, telephone, adresse)) {
                            case 0:
                                println("Compte créé avec succès!");
                                println("Appuyez sur n'importe quelle touche.");
                                reader.nextLine();
                                return;
                            case 1:
                                println("Il existe déjà un utilisateur avec cette addresse courriel.");
                                println("Appuyez sur n'importe quelle touche.");
                                reader.nextLine();
                                continue;
                            case 2:
                                println("Format de date de naissance invalide.");
                                println("Appuyez sur n'importe quelle touche.");
                                reader.nextLine();
                                continue;
                            case 3:
                                println("Vous devez avoir plus de 16 ans.");
                                println("Appuyez sur n'importe quelle touche.");
                                reader.nextLine();
                                continue;
                        }
                    case "3":
                        return;
                }
                break;
            }

        }
    };


    private void inscriptionIntervenant() {

    };


    public void pageConnexion(){
        while (true) {
            clearConsole();
            print("Adresse courielle > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password = reader.nextLine();

        }
    };


    public void startApplication() {
        pageAccueil();
    }


}
