import java.io.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Controllers.ResidentController;
import org.prototype.Controllers.UtilisateurController;
import org.prototype.Models.Resident;
import org.prototype.Models.Utilisateur;


public class UtilisateurControllerTest {

    @BeforeEach
    public void setUp() throws IOException{
        ResidentController.setResidentFile("src/test/testResidents.csv");
        IntervenantController.setIntervenantFile("src/test/testIntervenants.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(ResidentController.getResidentFile()));
        writer.close();
        writer = new BufferedWriter(new FileWriter(IntervenantController.getIntervenantFile()));
        writer.close();
    }

    @AfterEach
    public void getInitialState() {
        ResidentController.setResidentFile("src/residents.csv");
        IntervenantController.setIntervenantFile("src/intervenants.csv");
    }

    @Test
    public void getUtilisateurTestSucces() {
        // Test pour voir si on peut aller chercher un compte utilisateur avec des données exactes
        ResidentController.createResident("Alice Test", "1999-12-12", "alice@gmail.com","12345678", "123 rue jean", "514-300-4000", "H1C 3L6", LocalDateTime.now().toString());
        Resident user = (Resident)UtilisateurController.getUtilisateur("alice@gmail.com", "12345678");
        assertEquals("Alice Test", user.getNomComplet());
        assertEquals("1999-12-12", user.getDateDeNaissance());
        assertEquals("alice@gmail.com", user.getAdresseCourriel());
        assertEquals("123 rue jean", user.getAdresseResidentielle());
    }

    @Test
    public void getUtilisateurTestSuccesDifferentLetterCase() {
        // Test pour voir si on peut aller chercher un compte utilisateur avec un email ayant des lettercases différents
        ResidentController.createResident("Alice Test", "1999-12-12", "alice@gmail.com","12345678", "123 rue jean", "514-300-4000", "H1C 3L6", LocalDateTime.now().toString());
        Resident user = (Resident)UtilisateurController.getUtilisateur("aLiCe@GmAiL.com", "12345678");
        assertEquals("Alice Test", user.getNomComplet());
        assertEquals("1999-12-12", user.getDateDeNaissance());
        assertEquals("alice@gmail.com", user.getAdresseCourriel());
        assertEquals("123 rue jean", user.getAdresseResidentielle());
    }

    @Test
    public void getUtilisateurTestEchecPasswordIncorrect() {
        // Test pour voir si entrer un password incorrect, mais un email correct ne retourne pas l'utilisateur
        ResidentController.createResident("Alice Test", "1999-12-12", "alice@gmail.com","12345678", "123 rue jean", "514-300-4000", "H1C 3L6", LocalDateTime.now().toString());
        Utilisateur user = UtilisateurController.getUtilisateur("alice@gmail.com", "12345679");
        assertEquals(null, user);
    }

}
