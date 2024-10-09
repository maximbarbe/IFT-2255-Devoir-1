/**
 * Classe principale de l'application MaVille.
**/
class MaVille {
    
    public static void main(String[] args) {
        

       
        Controller controller = new Controller();
        User user = null;
        try {
            // Main loop
            while(true) {
                // Affichage de l'écran d'ouverture
                Controller.clearConsole();
                int res = controller.showOpeningScreen();
                switch (res) {
                    case 1:
                        // Inscription d'un nouvel utilisateur.
                        boolean success = controller.registerUser();
                        if (!success) {
                            continue;
                        }
                        continue;
                        
                    case 2:
                        user = controller.loginUser();
                        if (user == null) {
                            continue;
                        }
                        break;
                    default:
                        continue;
                        
                }
            while (true) {
                // Menu principal résident
                if (user instanceof Resident) {
                    controller.showMenuPrincipalResident(user);
                } else {
                // Menu principal intervenant
                    controller.showMenuPrincipalIntervenant(user);
                }
            }
                
            }
        // Gére toutes erreurs reliées avec l'IO.
        } catch (Exception e) {
            System.out.println("Erreur de lecture, veuillez relancer l'application.");
            System.exit(1);
        };
        
        
    }



}