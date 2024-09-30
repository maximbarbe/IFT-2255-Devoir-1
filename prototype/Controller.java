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
                        System.out.println("Le compte a été créé avec succès!");
                        System.out.println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        return true;
                    }
                    return false;
                    
                case "2":
                    if (registerIntervenant()) {
                        System.out.println("Le compte a été créé avec succès!");
                        System.out.println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }

        
    }


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
