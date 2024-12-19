package org.prototype.Views;

import org.prototype.Controllers.*;
import org.prototype.MaVille;
import org.prototype.Models.*;
import java.time.LocalDateTime;
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
            println("4) Mes candidatures");
            println("5) Profil");
            println("6) Se déconnecter");
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
                    pageCandidatures();
                    continue;
                case "5":
                    afficherProfil();
                    continue;
                case "6":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer!");
                    return;
            }
        }
    }

    public void pageCandidatures() {
        while (true) {
            clearConsole();
            println("Mes candidatures: ");
            ArrayList<Candidature> candidatures = CandidatureController.getCandidaturesByIntervenant(MaVille.getCurUser().getAdresseCourriel());
            for (int i = 0; i < candidatures.size(); i++) {
                String statut;
                if (candidatures.get(i).getStatut() == StatutCandidature.EN_ATTENTE) {
                    statut = "Pas de réponse";
                } else if(candidatures.get(i).getStatut() == StatutCandidature.ACCEPTE) {
                    statut = "Candidature acceptée";
                } else if (candidatures.get(i).getStatut() == StatutCandidature.REFUSEE ){
                    statut = "Candidature refusée";
                } else {
                    statut = "Candidature confirmée";
                }
                println((i+1) + ") " + "Requête: " + candidatures.get(i).getRequeteID() + "; Statut: " + statut + "; Date de début prévu: " + candidatures.get(i).getDateDebut() + "; Date de fin prévu: "+candidatures.get(i).getDateFin());
            }


            println("");
            println("1) Soustraire une candidature");
            println("2) Confirmer une candidature acceptée");
            println("3) Revenir");
            print("Votre choix > ");
            switch (reader.nextLine()) {
                case "1":
                    clearConsole();
                    candidatures = CandidatureController.getCandidaturesByIntervenant(MaVille.getCurUser().getAdresseCourriel());
                    candidatures.removeIf(c -> c.getStatut().equals(StatutCandidature.CONFIRMEE));
                    for (int i = 0; i < candidatures.size(); i++) {
                        println((i+1) + ") Requête: " + candidatures.get(i).getRequeteID() + "; Date de début: " + candidatures.get(i).getDateDebut() + "; Date de fin: " + candidatures.get(i).getDateFin() + "; Statut: " + candidatures.get(i).getStatut().toString());
                    }
                    print("Entrez l'index de la candidature à soustraire > ");
                    try {
                        Candidature candidatureAEnlever = candidatures.get(Integer.parseInt(reader.nextLine()) - 1);
                        CandidatureController.removeCandidature(candidatureAEnlever);
                        println("La candidature a été soustraite.");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                    } catch (Exception e) {
                        println("Vous n'avez pas entré un index valide. Veuillez réessayer");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        continue;
                    }
                case "2":
                    clearConsole();
                    println("Mes candidatures confirmées: ");
                    candidatures = CandidatureController.getCandidaturesByIntervenant(MaVille.getCurUser().getAdresseCourriel());
                    candidatures.removeIf(c -> !c.getStatut().equals(StatutCandidature.ACCEPTE));
                    for (int i = 0; i < candidatures.size(); i++) {
                        println((i + 1) +") Requête: " + candidatures.get(i).getRequeteID() + "; Message laissé: " + candidatures.get(i).getMessage());
                    }
                    print("Entrez l'index de la candidature à confirmer > ");
                    Candidature candidatureAConfirmer;
                    try {
                        candidatureAConfirmer = candidatures.get(Integer.parseInt(reader.nextLine()) - 1);
                    } catch (Exception e) {
                        println("Vous n'avez pas entré un index valide, veuillez réessayer.");
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        continue;
                    }
                    println("1) Confirmer; 2) Annuler");
                    print("Votre choix > ");
                    switch (reader.nextLine()) {
                        case "1":
                            candidatureAConfirmer.setStatut(StatutCandidature.CONFIRMEE);
                            CandidatureController.updateCandidature(candidatureAConfirmer);
                            // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                            NotificationController.pushCandidatureNotifications("Candidature confirmée", "L'intervenant " + MaVille.getCurUser().getNomComplet() + " a confirmé sa candidature sur la requête ayant le ID " + candidatureAConfirmer.getRequeteID(), MaVille.getCurUser().getAdresseCourriel(), candidatureAConfirmer.getRequeteID(), LocalDateTime.now().toString());
                            continue;
                        default:
                            continue;

                    }
                default:
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
        ArrayList<Requete> requetes = RequeteController.getRequetes(true);

        for (int i = 0; i < requetes.size(); i++) {
            Requete r = requetes.get(i);
            println("Index :" + (i + 1));
            println("Titre: "+r.getTitre());
            println("Description: "+r.getDescription());
            println("Type de travail: " + r.getType().toString());
            println("Date de début espéré: " + r.getDateDebutEspere());
            println("Quartier: "+r.getQuartier());
            println("Déposé par: " + r.getUserID());
            for (int j = 0; j < 60;j++) {
                print("=");
            }
            println("");

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
                    print("Entrez l'index de la requête > ");
                    String idx = reader.nextLine();
                    String id = "";
                    try {
                        id = String.valueOf(requetes.get(Integer.parseInt(idx) - 1).getRequeteId());
                    } catch(Exception e) {
                        println("Choisissez un index valide.");
                        continue;
                    }
                    print("Entrez la date de début (YYYY-MM-DD) > ");
                    String dateDebut = reader.nextLine();
                    print("Entrez la date de fin (YYYY-MM-DD) > ");
                    String dateFin  = reader.nextLine();
                    println("1) Confirmer; 2) Annuler");
                    switch (reader.nextLine()) {
                        case "1":
                            int responseCode = CandidatureController.createCandidature(id, dateDebut, dateFin, MaVille.getCurUser().getAdresseCourriel(), "");
                            switch (responseCode) {
                                case 0:
                                    // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                                    NotificationController.pushCandidatureNotifications("Une candidature a été déposée sur une de vos requêtes", "L'intervenant " + MaVille.getCurUser().getNomComplet() + " a déposé sa candidature sur votre requête intitulée '"+requetes.get(Integer.parseInt(idx) - 1).getTitre()+"'.", MaVille.getCurUser().getAdresseCourriel(), String.valueOf(requetes.get(Integer.parseInt(idx) - 1).getRequeteId()), LocalDateTime.now().toString());
                                    println("Votre candidature a été soumise avec succès.");
                                    println("Appuyez sur n'importe quelle touche pour continuer.");
                                    reader.nextLine();
                                    continue;
                                case 1:
                                    println("Les dates entrées ne sont pas valides.");
                                    println("Appuyez sur n'importe quelle touche pour continuer.");
                                    reader.nextLine();
                                    continue;

                                case 2:
                                    println("Vous avez déjà envoyé une candidature pour cette requête.");
                                    println("Appuyez sur n'importe quelle touche pour continuer.");
                                    reader.nextLine();
                                    continue;
                                case 3:
                                    println("Erreur lors de la sauvegarde de la candidature.");
                                    println("Appuyez sur n'importe quelle touche pour continuer.");
                                    reader.nextLine();
                                    continue;
                            }

                            continue;
                        default:
                            continue;
                    }
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
            ArrayList<Travail> travaux = TravailController.getTravauxByIntervenant(MaVille.getCurUser().getAdresseCourriel());
            for (int i = 0; i < travaux.size() ;i++) {
                println("ID:" + (i + 1) + "; Titre:" + travaux.get(i).getTitre() + "; Description: " + travaux.get(i).getDescription() + "; Statut: " + travaux.get(i).getStatus() + "; Date de début: " + travaux.get(i).getDateDebut() + "; Date de fin: " + travaux.get(i).getDateFin());
            }
            println("");
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
            println("Vos chantiers: \n");
            ArrayList<Travail> travaux = TravailController.getTravauxByIntervenant(MaVille.getCurUser().getAdresseCourriel());
            for (int i = 0; i < travaux.size() ;i++) {
                println((i + 1) + ") Titre:" + travaux.get(i).getTitre() + "; Description: " + travaux.get(i).getDescription() + "; Statut: " + travaux.get(i).getStatus() + "; Date de début: " + travaux.get(i).getDateDebut() + "; Date de fin: " + travaux.get(i).getDateFin());
            }
            println("");
            print("Entrez l'index du travail à modifier > ");
            Travail travailAModifier;
            try {
                travailAModifier = travaux.get(Integer.parseInt(reader.nextLine()) - 1);
            } catch (Exception e) {
                println("Vous n'avez pas entré un index valide, veuillez réessayer");
                println("Appuyez sur 0 pour continuer ou 1 pour revenir");
                switch (reader.nextLine()) {
                    case "0":
                        continue;
                    default:
                        break;
                }
                break;
            }
            while (true) {
                println("1) Modifier la description du projet; 2) Modifier la date de fin prévue; 3) Changer le statut du projet; 4) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        println("Entrez la nouvelle description du projet: ");
                        String newDesc = reader.nextLine();
                        print("1) Confirmer; 2) Annuler > ");
                        if (reader.nextLine().equals("1")) {
                            travailAModifier.setDescription(newDesc.replace(",", ""));
                            TravailController.updateTravail(travailAModifier);
                            // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                            NotificationController.createWorkNotification("Changement de description", "La description du projet " + travailAModifier.getTitre() + " a changé.", MaVille.getCurUser().getAdresseCourriel(), travailAModifier.getId(), LocalDateTime.now().toString());
                            println("Le projet a été modifié avec succès, appuyez sur n'importe quelle touche pour continuer. ");
                            reader.nextLine();
                        } else {
                            println("Le changement a été annulé, appuyez sur n'importe quelle touche pour continuer.");
                            reader.nextLine();

                        }
                        continue;

                    case "2":
                        print("Entrez la nouvelle date de fin prévue (YYYY-MM-DD) > ");
                        String newDate = reader.nextLine();

                        print("1) Confirmer; 2) Annuler > ");
                        if (reader.nextLine().equals("1")) {
                            if (!TravailController.isEndDateValid(newDate)) {
                                println("Vous n'avez pas entré une date valide, veuillez réessayer");
                                println("Appuyez sur n'importe quelle touche pour continuer. ");
                                reader.nextLine();
                                continue;
                            }
                            String dateFin = travailAModifier.getDateFin();
                            travailAModifier.setDateFin(newDate);
                            TravailController.updateTravail(travailAModifier);
                            // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                            NotificationController.createWorkNotification("Changement de date de fin", "La date de fin du projet " + travailAModifier.getTitre() + " est passé de " + dateFin + " à " + newDate+".", MaVille.getCurUser().getAdresseCourriel(), travailAModifier.getId(), LocalDateTime.now().toString());
                            println("Le projet a été modifié avec succès, appuyez sur n'importe quelle touche pour continuer. ");
                            reader.nextLine();
                        } else {
                            println("Le changement a été annulé, appuyez sur n'importe quelle touche pour continuer.");
                            reader.nextLine();
                        }
                        continue;
                    case "3":
                        StatutProjet[] statutsPossibles = StatutProjet.values();
                        for (int i = 0; i < statutsPossibles.length; i++) {
                            println((i + 1) + ")" + statutsPossibles[i]);
                        }
                        print("Entrez l'index du statut du projet > ");
                        StatutProjet nouveauStatut;
                        try {
                            nouveauStatut = statutsPossibles[Integer.parseInt(reader.nextLine()) - 1];
                        } catch (Exception e) {
                            println("Vous n'avez pas entré un index valide, appuyez sur n'importe quelle touche pour continuer.");
                            reader.nextLine();
                            continue;
                        }
                        print("1) Confirmer; 2) Annuler > ");
                        if (reader.nextLine().equals("1")) {
                            StatutProjet ancienStatut = travailAModifier.getStatus();
                            travailAModifier.setStatus(nouveauStatut);
                            TravailController.updateTravail(travailAModifier);
                            // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                            NotificationController.createWorkNotification("Changement de statut", "Le projet " + travailAModifier.getTitre() + " est passé du statut " + ancienStatut.toString() + " au statut " + travailAModifier.getStatus().toString()+".", MaVille.getCurUser().getAdresseCourriel(), travailAModifier.getId(), LocalDateTime.now().toString());
                            println("Le projet a été modifié avec succès, appuyez sur n'importe quelle touche pour continuer. ");
                            reader.nextLine();
                        } else {
                            println("Le changement a été annulé, appuyez sur n'importe quelle touche pour continuer.");
                            reader.nextLine();
                        }
                        continue;
                    case "4":
                        return;
                    default:
                        println("Mauvaise entrée, veuillez réessayer");
                        continue;
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
            println("Quartiers affectés (séparés par des virgules) (Au moins 1): ");
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
                            // Pour les notifications, nous devons non seulement garder la date, mais également l'heure qu'elle a été créée. Ceci peut se faire avec LocalDateTime.
                            // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class LocalDateTime. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html.
                            NotificationController.createWorkNotification("Création du projet " + travail.getTitre(), "Le projet " + travail.getTitre() + " a été créé par l'intervenant " + MaVille.getCurUser().getNomComplet() + " et affectera votre quartier.", MaVille.getCurUser().getAdresseCourriel(), travail.getId(), LocalDateTime.now().toString());
                            println("Le nouveau projet a été soumis avec succès");
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
