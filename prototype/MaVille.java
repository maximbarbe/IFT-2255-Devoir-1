import java.util.Scanner;
import java.util.HashMap;
/**
 * Classe principale de l'application MaVille..
**/
class MaVille {
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        HashMap<String, User> users = createAccounts();

        try {
            // Main loop
            while(true) {
                int choix;
                System.out.println("MaVille");
                System.out.println();
                while (true) {
                    System.out.println("1) S'inscrire");
                    System.out.println("2) Se connecter");
                    System.out.print("Votre choix: > ");
                    String choice =reader.nextLine();
                    if (!choice.equals("1") && !choice.equals("2")) {
                        System.out.println("Choix invalide, veuillez réessayer.");
                    } else {
                        choix = Integer.parseInt(choice);
                        break;
                    }
                }
                if (choix == 1) {
                    // S'inscrire
                } else {
                    // Se connecter
                }
                break;
                
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture, veuillez relancer l'application.");
            System.exit(1);
        } finally {
            reader.close();
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