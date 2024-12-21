import java.io.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.CandidatureController;
import org.prototype.Models.Candidature;
import org.prototype.Models.StatutCandidature;


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
        // Restore l'état initial
        CandidatureController.setCandidaturesFile("src/candidatures.csv");
    }


    @Test
    public void creerCandidatureSucces() {
        // Dans ce test, on s'attend à un succès puisqu'on crée une candidature avec des données valides.
        assertTrue(CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "") == 0);

    }

    @Test
    public void creerCandidatureEchecCandidatureDejaEnvoyee() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer une candidature à une requête dont une candidature a déjà été déposée par le même intervenant.
        CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "");
        assert(CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "")==2);
    }

    @Test
    public void creerCandidatureEchecDateInvalide() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer une candidature avec des données invalides.
        assert(CandidatureController.createCandidature("0", "2025-12-17", "2024-01-01", "tester", "")==1);
    }

    @Test
    public void updateCandidatureTestSucces() {
        // Dans ce test, on s'attend à un succès puisqu'on essaie d'update une candidature avec des données valides.
        CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "");
        Candidature expected = new Candidature("0", "tester", "2025-12-17", "2026-01-01", "message_test");
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
        // Dans ce test, on veut voir si après la composition par l'opération inverse, si on arrive au résultat initial.
        CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "");
        Candidature expected = new Candidature("0", "tester", "2025-12-17", "2026-01-01", "message_test");
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
        // Dans ce test, on essaie d'update une candidature non-existente, on s'attend à ce que rien ne se passe.
        CandidatureController.createCandidature("0", "2025-12-17", "2026-01-01", "tester", "");
        Candidature candidatureNonExistente = new Candidature("1", "tester", "2025-12-17", "2026-01-01", "message_test");
        CandidatureController.updateCandidature(candidatureNonExistente);
        ArrayList<Candidature> candidatures = CandidatureController.getCandidaturesByIntervenant("tester");
        assertTrue(candidatures.size() == 1);
        Candidature actual = candidatures.get(0);
        assertEquals("0", actual.getRequeteID());
        assertEquals("tester", actual.getIntervenant());
        assertEquals(StatutCandidature.EN_ATTENTE, actual.getStatut());
        assertEquals("", actual.getMessage());
        assertEquals("2025-12-17", actual.getDateDebut());
        assertEquals("2026-01-01", actual.getDateFin());

    }




}
