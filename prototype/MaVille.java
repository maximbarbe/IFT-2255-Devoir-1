import java.util.HashMap;
/**
 * Classe principale de l'application MaVille.
**/
class MaVille {
    
    public static void main(String[] args) {
        

       
        Controller controller = new Controller();
        try {
            // Main loop
            while(true) {
                // Affichage de l'écran d'ouverture
                int res = controller.showOpeningScreen();
                switch (res) {
                    case 1:
                        // Inscription d'un nouvel utilisateur.
                        boolean success = controller.registerUser();
                        if (!success) {
                            continue;
                        }
                        break;
                    case 2:
                        User user = controller.loginUser();
                        if (user.equals(null)) {
                            continue;
                        }
                    default:
                        continue;
                        
                }
                
            //!affichage page principale
                
            }
        // Gére toutes erreurs reliées avec l'IO.
        } catch (Exception e) {
            System.out.println("Erreur de lecture, veuillez relancer l'application.");
            System.exit(1);
        };
        
        
    }



}