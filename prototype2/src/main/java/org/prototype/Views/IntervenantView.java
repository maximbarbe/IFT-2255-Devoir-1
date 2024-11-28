package org.prototype.Views;

import org.prototype.Controllers.PlageHoraireController;
import org.prototype.Controllers.RequeteController;
import org.prototype.Controllers.TravailController;
import org.prototype.MaVille;
import org.prototype.Models.*;

import java.util.ArrayList;

/**
 * Classe avec laquelle les intervenants interagissent. Permet d'accéder aux fonctionnalités qui sont propres aux intervenants.
 */
public class IntervenantView extends View implements ConnectedView{

    @Override
    public void menuPrincipal() {
        while (true) {
            clearConsole();
            println("Menu Principal: \n");
            println("1) Soumettre un nouveau projet de travail.");
            println("2) Consulter les requêtes de travail");
            println("3) Mettre à jour les informations sur un chantier");
            println("4) Profil");
            println("5) Se déconnecter");
            print("Votre choix > ");
            String code = reader.nextLine();
            switch (code) {
                case "1":
                    soumettreNouveauTravail();
                    continue;
                case "2":
                    consulterRequetes();
                    continue;
                case "3":
                    afficherChantiers();
                    continue;
                case "4":
                    afficherProfil();
                    continue;
                case "5":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer!");
                    return;
            }
        }
    }

