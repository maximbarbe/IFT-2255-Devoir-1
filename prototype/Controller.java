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

    private boolean registerResident() {
        return true;
    }

    private boolean registerIntervenant(){
        return true;
    }

    public boolean registerUser() {
        while (true) {
            System.out.println("1) Résident");
            System.out.println("2) Intervenant");
            System.out.println("3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    return registerResident();
                    
                case "2":
                    return registerIntervenant();
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
