package org.prototype.Views;




import org.prototype.Controllers.UtilisateurController;
import org.prototype.MaVille;
import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;

import java.io.IOException;
import java.util.Scanner;
public class UnauthenticatedView extends View{



    private void pageAccueil() {
        while (true) {
            clearConsole();
            println("MaVille");
            println("\n");
            println("1) S'inscrire");
            println("2) Se connecter");

                print("Votre choix: > ");
                String choice =reader.nextLine();
                switch (choice) {
                    case "1":
                        pageInscription();
                        continue;
                    case "2":
                        pageConnexion();
                        continue;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }

        }

    }

    private void pageInscription(){
        while (true) {
            clearConsole();
            println("1) Résident");
            println("2) Intervenant");
            println("3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    if (inscriptionResident()) {
                        println("Compte créé avec succès!");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        clearConsole();
                        return;
                    } else {
                        continue;
                    }

                case "2":
                    if (inscriptionIntervenant()) {
                        println("Compte créé avec succès!");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        clearConsole();
                        return;
                    } else {
                        continue;
                    }
                case "3":
                    return;

                default:
                    continue;
            }
        }

    };


    private boolean inscriptionResident() {
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
            println("1) Modifier; 2) Confirmer; 3) Annuler");

            switch (reader.nextLine()) {
                case "1":
                    continue;
                case "2":
                    return true;
                case "3":
                    return false;
                default:
                    println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }    };


    private boolean inscriptionIntervenant() {
        while (true) {
            clearConsole();
            println("Formulaire d'inscription pour intervenant.");
            print("Nom complet > ");
            String name = reader.nextLine();
            print("Adresse courriel > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password =reader.nextLine();
            while (true) {
                println("Type: (1) Entreprise publique, (2) Entrepreneur privé, (3) Particulier");
                String type;
                switch (reader.nextLine()) {
                    case "1":
                        type = "Entreprise publique";
                        break;
                    case "2":
                        type = "Entrepreneur privé";
                        break;
                    case "3":
                        type = "particulier";
                        break;
                    default:
                        println("Mauvais choix, veuillez recommencer.");
                        print("Appuyez sur n'importe quelle touche");
                        reader.nextLine();
                        continue;
                }
                break;
            }

            print("Code fourni par la ville > ");
            String code = reader.nextLine();

            println("1) Modifier; 2) Confirmer; 3) Annuler");

            switch (reader.nextLine()) {
                case "1":
                    continue;
                case "2":
                    return true;
                case "3":
                    return false;
                default:
                    println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }

    };


    private void pageConnexion(){
        while (true) {
            clearConsole();
            print("Adresse courielle > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password = reader.nextLine();
            Utilisateur connectedUser = null;
            try {
                connectedUser = UtilisateurController.getUtilisateur(email, password);

            } catch (IOException e) {
                println("Erreur lors de la connexion au fichiers de données. Veuillez réessayer");
                println("Appuyez sur n'importe quelle touche pour continuer");
                reader.nextLine();
            }
            if (connectedUser == null) {
                println("Mauvais email ou password");
                println("1) Réessayer; 2) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        continue;
                    case "2":
                        return;

                }
            } else if (connectedUser instanceof Resident) {
                MaVille.setCurUser(connectedUser);
                ResidentView view = new ResidentView();
                view.menuPrincipal();
                return;
            } else {
                MaVille.setCurUser(connectedUser);
                IntervenantView view = new IntervenantView();
                view.menuPrincipal();
                return;
            }


        }
    };


    public void startApplication() {
        pageAccueil();
    }


}
