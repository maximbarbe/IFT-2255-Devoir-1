import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.prototype.Controllers.EntraveController;
import org.prototype.Controllers.IntervenantController;
import org.prototype.Controllers.RequeteController;
import org.prototype.Controllers.ResidentController;
import org.prototype.Controllers.TravailController;
import org.prototype.Controllers.UtilisateurController;
import org.prototype.Models.Entrave;
import org.prototype.Models.Intervenant;
import org.prototype.Models.Requete;
import org.prototype.Models.Resident;
import org.prototype.Models.Travail;
import static org.prototype.Models.TypeIntervenant.PARTICULIER;

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

    @Test
    public void getIntervenantTest() {
        IntervenantController.setIntervenantFile("src/test/testIntervenants.csv");
        Intervenant expectedUser = new Intervenant("test","tester1@gmail.com","testpassword",PARTICULIER,"00000000");
        Intervenant resultUser = null;
        try {
            resultUser = (Intervenant) UtilisateurController.getUtilisateur("tester1@gmail.com", "testpassword");
        } catch (Exception e) {
            fail("Erreur lors du casting entre utilisateur et Intervenant");
        }
        if (resultUser == null) {
            fail("Erreur lorsqu'on essaye d'aller chercher un utilisateur par son email et password");
        }
        assertEquals(expectedUser.getNomComplet(), resultUser.getNomComplet());
        assertEquals(expectedUser.getIdentifiantVille(), resultUser.getIdentifiantVille());
        assertEquals(expectedUser.getAdresseCourriel(), resultUser.getAdresseCourriel());
        assertEquals(expectedUser.getMotDePasse(), resultUser.getMotDePasse());
    }

    @Test
    public void getEntravesTest() {
        ArrayList<Entrave> entraves = EntraveController.getEntravesByID("671a580d7649be00197b3eee");
        Entrave premiereEntrave = entraves.get(0);
        String entrave1 = ("ID du travail correspondant: " + premiereEntrave.getTravailId()+"; Nom de la rue: "+premiereEntrave.getStreetId() + "; Effet sur la rue: "+premiereEntrave.getStreetImpact());
        String expectedEntrave1 = "ID du travail correspondant: 671a580d7649be00197b3eee; Nom de la rue: rue Garnier; Effet sur la rue: Rue barrée";
        assertEquals(expectedEntrave1, entrave1);
    }

    @Test
    public void getRequeteTest() {
        RequeteController.setRequetesFile("src/test/testRequetes2.csv");
        ArrayList<Requete> requetes = RequeteController.getRequetes();
        Requete premiereRequete = requetes.get(0);
        String expectedRequête = "0,test,tester,RESIDENTIELS,2025-04-01,Verdun,resident3@gmail.com";
        String requete1 = (premiereRequete.getRequeteId() + "," + premiereRequete.getTitre() + "," + premiereRequete.getDescription() + "," + premiereRequete.getType() + "," + premiereRequete.getDateDebutEspere() + "," + premiereRequete.getQuartier() + "," + premiereRequete.getUserID());
        assertEquals(expectedRequête, requete1);
    }

}