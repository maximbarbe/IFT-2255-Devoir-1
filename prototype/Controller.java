import java.util.Scanner;
import java.util.HashMap;


/**
 * Classe qui agira comme contrôleur de l'application, c'est cette classe qui s'occupera d'afficher les menus, d'obtenir les inputs d'utilisateurs et de gérer les comptes/travaux.
 */
public class Controller {
    
    // Le reader nous permettra de lire les entrées par l'utilisateur
    private Scanner reader = new Scanner(System.in);
    private HashMap<String, User> users = createAccounts();

    /**
     * Affiche l'écran d'accueil lorsqu'on ouvre l'application et prompt l'utilisateur à s'inscrire ou à se connecter.
     * @return <code>int</code> l'entier entré par l'utilisateur ou <code>3</code> si l'entrée est invalide.
     */
    public int showOpeningScreen() {
        println("MaVille");
        println("\n");
        println("1) S'inscrire");
        println("2) Se connecter");
        print("Votre choix: > ");
        String choice =reader.nextLine();
        switch (choice) {
            case "1":
            case "2":
                return Integer.parseInt(choice);
            default:
                println("Mauvais choix, veuillez réessayer.");
                return 3;
        }
    };

    /**
     * Crée un nouveau résident <b><i>(ne fait aucune vérification, utilisé uniquement pour simuler)</i></b>
     * @return <code>true</code> si l'inscription a été un succès <code>false</code> sinon.
     */
    private boolean registerResident() {
        while (true) {
            println("Formulaire d'inscription pour résident:");
            print("Nom complet > ");
            String name = reader.nextLine();
            print("Date de naissance (Format:AAAA/MM/JJ) > ");
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
        }
    }