    /**
     * Permet à un intervenant de consulter les requêtes de travail déposées par les résidents, l'intervenant peut les filtrer si désiré.
     * Permet également d'accéder à la fonctionnalité de soumettre une candidature.
     */
    public void consulterRequetes() {
        clearConsole();
        println("Requêtes: ");
        for (Requete r: RequeteController.getRequetes()) {
            if (r.getStatut().equals(RequeteStatut.OUVERTE)) {
                println("ID: "+r.getRequeteId());
                println("Titre: "+r.getTitre());
                println("Description: "+r.getDescription());
                println("Type de travail: " + r.getType().toString());
                println("Date de début espéré: " + r.getDateDebutEspere());
                println("Quartier: "+r.getQuartier());
                println("Déposé par: " + r.getUserID());
                for (int i = 0; i < 60;i++) {
                    print("=");
                }
                println("");
            }
        }
        println("");
        while (true) {
            println("1) Filtrer; 2) Soumettre sa candidature; 3) Revenir");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    println("Filtrer par 1) Type; 2) Quartier; 3) Date de debut; 4) Revenir");
                    String choice = reader.nextLine();
                    switch (choice) {
                        // Filtrer les requetes par type
                        case "1":
                            clearConsole();
                            for (int i = 0; i < TypeTravail.values().length; i++) {
                                println((i+1) + ") " + TypeTravail.values()[i].toString());
                            }
                            print("Entrez l'index du type de travail désiré > ");
                            try {
                                int index = Integer.parseInt(reader.nextLine());
                                TypeTravail s =TypeTravail.values()[index - 1];
                            
                                ArrayList<Requete> requetesFiltresByType = RequeteController.getRequeteByType(s.toString());
                                for (Requete r:requetesFiltresByType) {
                                    // On ne veut que voir les requetes ouvertes
                                    if (r.getStatut().equals(RequeteStatut.OUVERTE)) {
                                        println("ID: "+r.getRequeteId());
                                        println("Titre: "+r.getTitre());
                                        println("Description: "+r.getDescription());
                                        println("Type de travail: " + r.getType().toString());
                                        println("Date de début espéré: " + r.getDateDebutEspere());
                                        println("Quartier: "+r.getQuartier());
                                        println("Déposé par: " + r.getUserID());
                                        for (int i = 0; i < 60;i++) {
                                            print("=");
                                        }
                                        println("");
                                    }
                                }
                                continue;
                            } catch (Exception e) {
                                println("Mauvais choix, veuillez réessayer");
                                continue;
                            }
                        // Filtrer les requêtes par quartier
                        case "2":
                            clearConsole();
                            print("Entrez le quartier désiré >");
                            String quartier = reader.nextLine();
                            ArrayList<Requete> requetesFiltresByQuartier = RequeteController.getRequeteByQuartier(quartier);
                            for (Requete r:requetesFiltresByQuartier) {
                                if (r.getStatut().equals(RequeteStatut.OUVERTE)) {
                                    println("ID: "+r.getRequeteId());
                                    println("Titre: "+r.getTitre());
                                    println("Description: "+r.getDescription());
                                    println("Type de travail: " + r.getType().toString());
                                    println("Date de début espéré: " + r.getDateDebutEspere());
                                    println("Quartier: "+r.getQuartier());
                                    println("Déposé par: " + r.getUserID());
                                    for (int i = 0; i < 60;i++) {
                                        print("=");
                                    }
                                    println("");
                                }
                            }
                            continue;
                        // Filtrer les requêtes par date.
                        case "3":
                            clearConsole();
                            print("Entrez la date désirée (YYYY-MM-DD) >");
                            String date = reader.nextLine();
                            ArrayList<Requete> requetesFiltresByDate = RequeteController.getRequeteByDate(date);
                            for (Requete r:requetesFiltresByDate) {
                                if (r.getStatut().equals(RequeteStatut.OUVERTE)) {
                                    println("ID: "+r.getRequeteId());
                                    println("Titre: "+r.getTitre());
                                    println("Description: "+r.getDescription());
                                    println("Type de travail: " + r.getType().toString());
                                    println("Date de début espéré: " + r.getDateDebutEspere());
                                    println("Quartier: "+r.getQuartier());
                                    println("Déposé par: " + r.getUserID());
                                    for (int i = 0; i < 60;i++) {
                                        print("=");
                                    }
                                    println("");
                                }
                            }
                            continue;
                        case "4":
                            continue;
                        default:
                            println("Mauvais choix");
                            continue;
                    }
                case "2":
                    println("Affichage de la page pour soumettre sa candidature...");
                    continue;
                case "3":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer.");
            }
        }

    }

    /**
     * Affiche les travaux entrepris par l'intervenant et permet d'accéder à la fonctionnalité de modifier les informations sur un chantier
     */
    public void afficherChantiers() {
        while(true) {
            clearConsole();
            println("Vos chantiers: \n");
            println("\t - ID: 0187 - Rénovation de la maison d'un résident");
            println("\t - ID: 0188 - Installation de panneaux de circulation");
            println("\t - ID: 0199 - Construction d'une nouvelle ligne de la STM");
            while (true) {
                println("1) Modifier les informations d'un chantier; 2) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        modifierInfosChantiers();
                        break;
                    case "2":
                        return;

                    default:
                        println("Mauvais choix veuillez réessayer");
                        continue;
                }
                break;
            }
        }

    }
    /**
     * Permet de modifier les informations sur un chantier
     */
    public void modifierInfosChantiers() {
        while (true) {
            clearConsole();
            println("Modifier des informations du chantier (Veuillez laisser blank si on ne veut pas modifier):\n");
            print("ID du chantier: ");
            String id = reader.nextLine();
            print("Description du projet: ");
            reader.nextLine();
            print("Date de fin prévue: ");
            reader.nextLine();
            print("Statut du projet: ");
            reader.nextLine();
            while (true) {
                println("1) Confirmer les changements; 2) Annuler");
                print("Votre choix > ");
                switch(reader.nextLine()) {
                    case "1":
                        println("Changements effectués");
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        return;
                    case "2":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                }
            }


        }
    }
    /**
     * Permet de soumettre un nouveau projet de travail.
     */
    public void soumettreNouveauTravail() {
        while (true) {
            clearConsole();
            println("Veuillez remplir le formulaire suivant pour soumettre un nouveau projet de travail: ");
            println("Titre: ");
            String titre = reader.nextLine();
            println("Description du projet: ");
            String description = reader.nextLine();
            TypeTravail[] types = TypeTravail.values();
            for (int i = 0; i < types.length; i++) {
                println((i+1)+") " + types[i].toString());
            }
            println("Entrez l'index du type de travail");
            String idx = reader.nextLine();
            TypeTravail typeChoisi;
            try {
                typeChoisi = types[Integer.parseInt(idx) - 1];
            } catch (Exception e) {
                println("Entrez un index valide");
                println("Appuyez sur n'importe quelle touche pour continuer");
                continue;
            }
            println("Quartiers affectés (séparés par des virgules): ");
            String quartiers = reader.nextLine();
            println("Rues affectées (séparés par des virgules): ");
            String rues = reader.nextLine();
            println("Date de début (YYYY-MM-DD): ");
            String dateDebut = reader.nextLine();
            println("Date de fin (YYYY-MM-DD): ");
            String dateFin = reader.nextLine();
            println("Horaire des travaux: ");
            print("Lundi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String lundi = reader.nextLine();
            print("Mardi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String mardi = reader.nextLine();
            print("Mercredi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String mercredi = reader.nextLine();
            print("Jeudi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String jeudi = reader.nextLine();
            print("Vendredi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String vendredi = reader.nextLine();
            print("Samedi (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String samedi = reader.nextLine();
            print("Dimanche (HH:MM-HH:MM ou laisser blank pour aucune): ");
            String dimanche = reader.nextLine();
            PlageHoraire plage = PlageHoraireController.creerPlageHoraire(lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche);
            if (plage == null) {
                println("Vous avez entré une plage invalide, veuillez réessayer");
                println("Appuyez sur n'importe quelle touche pour continuer");
                reader.nextLine();
                continue;
            }
            String[] quartiersAffectes = quartiers.split(",");
            String[] ruesAffectees = rues.split(",");
            if (!TravailController.doQuartiersExist(quartiersAffectes)) {
                println("Certains des quartiers entrés n'existent pas, veuillez réessayer");
                println("Appuyez sur n'importe quelle touche pour continuer");
                reader.nextLine();
                continue;
            }
            ArrayList<PlageHoraire> plagesHorairesQuartier = PlageHoraireController.getPlageHoraireByQuartiers(quartiersAffectes);
            int conflits = PlageHoraireController.nombreConflits(plage, plagesHorairesQuartier);
            if (conflits != 0) {
                println("Vous avez des conflits d'horaire avec certains des résidents des quartiers que vous avez listé, veuillez vérifier leur préférences");
            }
            while (true) {
                println("1) Consulter les préférences; 2) Confirmer; 3) Annuler");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        for (PlageHoraire p: plagesHorairesQuartier) {
                            println("Plage horaire de " + p.getId());
                            println(String.format("Lundi: " + ((p.getLundi()[0] == p.getLundi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getLundi()[0]/60, p.getLundi()[0]%60, p.getLundi()[1]/60, p.getLundi()[1]%60));
                            println(String.format("Mardi: " + ((p.getMardi()[0] == p.getMardi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getMardi()[0]/60, p.getMardi()[0]%60, p.getMardi()[1]/60, p.getMardi()[1]%60));
                            println(String.format("Mercredi: " + ((p.getMercredi()[0] == p.getMercredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getMercredi()[0]/60, p.getMercredi()[0]%60, p.getMercredi()[1]/60, p.getMercredi()[1]%60));
                            println(String.format("Jeudi: " + ((p.getJeudi()[0] == p.getJeudi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getJeudi()[0]/60, p.getJeudi()[0]%60, p.getJeudi()[1]/60, p.getJeudi()[1]%60));
                            println(String.format("Vendredi: " + ((p.getVendredi()[0] == p.getVendredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getVendredi()[0]/60, p.getVendredi()[0]%60, p.getVendredi()[1]/60, p.getVendredi()[1]%60));
                            println(String.format("Samedi: " + ((p.getSamedi()[0] == p.getSamedi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getSamedi()[0]/60, p.getSamedi()[0]%60, p.getSamedi()[1]/60, p.getSamedi()[1]%60));
                            println(String.format("Dimanche: " + ((p.getDimanche()[0] == p.getDimanche()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), p.getDimanche()[0]/60, p.getDimanche()[0]%60, p.getDimanche()[1]/60, p.getDimanche()[1]%60));
                            println("============================================================================");
                            println("");

                        }
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        continue;
                    case "2":

                        Travail travail = TravailController.creerTravail(titre, description, typeChoisi, quartiersAffectes, ruesAffectees, dateDebut, dateFin);
                        if (travail == null) {
                            println("Les dates entrées sont invalides, veuillez réessayer");
                            println("Appuyez sur n'importe quelle touche pour continuer");
                            reader.nextLine();
                            continue;
                        } else {
                            plage.setId(travail.getId());
                            PlageHoraireController.savePlageHoraire(plage);
                            println("Appuyez sur n'importe quelle touche pour continuer");
                            reader.nextLine();
                        }

                        return;
                    case "3":
                        println("Annulation du formulaire");
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        break;
                }
            }
        }
    }
    @Override
    public void afficherProfil(){
        while (true) {
            clearConsole();
            Intervenant user = (Intervenant) MaVille.getCurUser();
            println("Profil: ");
            println("Compte: Intervenant");
            println("Nom: "+user.getNomComplet());
            println("Email: "+user.getAdresseCourriel());
            println("Type: "+user.getType());
            while (true) {
                println("1) Modifier le profil; 2) Revenir");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage de la page de modification du profil...");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        break;
                    case "2":
                        return;

                    default:
                        println("Mauvais choix, veuillez réessayer.");
                        continue;
                }
                break;
            }

        }
    };
}
