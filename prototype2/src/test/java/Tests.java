import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Controllers.RequeteController;
import org.prototype.Controllers.ResidentController;
import org.prototype.Controllers.TravailController;
import org.prototype.Models.Requete;

import java.util.ArrayList;

class Tests {


    @AfterEach
    public void resetFiles() {
        IntervenantController.setIntervenantFile("src/intervenants.csv");
        RequeteController.setRequetesFile("src/requetes.csv");
        ResidentController.setResidentFile("src/residents.csv");
        TravailController.setTravauxFile("src/travaux.csv");
    }


    @Test
    public void createRequeteAndSaveToFile() {


        RequeteController.setRequetesFile("src/test/testRequetes.csv");
        ArrayList<Requete> requetes = RequeteController.getRequetes();
        Requete expectedRequete;
        if (requetes.size() == 0) {
            expectedRequete = new Requete(0, "test requete", "just un test d'une requete", "souterrain", "2025-01-01", "test@gmail.com", "outremont");
        } else {
            expectedRequete = new Requete(requetes.size(), "test requete", "just un test d'une requete", "souterrain", "2025-01-01", "test@gmail.com", "outremont");
        }
        RequeteController.creerRequete("test requete", "just un test d'une requete", "souterrain", "2025-01-01", "test@gmail.com", "outremont");
        requetes = RequeteController.getRequetes();
        if (requetes.size() == 0) {
            fail("La requête n'a pas été sauvegardée dans le fichier");
        }
        Requete resultRequete = requetes.get(requetes.size() - 1);
        // On vérifie si chaque field est égal
        assertEquals(expectedRequete.getRequeteId(), resultRequete.getRequeteId());
        assertEquals(expectedRequete.getTitre(), resultRequete.getTitre());
        assertEquals(expectedRequete.getDescription(), resultRequete.getDescription());
        assertEquals(expectedRequete.getType(), resultRequete.getType());
        assertEquals(expectedRequete.getStatut(), resultRequete.getStatut());
        assertEquals(expectedRequete.getDateDebutEspere(), resultRequete.getDateDebutEspere());
        assertEquals(expectedRequete.getUserID(), resultRequete.getUserID());
        assertEquals(expectedRequete.getQuartier(), resultRequete.getQuartier());
    }

}