package org.prototype.Views;

import org.prototype.Controllers.IntervenantController;
import org.prototype.Controllers.ResidentController;
import org.prototype.Controllers.UtilisateurController;
import org.prototype.MaVille;
import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;

import java.io.IOException;

/**
 * Classe qui s'occupe de la vue qu'un utilisateur non-connecté voit.
 */
public class UnauthenticatedView extends View{


    /**
     * Affiche la page d'accueil, la page que l'on obtient en ouvrnt l'application, et permet de se connecter/s'inscrire.
     */
    public void pageAccueil() {
        while (true) {
            MaVille.setCurUser(null);
            clearConsole();
            println("MaVille");
            println("\n");
            println("1) S'inscrire");
            println("2) Se connecter");
            println("3) Quitter");
            print("Votre choix: > ");
            String choice =reader.nextLine();
            switch (choice) {
                case "1":
                    pageInscription();
                    continue;
                case "2":
                    pageConnexion();
                    continue;
                case "3":
                    System.exit(0);
                default:
                    println("Mauvais choix, veuillez réessayer");
                    continue;
            }

        }

    }

    /**
     * Affiche la page d'inscription et permet à l'utilisateur de choisir s'il veut s'inscrire en tant que résident ou intervenant.
     */
    public void pageInscription(){
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


    /**
     * Gère l'inscription pour le résident en affichant le formulaire et en prenant les données entrées.
     * @return - Un booléen indiquant si l'inscription a été un succès
     */
    public boolean inscriptionResident() {
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
            print("Code postal (YYY YYY) > ");
            String postalCode = reader.nextLine();
            println("1) Modifier; 2) Confirmer; 3) Annuler");

            switch (reader.nextLine()) {
                case "1":
                    continue;
                case "2":
                    int statusCode = ResidentController.createResident(name, bday, email, password, adresse, telephone, postalCode);
                    String msg = "";
                    switch (statusCode) {
                        case 0:
                            return true;
                        case 1:
                            msg = "L'adresse courriel existe déjà, veuillez vous connecter";
                            break;
                        case 2:
                            msg = "Le format d'adresse courriel est invalide.";
                            break;
                        case 3:
                            msg = "Le format de date de naissance est invalide.";
                            break;
                        case 4:
                            msg = "Vous ne possédez pas au moins 16 ans.";
                            break;
                        case 5:
                            msg = "Le format de code postal n'est pas valide.";
                            break;
                        case 6:
                            msg = "Le code postal fournit de correspond pas à un quartier.";
                            break;
                        case 7:
                            msg = "Erreur lors de la sauvegarde de votre compte, veuillez réessayer.";
                            break;

                    }
                    println(msg);
                    print("Appuyez sur n'importe quelle touche pour continuer");
                    reader.nextLine();
                    continue;
                case "3":
                    return false;
                default:
                    println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }    };

    /**
     * Gère l'inscription pour l'intervenant en affichant le formulaire et en prenant les données entrées.
     * @return - Un booléen indiquant si l'inscription a été un succès
     */
    public boolean inscriptionIntervenant() {
        while (true) {
            clearConsole();
            println("Formulaire d'inscription pour intervenant.");
            print("Nom complet > ");
            String name = reader.nextLine();
            print("Adresse courriel > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password =reader.nextLine();
            String type;
            while (true) {
                println("Type: (1) Entreprise publique, (2) Entrepreneur privé, (3) Particulier");
                switch (reader.nextLine()) {
                    case "1":
                        type = "entreprise_publique";
                        break;
                    case "2":
                        type = "entrepreneur_prive";
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
                    String msg = "";
                    switch (IntervenantController.createIntervenant(name, type, email, password, code)) {
                        case 0:
                            return true;
                        case 1:
                            msg="Cet identifiant a déjà été utilisé, veuillez vous connecter ou contacter la ville.";
                            break;
                        case 2:
                            msg = "L'identifiant fourni n'est pas dans le bon format, assurez vous d'écrire un code à 8 chiffres.";
                            break;
                        case 3:
                            msg = "L'adresse courrielle existe déjà, connectez-vous.";
                            break;
                        case 4:
                            msg = "L'adresse courriel n'est pas dans le bon format.";
                            break;
                        case 5:
                            msg = "Une erreur est survenue lors de l'enregistrement de votre compte, veuillez réessayer.";
                            break;
                    }
                    println(msg);
                    println("Appuyez sur n'importe quelle touche pour continuer");
                    reader.nextLine();
                    continue;
                case "3":
                    return false;
                default:
                    println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }

    };

    /**
     * Permet la connexion et redirige vers le menu principal correspondant au type de compte.
     */
    public void pageConnexion(){
        while (true) {
            clearConsole();
            print("Adresse courielle > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password = reader.nextLine();
            Utilisateur connectedUser = null;
            connectedUser = UtilisateurController.getUtilisateur(email, password);

            if (connectedUser == null) {
                println("Erreur lors de la connexion à votre compte.");
                println("1) Réessayer; 2) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        continue;
                    case "2":
                        return;

                }
            // On redirige l'utilisateur vers la bonne vue.    
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

    /**
     * Commence l'application en affichant la page d'accueil.
     */
    public void startApplication() {
        pageAccueil();
    }


}
