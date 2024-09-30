import java.util.HashMap;
/**
 * Classe principale de l'application MaVille..
**/
class MaVille {
    
    public static void main(String[] args) {
        

       
        Controller controller = new Controller();
        try {
            // Main loop
            while(true) {
                int res = controller.showOpeningScreen();
                switch (res) {
                    case 1:
                        boolean success = controller.registerUser();
                        if (!success) {
                            continue;
                        }
                        break;
                    case 2:
                        // User user = controller.login();
                        // break;
                    default:
                        continue;
                        
                }
                
                
                
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture, veuillez relancer l'application.");
            System.exit(1);
        };
        
        
    }



}