    /**
     * S'occupe de l'inscription d'un nouvel intervenant
     * @return <code>true</code> si l'inscription a été un succès, <code>false</code> sinon
     */
    private boolean registerIntervenant(){
        while (true) {
            println("Formulaire d'inscription pour intervenant.");
            print("Nom complet > ");
            String name = reader.nextLine();
            print("Adresse courriel > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password =reader.nextLine();
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
                    continue;
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
    }

    /**
     * Fonction qui s'occupe d'enregistrer un nouvel utilisateur, résident ou intervenant
     * @return <code>true</code> si l'inscription a été un succès, <code>false</code> sinon
     */
    public boolean registerUser() {
        while (true) {
            println("1) Résident");
            println("2) Intervenant");
            println("3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    if (registerResident()) {
                        println("Compte créé avec succès!");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        clearConsole();
                        return true;
                    }
                    return false;
                    
                case "2":
                    if (registerIntervenant()) {
                        println("Compte créé avec succès!");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        clearConsole();
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }

        
    }

    /**
     * Clear la console pour améliorer le output (empêche d'avoir trop de choses dans la console)
     */
    public static void clearConsole() {
System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    /**
     * Authentifie un utilisateur et lui donne accès à l'application
     * @return - l'utilisateur authentifié
     */
    public User loginUser() {
        while (true) {
            print("Adresse courielle > ");
            String email = reader.nextLine();
            print("Mot de passe > ");
            String password = reader.nextLine();
            if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
                return users.get(email);
            } else {
                
                println("\nErreur! Mauvais email ou mauvais mot de passe.\n");
                print("1) Réessayer; 2) Revenir > ");
                switch (reader.nextLine()) {
                    case "1":
                        continue;
                    case "2":
                        return null;
                    default:
                        println("Mauvais choix, retour à la page d'accueil");
                        return null;
                }

            }
        }
    }


    /**
     * Affiche le menu principal pour les résidents
     * @param user - l'utilisateur en tant que tel
     */
    public void showMenuPrincipalResident(User user) {
        
        while (true) {
            clearConsole();
            println("Menu principal:\n");
            println("1) Travaux");
            println("2) Profil");
            println("3) Notifications");
            println("4) Signaler un problème");
            println("5) Quitter");
            print("Votre choix > ");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    pageTravail();
                    continue;
                case "2":
                    afficherProfil(user);
                    continue;
                case "3":
                    pageNotifications();
                    continue;
                case "4":
                    signalerProbleme();
                    continue;
                case "5":
                    System.exit(0);
                default:
                    println("Mauvais choix, veuillez réessayer");
            }
        }

    };

    /**
     * Affiche le sous-menu du travail et permet aux résidents d'accéder à toutes les fonctionnalités reliées au travail.
     */
    private void pageTravail() {
        while (true) {
            clearConsole();
            println("1) Consulter les travaux en cours ou à venir");
            println("2) Soumettre une requête de travail");
            println("3) Plages horaires");
            println("4) Revenir");
            print("Votre choix > ");
            while (true) {
                switch (reader.nextLine()) {
                    case "1":
                        consulterTravaux();
                        break;
                    case "2":
                        soumettreRequeteTravail();
                        break;
                    case "3":
                        plagesHoraires();
                        break;
                    case "4":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }


        }
    }

    /**
     * Affiche les plages horaires de l'utilisateur et permet d'accéder aux fonctionnalités de modification des plages horaires, ainsi que voir les plages horaires des autres du quartier.
     */
    private void plagesHoraires() {
        while (true) {
            clearConsole();
            println("Voici votre plage horaire:\n");
            println("Lundi: 8:00-12:00");
            println("Mardi: 15:00-17:00");
            println("Mercredi: 12:00-15:00");
            println("Jeudi: Aucune");
            println("Vendredi: 16:00-18:00");
            println("Samedi: Aucune");
            println("Dimanche: Aucune");
            println("\n");
            while (true) {
                println("1) Modifier la plage horaire; 2) Voir les plages horaires des autres résidents du quartier; 3) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        println("Affichage de la page de modification des plages horaires...");
                        println("Appuyez sur n'importe quelle touche");
                        reader.nextLine();
                        break;
                    case "2":
                        println("Affichage de la page pour voir les plages horaires...");
                        println("Appuyez sur n'importe quelle touche");
                        reader.nextLine();
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }
            

        }
    }

    /**
     * Permet à un résident de soumettre une nouvelle requête de travail aux intervenants.
     */
    private void soumettreRequeteTravail() {
        while (true) {
            clearConsole();
            println("Soumettre une nouvelle requête de travail:\n\n");
            print("Titre du travail à réaliser: ");
            reader.nextLine();
            println("Description détaillée: ");
            reader.nextLine();
            print("Type: ");
            reader.nextLine();
            print("Date de début espéré (YYYY/MM/DD): ");
            reader.nextLine();
            while (true) {
                println("1) Confirmer; 2) Modifier; 3) Revenir au menu");
                switch (reader.nextLine()) {
                    case "1":
                        println("La requête a été soumise avec succès.");
                        println("Appuyez sur n'importe quelle touche");
                        reader.nextLine();
                        return;
                    case "2":
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }
        }
    }

    /**
     * Permet aux résidents de consulter les travaux en cours ou futurs et de filtrer cette liste.
     */
    private void consulterTravaux() {
        while (true) {
            clearConsole();
            println("Travaux en cours ou futurs: \n\n");
            println("\t- Rénovation d'une maison; (En cours)");
            println("\t- Travail sur le réseau électrique; (En cours)");
            println("\t- Maintenance des routes; (Date de début anticipé: 2024/10/11)");
            println("1) Rechercher; 2) Filtrer; 3) Revenir");
            while (true) {
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage de la barre de recherche...");
                        println("Appuyer sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "2":
                        println("Affichage des filtres appliquables...");
                        println("Appuyer sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }
            
        }        
    }

    /**
     * Permet aux résidents de donner un avis sur un travail terminé.
     */
    private void donnerAvis() {
        
        while (true) {
            clearConsole();
            println("Donner un avis sur un projet terminé");
            println("\n");
            print("ID du projet > ");
            reader.nextLine();
            println("Donner votre avis sur le projet et/ou sur l'intervenant: ");
            reader.nextLine();
            while (true) {
                println("1) Confirmer; 2) Modifier; 3) Revenir");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Envoi avec succès. Merci pour le feedback.");
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        return;                        
                    case "2":
                        break;
                    case "3":
                        return;
                    
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }

            
        }
    }

    /**
     * Affiche les notifications de travaux terminés. Permet également d'accéder à la fonctionnalité de donner un avis sur un projet.
     */
    private void afficherTravauxTermines() {
        while (true) {
            clearConsole();
            println("ID:0002 - Projet `Rénovation au pavillon André-Aisenstadt` est terminé.");
            println("1) Donner votre avis sur un projet; 2) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    donnerAvis();
                    continue;        
                case "2":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer");
            }
        }

    }

    /**
     * Affiche les notifications des requêtes. Cela permet à l'utilisateur de faire le suivi de sa requête et d'accepter/refuser une candidature.
     */
    private void afficherRequetes() {
        while (true) {
            clearConsole();
            println("Notifications de requêtes: ");
            println("\n");
            println("ID: 1 - Retour sur votre requête de travail `Besoin de rénovation au HEC.`");
            while (true) {
                println("1) Prendre une décision sur une requête; 2) Revenir");
                switch (reader.nextLine()) {
                    case "1":
                        println("Affichage de page pour accepter requête...");
                        println("Appuyer sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                        
                    case "2":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer.");
                        continue;
                }
                break;
            }
            
        }
    }


    /**
     * Affiche toutes les notifications et permet d'accéder à plusieurs fonctionnalités tels que la modification des abonnements des notifications, le suivi des requêtes de travail et donner un avis sur un travail/intervenant.
     */
    private void pageNotifications() {
        while (true) {
            clearConsole();
            println("Notifications: ");
            println("\t-Projet `Construction sur le pont Jacques-Cartier` a commencé dans votre quartier (ID:0000).");
            println("\t-Modification des détails du projet `Construction au pavillon Roger-Gaudry` (ID:0001).");
            println("\t-Projet `Rénovation au pavillon André-Aisenstadt` est terminé (ID:0002).");
            println("\t-Retour sur votre requête de travail `Besoin de rénovation au HEC.`");
            println("\n");
            while (true) {
                println("1) Modifier les abonnements aux notifications.");
                println("2) Afficher les requêtes de travail");
                println("3) Afficher les travaux terminés");
                println("4) Revenir");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage page pour modifier les notifications...");
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "2":
                        afficherRequetes();
                        break;
                    case "3":
                        afficherTravauxTermines();
                        break;
                        
                    case "4":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer.\n");
                }
                break;
            }


        }
    }

    /**
     * Permet à un intervenant de voir la liste des requêtes de travail et de déposer sa candidature s'il le désire.
     */
    private void consulterRequetes() {
        clearConsole();
        println("Voici les requêtes de travail:\n");
        println("Lorem Ipsum/Déposé par User1");
        println("FooBar/Déposé par User2");
        println("");
        while (true) {
            println("1) Soumettre sa candidature; 2) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    println("Affichage de la page pour soumettre sa candidature...");    
                    continue;
                case "2":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer.");
            }
        }
        

    }


    /**
     * Permet de modifier les informations reliés aux chantiers.
     */
    private void modifierInfosChantiers() {
        

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

    /**
     * Affiche la liste des chantiers qu'un intervenant participe et permet d'accéder à la fonctionnalité de modification des informations d'un chantier.
     */
    private void afficherChantiers() {
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
    };

    /**
     * Permet de soumettre un nouveau projet de travail.
     */
    private void soumettreNouveauTravail() {
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
        println("Date de début (YYYY/MM/DD): ");
        reader.nextLine();
        println("Date de fin (YYYY/MM/DD): ");
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

    /**
     * Affiche le profil et permet de modifier le profil
     * @param user - Un résident ou un intervenant
     */
    private void afficherProfil(User user) {

        while (true) {
            clearConsole();
            println("Profil: ");
            if (user instanceof Intervenant) {
                println("Compte: Intervenant");
            } else {
                Resident temp = (Resident)user;
                println("Compte: Résident");
                println("Adresse résidentielle: "+temp.getAdresse());
            }
            println("Nom: "+user.getName());
            println("Email: "+user.getEmail());
            while (true) {
                println("1) Modifier le profil; 2) Revenir");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage de la page de modification du profil...");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        break;
                    case "2":
                        return;
                
                    default:
                        println("Mauvais choix, veuillez réessayer.");
                        continue;
                }
                break;
            }

        }
        
    }

    /**
     * Affiche le menu principal pour les intervenants
     * @param user
     */
    public void showMenuPrincipalIntervenant(User user) {

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
                    afficherProfil(user);
                    continue;
                case "5":
                    System.exit(0);
                default:
                    println("Mauvais choix, veuillez réessayer!");
                    return;
            }
        }

    };

    /**
     * Permet aux résidents de signaler un problème
     */
    private void signalerProbleme(){
        while (true) {
            clearConsole();
            println("Signaler un problème");
            println("\n");
            print("Type du problème: ");
            reader.nextLine();
            print("Description du problème: ");
            reader.nextLine();
            while (true) {
                println("1) Confirmer; 2) Modifier; 3) Revenir au menu principal");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Le formulaire a été envoyé avec succès!");
                        println("Appuyer sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        return;
                    case "2":
                        break;
                
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer!");
                        continue;
                }
                break;
            }
  
        }
        
    }


// Méthodes qui facileront la vie pour ne pas à avoir à écrire System.out à chaque fois
// =========================================================================================
    private void print(String msg) {
        System.out.print(msg);
    }


    private void println(String msg) {
        System.out.println(msg);
    }
// ============================================================================================
    /**
     * Crée les comptes prédéfinis pour simuler les cas d'utilisations
     * @return <code>HashMap</code> représentant un dictionnaire qui map les emails aux utilisateurs de l'application.
     */
    private static HashMap<String, User> createAccounts() {
        User testResident = new Resident("Test", "2003/04/16", "test_resident@gmail.com", "qwerty", "3200 rue Jean-Brillant");
        User testIntervenant = new Intervenant("Test", "test_intervenant@gmail.com", "qwerty", "privé", "00000000");
        HashMap<String, User> users = new HashMap<String, User>();
        users.put(testResident.getEmail(), testResident);
        users.put(testIntervenant.getEmail(), testIntervenant);
        return users;
    }
}
