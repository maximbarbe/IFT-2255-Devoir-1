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
        // Test pour voir si l'update de plage horaire avec des données valides fonctionnes
        PlageHoraire plageHoraire = PlageHoraireController.creerPlageHoraire("10:00-15:00", "09:00-18:00", "23:00-23:59", "22:00-22:30", "15:00-16:00", "01:00-02:00", "03:00-04:30");
        PlageHoraireController.savePlageHoraire(plageHoraire);
        assertTrue(PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "15:00", "17:00")==true);
    }

    @Test
    public void updatePlageHoraireSanitaire() {
        // Test pour voir si la composition par opération inverse donne la plage initiale
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
        // Test pour voir si on met une heure invalide donne un échec
        PlageHoraire plageHoraire = PlageHoraireController.creerPlageHoraire("10:00-15:00", "09:00-18:00", "23:00-23:59", "22:00-22:30", "15:00-16:00", "01:00-02:00", "03:00-04:30");
        PlageHoraireController.savePlageHoraire(plageHoraire);
        assertTrue(PlageHoraireController.updatePlageHoraire(plageHoraire, 1, "15:00", "23:64")==false);

    }



}
