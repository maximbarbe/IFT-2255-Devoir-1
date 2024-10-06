import java.util.Scanner;
import java.util.HashMap;

/**
 * Classe qui agira comme contrôleur de l'application, c'est cette classe qui s'occupera d'afficher les menus, d'obtenir les inputs d'utilisateurs et de gérer les comptes/travaux.
 */
public class Controller {
    
    private Scanner reader = new Scanner(System.in);
    private HashMap<String, User> users = createAccounts();

    /**
     * Affiche l'écran d'accueil lorsqu'on ouvre l'application et prompt l'utilisateur à s'inscrire ou à se connecter.
     * @return <code>int</code> l'entier entré par l'utilisateur ou <code>3</code> si l'entrée est invalide.
     */
    public int showOpeningScreen() {
        System.out.println("MaVille");
        System.out.println();
        System.out.println("1) S'inscrire");
        System.out.println("2) Se connecter");
        System.out.print("Votre choix: > ");
        String choice =reader.nextLine();
        switch (choice) {
            case "1":
            case "2":
                return Integer.parseInt(choice);
            default:
                System.out.println("Mauvais choix, veuillez réessayer.");
                return 3;
        }
    };

    /**
     * Crée un nouveau résident <b><i>(ne fait aucune vérification, utilisé uniquement pour simuler)</i></b>
     * @return <code>true</code> si l'inscription a été un succès <code>false</code> sinon.
     */
    private boolean registerResident() {
        while (true) {
            System.out.println("Formulaire d'inscription pour résident:");
            System.out.print("Nom complet > ");
            String name = reader.nextLine();
            System.out.print("Date de naissance (Format:AAAA/MM/JJ) > ");
            String bday = reader.nextLine();
            System.out.print("Adresse courriel > ");
            String email = reader.nextLine();
            System.out.print("Mot de passe > ");
            String password =reader.nextLine();
            System.out.print("Téléphone (optionnel) > ");
            String telephone = reader.nextLine();
            System.out.print("Adresse résidentielle > ");
            String adresse = reader.nextLine();
            System.out.println("1) Modifier; 2) Confirmer; 3) Annuler");
        
            switch (reader.nextLine()) {
                case "1":
                    continue;
                case "2":
                    return true;
                case "3":
                    return false;
                default:
                    System.out.println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }
    
        




    }

    private boolean registerIntervenant(){
        while (true) {
            System.out.println("Formulaire d'inscription pour intervenant.");
            System.out.print("Nom complet > ");
            String name = reader.nextLine();
            System.out.print("Adresse courriel > ");
            String email = reader.nextLine();
            System.out.print("Mot de passe > ");
            String password =reader.nextLine();
            System.out.println("Type: (1) Entreprise publique, (2) Entrepreneur privé, (3) Particulier");
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
                    System.out.println("Mauvais choix, veuillez recommencer.");
                    continue;
            }
            System.out.print("Code fourni par la ville > ");
            String code = reader.nextLine();

            System.out.println("1) Modifier; 2) Confirmer; 3) Annuler");
        
            switch (reader.nextLine()) {
                case "1":
                    continue;
                case "2":
                    return true;
                case "3":
                    return false;
                default:
                    System.out.println("Mauvais choix, retour à l'écran d'accueil");
                    return false;
            }
        }
    }

    public boolean registerUser() {
        while (true) {
            System.out.println("1) Résident");
            System.out.println("2) Intervenant");
            System.out.println("3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    if (registerResident()) {
                        System.out.println("Compte créé avec succès!");
                        System.out.println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        clearConsole();
                        return true;
                    }
                    return false;
                    
                case "2":
                    if (registerIntervenant()) {
                        System.out.println("Compte créé avec succès!");
                        System.out.println("Appuyez sur n'importe quelle touche pour continuer.");
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

    private void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Authentifie un utilisateur et lui donne accès à l'application
     * @return - l'utilisateur authentifié
     */
    public User loginUser() {
        while (true) {
            System.out.print("Adresse courielle > ");
            String email = reader.nextLine();
            System.out.print("Mot de passe > ");
            String password = reader.nextLine();
            if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
                return users.get(email);
            } else {
                
                System.out.println("\nErreur! Mauvais email ou mauvais mot de passe.\n");
                System.out.print("1) Réessayer; 2) Revenir > ");
                switch (reader.nextLine()) {
                    case "1":
                        continue;
                    case "2":
                        return null;
                    default:
                        System.out.println("Mauvais choix, retour à la page d'accueil");
                        return null;
                }

            }
        }
    }

    public void showMenuPrincipalResident() {
        clearConsole();
        System.out.println("1) Travaux");
        System.out.println("2) Profil");
        System.out.println("3) Notifications");
        System.out.println("4) Signaler un problème");
        System.out.println("5) Quitter");
        System.out.print("Votre choix > ");
        String res = reader.nextLine();
        switch (res) {
            case "1":
                // Do something
                break;
            case "2":
                // Do something
                break;
            case "3":
                // Do something
            case "4":
                // Do something
            case "5":
                System.exit(0);
            default:
                System.out.println("Mauvais choix, veuillez réessayer");
        }
    };

    public void showMenuPrincipalIntervenant() {
        System.out.println("Menu Principal");
        System.out.println("1) Soumettre un nouveau projet de travail.");
        System.out.println("2) Consulter les requêtes de travail");
        System.out.println("3) Mettre à jour les informations sur un chantier");
        System.out.println("4) Profil");
        System.out.println("5) Quitter");
        while (true) {
            String code = reader.nextLine();
            switch (code) {
                case "1":
                case "2":
                case "3":
                case "4":
                    continue;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Mauvais choix, veuillez réessayer!");
                    return;
            }
        }

    };


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
