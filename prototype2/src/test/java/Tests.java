import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;
import org.prototype.Controllers.*;
import org.prototype.Models.Requete;
import org.prototype.Models.Resident;
import org.prototype.Models.Travail;
import org.prototype.Models.Utilisateur;

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
    public void createRequeteAndSaveToFileTest() {

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

    @Test
    public void getResidentTest() {
        ResidentController.setResidentFile("src/test/testResidents.csv");
        IntervenantController.setIntervenantFile("src/test/testIntervenants.csv");
        Resident expectedUser = new Resident("test","tester1@gmail.com","testpassword","2000-01-01","","3200 rue jean-brilliant","Mercier-Hochelaga-Maisonneuve");
        Resident resultUser = null;
        try {
            resultUser = (Resident) UtilisateurController.getUtilisateur("tester1@gmail.com", "testpassword");
        } catch (Exception e) {
            fail("Erreur lors du casting entre utilisateur et résident");
        }
        if (resultUser == null) {
            fail("Erreur lorsqu'on essaye d'aller chercher un utilisateur par son email et password");
        }
        assertEquals(expectedUser.getNomComplet(), resultUser.getNomComplet());
        assertEquals(expectedUser.getQuartier(), resultUser.getQuartier());
        assertEquals(expectedUser.getAdresseResidentielle(), resultUser.getAdresseResidentielle());
        assertEquals(expectedUser.getDateDeNaissance(), resultUser.getDateDeNaissance());
        assertEquals(expectedUser.getAdresseCourriel(), resultUser.getAdresseCourriel());
        assertEquals(expectedUser.getMotDePasse(), resultUser.getMotDePasse());
        assertEquals(expectedUser.getNumTelephone(), resultUser.getNumTelephone());

    }

    @Test
    public void getTravauxByQuartierTest() {
        for (Travail t:TravailController.getTravauxByQuartier("Mercier-Hochelaga-Maisonneuve")) {
            assertTrue(t.getQuartiers().contains("Mercier-Hochelaga-Maisonneuve".toLowerCase()));
        }

    }
}