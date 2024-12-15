import java.io.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Models.Intervenant;



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
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000") == 0);
        assertTrue(IntervenantController.createIntervenant("Tester", "entreprise_publique", "intervenant1@gmail.com", "password123456789", "00000001") == 0);
    }

    @Test
    public void createIntervenantTestEchec() {
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000") == 0);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000001") == 3);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant1@gmail.com", "password123", "00000000") == 1);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant2@gmail.com", "password123", "0000000") == 2);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant3gmail.com", "password123", "00000012") == 4);
        assertTrue(IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant4@gmail.com", "p", "00000013") == 4);
    }

    @Test
    public void createIntervenantExactitude() {
        IntervenantController.createIntervenant("TestIntervenant", "entrepreneur_prive", "intervenant@gmail.com", "password123", "00000000");
        ArrayList<Intervenant> intervenants = IntervenantController.getIntervenants();
        Intervenant intervenant = intervenants.get(intervenants.size() - 1);
        assertTrue(intervenant.getIdentifiantVille().equals("00000000"));
        assertTrue(intervenant.getNomComplet().equals("TestIntervenant"));
        assertTrue(intervenant.getAdresseCourriel().equals("intervenant@gmail.com"));
        assertTrue(intervenant.getType().toString().equals("ENTREPRENEUR_PRIVE"));

    }

}
