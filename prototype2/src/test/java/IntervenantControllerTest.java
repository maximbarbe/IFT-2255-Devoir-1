import java.io.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.IntervenantController;



public class IntervenantControllerTest {


    @BeforeEach
    public void setIntervenantFile() throws IOException{
        IntervenantController.setIntervenantFile("src/test/testIntervenants.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(IntervenantController.getIntervenantFile()));
        writer.close();
    };

    @AfterEach
    public void resetIntervenantFile() {
        IntervenantController.setIntervenantFile("src/intervenants.csv");
    };

    @Test
    public void createIntervenantTestSucces() {
        // Dans ce test, on s'attend à un succès puisqu'on essaie de créer des comptes intervenants avec des données valides.
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000") == 0);
        assertTrue(IntervenantController.createIntervenant("Tester", "entreprise_publique", "intervenant1@gmail.com", "password123456789", "00000001") == 0);
    }

    @Test
    public void createIntervenantTestEchecEmailExistant() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer un intervenant avec un email qui existe déjà.
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000") == 0);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000001") == 3);

    }

    @Test
    public void createIntervenantTestEchecIdentifiantExistant() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer un intervenant avec un identifiant qui existe déjà.
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000") == 0);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant1@gmail.com", "password123", "00000000") == 1);

    }

}
