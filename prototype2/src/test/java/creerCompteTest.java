import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Controllers.ResidentController;
import org.prototype.Models.Intervenant;
import org.prototype.Models.Resident;

public class creerCompteTest {

    @AfterEach
    public void resetFiles() {
        ResidentController.setResidentFile("src/residents.csv");
        IntervenantController.setIntervenantFile("src/intervenants.csv");
    }

    @BeforeEach
    public void setUpFile() throws IOException{
        ResidentController.setResidentFile("src/test/testResidents.csv");
        
        IntervenantController.setIntervenantFile("src/test/testIntervenants.csv");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ResidentController.getResidentFile()))) {
            //to clear the file
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(IntervenantController.getIntervenantFile()))) {
            //to clear the file
        }
        
    }

    @Test
    public void testCreerCompteResident() {
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
    public void testCreerCompteIntervenant() {
        Integer statuscode = IntervenantController.createIntervenant("Bob Test", "particulier", "bob@gmail.com", "bob12345678", "14156533");
        Intervenant resultUser = null;
        assertTrue(statuscode == 0); // si 0 alors intervenant créé
        ArrayList<Intervenant> intervenants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/testIntervenants.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 5) {
                    Intervenant i = new Intervenant(
                        data[0], // nom
                        data[1], // email
                        data[2], // password
                        null, 
                        data[4]  // identifiantVille
                    );
                    intervenants.add(i);
                }
            }
        } catch (IOException e) {
            fail("Could not read test file: " + e.getMessage());
        }

        for (Intervenant i:intervenants) {
            if (i.getAdresseCourriel().toLowerCase().equals("bob@gmail.com") ) {
                resultUser = i;
            }
        }
        if (resultUser == null) {
            fail("Erreur lorsqu'on essaye d'aller chercher un utilisateur par son email et password");
        }
        assertTrue(resultUser.getNomComplet().equals("Bob Test"));
        assertTrue(resultUser.getAdresseCourriel().equals("bob@gmail.com"));
        assertTrue(resultUser.getIdentifiantVille().equals("14156533"));            

    }

    //Tester si on essaye de créer un compte avec des donnés non valides
    // si le compte se créé ou donne un message d'erreur
    @Test
    public void testDonnesInvalides() {

        // tester "@bob@gmail.com" ce qui est un mauvais format
        Integer statuscode = IntervenantController.createIntervenant("Bob Test", "particulier", "@bob@gmail.com", "bob12345678", "14156533");
        assertTrue(statuscode == 4); // si 4 alors mauvais format

        //utiliser un identifiant de la ville deux fois
        Integer statuscode2 = IntervenantController.createIntervenant("Bob Test", "particulier", "bob@gmail.com", "bob12345678", "14156533");
        Integer statuscode3 = IntervenantController.createIntervenant("Carl Test", "particulier", "carl@gmail.com", "carl12345678", "14156533");
        assertTrue(statuscode2 == 0); // pas de problème, intervenant créé
        assertTrue(statuscode3 == 1); // si 1 alors identifiant de la ville déjà utilisé

        //identifiant n'est pas 8 chiffres
        Integer statuscode4 = IntervenantController.createIntervenant("Bob Test", "particulier", "bob@gmail.com", "bob12345678", "123");
        assertTrue(statuscode4 == 2); // si 2 alors identifiant de la ville n'est pas 8 chiffres
    }   
}
