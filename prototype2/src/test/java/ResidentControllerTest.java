import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.ResidentController;
import org.prototype.Models.Resident;


public class ResidentControllerTest {


    @BeforeEach
    public void setUpFile() throws IOException{
        ResidentController.setResidentFile("src/test/testResidents.csv");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ResidentController.getResidentFile()))) {
            writer.close();
        }

    }

    @AfterEach
    public void resetFile() {
        ResidentController.setResidentFile("src/residents.csv");
    }

    @Test
    public void CreerCompteResidentTestSucces() {
        // Dans ce test, on s'attend à un succès puisqu'on essaie de créer un compte résident avec des données valides.
        ResidentController.initHashMap(); // hashmap contenant les quartiers

        Integer statuscode = ResidentController.createResident("Alice Test", "1999-12-12", "alice@gmail.com","12345678", "123 rue jean", "514-300-4000", "H1C 3L6", LocalDateTime.now().toString());
        Resident resultUser = null;
        assertTrue(statuscode == 0); // si 0 alors résident créé
        ArrayList<Resident> residents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/testResidents.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 7) {
                    Resident r = new Resident(
                            data[0], // nom
                            data[1], // date
                            data[2], // email
                            data[3], // password
                            data[4], // address
                            data[5], // phone
                            null,
                            null,
                            data[6]  // postal code
                    );
                    residents.add(r);
                }
            }
        } catch (IOException e) {
            fail("Could not read test file: " + e.getMessage());
        }

        for (Resident r:residents) {
            if (r.getAdresseCourriel().toLowerCase().equals("alice@gmail.com") ) {
                resultUser = r;
            }
        }

        if (resultUser == null) {
            fail("Erreur lorsqu'on essaye d'aller chercher un utilisateur par son email et password");
        }

        assertTrue(resultUser.getNomComplet().equals("Alice Test"));
        assertTrue(resultUser.getDateDeNaissance().equals("1999-12-12"));
        assertTrue(resultUser.getAdresseCourriel().equals("alice@gmail.com"));
        assertTrue(resultUser.getAdresseResidentielle().equals("123 rue jean"));
        assertTrue(resultUser.getNumTelephone().equals("514-300-4000"));

    }

    @Test
    public void creerCompteResidentEchecUtilisateurTropJeune() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer un résident avec un utilisateur trop jeune (< 16 ans).
        assertTrue(ResidentController.createResident("tester", "2010-01-01", "test@gmail.com", "password123", "123 rue jean", "","H1C 3L6", LocalDateTime.now().toString())==4);
    }

    @Test
    public void creerCompteResidentEchecEmailExistant() {
        // Dans ce test, on s'attend à un échec puisqu'on essaie de créer un résident avec un email déjà utilisé par un autre résident.
        ResidentController.createResident("tester", "1999-01-01", "alice@gmail.com", "password123", "123 rue jean", "","H1C 3L6", LocalDateTime.now().toString());
        assertTrue(ResidentController.createResident("Alice Test", "1999-12-12", "alice@gmail.com","12345678", "123 rue jean", "514-300-4000", "H1C 3L6", LocalDateTime.now().toString())==1);
    }

}
