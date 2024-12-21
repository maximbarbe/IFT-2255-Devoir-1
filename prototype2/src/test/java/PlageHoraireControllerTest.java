import java.io.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.PlageHoraireController;
import org.prototype.Models.PlageHoraire;

public class PlageHoraireControllerTest {

    @BeforeEach
    public void setUp() throws IOException {
        PlageHoraireController.setPlagesFile("src/test/testPlagesHoraires.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(PlageHoraireController.getPlagesFile()));
        writer.close();

    }


    @AfterEach
    public void resetState() {
        PlageHoraireController.setPlagesFile("src/plageHoraire.csv");
    }


    @Test
    public void updatePlageHoraireSucces() {
        // Dans ce test, on s'attend à un succès puisqu'on essaie d'update une plage horaire avec des données valides.
        PlageHoraire plageHoraire = PlageHoraireController.creerPlageHoraire("10:00-15:00", "09:00-18:00", "23:00-23:59", "22:00-22:30", "15:00-16:00", "01:00-02:00", "03:00-04:30");
        PlageHoraireController.savePlageHoraire(plageHoraire);
        assertTrue(PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "15:00", "17:00")==true);
    }

    @Test
    public void updatePlageHoraireSanitaire() {
        // Ceci est un test sanitaire pour voir si la composition par l'opération inverse donne le résultat initial.
        PlageHoraire plageHoraire = PlageHoraireController.creerPlageHoraire("10:00-15:00", "09:00-18:00", "23:00-23:59", "22:00-22:30", "15:00-16:00", "01:00-02:00", "03:00-04:30");
        PlageHoraireController.savePlageHoraire(plageHoraire);
        PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "15:00", "17:00");
        PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "10:00", "15:00");
        int times[] = PlageHoraireController.getPlageHoraire("temp").getLundi();
        assertEquals(600, times[0]);
        assertEquals(900, times[1]);
    }

    @Test
    public void updatePlageHoraireEchec() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie d'update une plage horaire avec des données invalides.
        PlageHoraire plageHoraire = PlageHoraireController.creerPlageHoraire("10:00-15:00", "09:00-18:00", "23:00-23:59", "22:00-22:30", "15:00-16:00", "01:00-02:00", "03:00-04:30");
        PlageHoraireController.savePlageHoraire(plageHoraire);
        assertTrue(PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "15:00", "23:64")==false);

    }



}
