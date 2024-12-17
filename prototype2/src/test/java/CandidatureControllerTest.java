import java.io.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.CandidatureController;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Models.Candidature;
import org.prototype.Models.Intervenant;



public class CandidatureControllerTest {

    @BeforeEach
    public void setUp() throws IOException{
        CandidatureController.setCandidaturesFile("src/test/testCandidatures.csv");
        // Clear le contenu du fichier
        BufferedWriter writer = new BufferedWriter(new FileWriter(CandidatureController.getCandidaturesFile()));
        writer.close();
    }

    @AfterEach
    public void getInitialState() {
        CandidatureController.setCandidaturesFile("src/candidatures.csv");
    }


    @Test
    public void creerCandidatureSucces() {
        assertTrue(CandidatureController.createCandidature("0", "2024-12-17", "2025-01-01", "tester", "") == 0);

    }

    @Test
    public void creerCandidatureEchecCandidatureDejaEnvoyee() {
        CandidatureController.createCandidature("0", "2024-12-17", "2025-01-01", "tester", "");
        assert(CandidatureController.createCandidature("0", "2024-12-17", "2025-01-01", "tester", "")==2);
    }

    @Test
    public void creerCandidatureEchecDateInvalide() {
        assert(CandidatureController.createCandidature("0", "2024-12-17", "2024-01-01", "tester", "")==1);
    }

    @Test
    public void updateCandidatureTestSucces() {
        CandidatureController.createCandidature("0", "2024-12-17", "2025-01-01", "tester", "");
        Candidature expected = new Candidature("0", "tester", "2024-12-17", "2025-01-01", "message_test");
        CandidatureController.updateCandidature(expected);
        Candidature actual = CandidatureController.getCandidaturesByIntervenant("tester").get(0);
        assertEquals(expected.getRequeteID(), actual.getRequeteID());
        assertEquals(expected.getIntervenant(), actual.getIntervenant());
        assertEquals(expected.getStatut(), actual.getStatut());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getDateDebut(), actual.getDateDebut());
        assertEquals(expected.getDateFin(), actual.getDateFin());
    }
    @Test
    public void updateCandidatureTestSanitaire() {
        CandidatureController.createCandidature("0", "2024-12-17", "2025-01-01", "tester", "");
        Candidature expected = new Candidature("0", "tester", "2024-12-17", "2025-01-01", "message_test");
        CandidatureController.updateCandidature(expected);
        expected.setMessage("");
        CandidatureController.updateCandidature(expected);
        Candidature actual = CandidatureController.getCandidaturesByIntervenant("tester").get(0);
        assertEquals(expected.getRequeteID(), actual.getRequeteID());
        assertEquals(expected.getIntervenant(), actual.getIntervenant());
        assertEquals(expected.getStatut(), actual.getStatut());
        assertEquals(expected.getMessage(), "");
        assertEquals(expected.getDateDebut(), actual.getDateDebut());
        assertEquals(expected.getDateFin(), actual.getDateFin());
    }
    @Test
    public void updateCandidatureTestCandidatureNonExistente() {


    }




}
