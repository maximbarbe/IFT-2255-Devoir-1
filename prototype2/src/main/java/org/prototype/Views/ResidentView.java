package org.prototype.Views;

import org.prototype.Controllers.*;
import org.prototype.MaVille;
import org.prototype.Models.*;
import java.util.ArrayList;


/**
 * Classe qui s'occupe de la vue des résidents, contient tous les menus des résidents et permet d'accéder aux fonctionnalités des résidents.
 */
public class ResidentView extends View implements ConnectedView{

    @Override
    public void menuPrincipal() {
        while (true) {
            ArrayList<Notification> unseenNotifications = NotificationController.getUnseenNotifications((Resident) MaVille.getCurUser());
            clearConsole();
            println("Menu principal:\n");
            println("1) Travaux");
            println("2) Profil");
            println("3) Notifications (" + unseenNotifications.size() + ")");
            println("4) Requetes");
            println("5) Se déconnecter");
            print("Votre choix > ");
            String res = reader.nextLine();
            switch (res) {
                case "1":
                    pageTravail();
                    continue;
                case "2":
                    afficherProfil();
                    continue;
                case "3":
                    pageNotifications(unseenNotifications);
                    continue;
                case "4":
                    pageRequeteTravail();
                    continue;
                case "5":
                    return;
                default:
                    println("Mauvais choix, veuillez réessayer");
            }
        }
    }
    /**
     * Affiche le sous-menu du travail et permet aux résidents d'accéder à toutes les fonctionnalités reliées au travail.
     */
    public void pageTravail() {
        while (true) {
            clearConsole();
            println("1) Consulter les travaux en cours ou à venir");
            println("2) Consulter les entraves routières");
            println("3) Plages horaires");
            println("4) Revenir");
            print("Votre choix > ");
            while (true) {
                switch (reader.nextLine()) {
                    case "1":
                        consulterTravaux();
                        break;
                    case "2":
                        consulterEntraves();
                        break;
                    case "3":
                        plagesHoraires();
                        break;
                    case "4":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }


        }
    }

    /**
     * Permet aux utilisateurs de consulter les entraves causés par les travaux. Cet utilisateur peut choisir de les filtrer ou les avoir de manière brute.
     */
    public void consulterEntraves() {
        while (true) {
            clearConsole();
            println("Entraves causées par les travaux en cours: \n\n");
            ArrayList<Entrave> entraves = EntraveController.getEntraves();
            for (Entrave e:entraves) {
                println("ID du travail correspondant: " + e.getTravailId()+"; Nom de la rue: "+e.getStreetId() + "; Effet sur la rue: "+e.getStreetImpact());
            }
            while (true) {
                println("1) Rechercher par rue; 2) Rechercher par travail; 3) Revenir");
                print("Votre choix > ");


                switch (reader.nextLine()) {
                    // Filtre les entraves par travail via les rues.
                    case "1":
                        print("Entrez la rue désirée (ex: rue jean-brillant) > ");
                        String rue = reader.nextLine();
                        clearConsole();
                        ArrayList<Entrave> entravesFiltreesByStreet = EntraveController.getEntravesByStreet(rue);
                        for (Entrave e:entravesFiltreesByStreet) {
                            println("ID du travail correspondant: " + e.getTravailId()+"; Nom de la rue: "+e.getStreetId() + "; Effet sur la rue: "+e.getStreetImpact());
                        }
                        continue;
                        // Filtre les entraves par travail via les ID.
                    case "2":
                        print("Entrez l'ID du travail > ");
                        String id = reader.nextLine();
                        clearConsole();
                        ArrayList<Entrave> entravesFiltrees = EntraveController.getEntravesByID(id);
                        for (Entrave e:entravesFiltrees) {
                            println("ID du travail correspondant: " + e.getTravailId()+"; Nom de la rue: "+e.getStreetId() + "; Effet sur la rue: "+e.getStreetImpact());
                        }

                        continue;

                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        break;
                }
            }
        }
    }

    /**
     * Permet aux résidents de consulter les travaux, il peut les filtrer par quartier ou type de travail s'il le veut.
     */
    public void consulterTravaux(){
        while (true) {
            clearConsole();
            ArrayList<Travail> travaux = TravailController.getTravaux();
            ArrayList<Travail> travauxPresent = new ArrayList<>();
            ArrayList<Travail> travauxFuturs = new ArrayList<>();
            for (Travail t:travaux) {
                if (t.getStatus().equals(StatutProjet.EN_COURS)) {
                    travauxPresent.add(t);
                } else if (t.getStatus().equals(StatutProjet.PREVU)) {
                    travauxFuturs.add(t);
                }
            }
            println("Travaux en cours: \n\n");
            
            for (Travail t:travauxPresent) {
                println("ID: "+t.getId() + "; Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Date de début: "+t.getDateDebut() + "; Date de fin: " + t.getDateFin());
            }
            print("\n\n");
            println("Travaux dans les 3 prochains mois: \n\n");

            for (Travail t:travauxFuturs) {
                println("ID: "+t.getId() + "; Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Date de début: "+t.getDateDebut() + "; Date de fin: " + t.getDateFin());
            }

            while (true) {
                println("1) Rechercher; 2) Filtrer; 3) Revenir");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Entrez le titre du travail à rechercher: ");
                        String titre = reader.nextLine();
                        ArrayList<Travail> travauxParTitre = TravailController.getTravauxParTitre(titre);
                        if (travauxParTitre.size() == 0) {
                            println("Il n'y a aucun travaux avec ce titre");
                        } else {
                            for (Travail t: travauxParTitre) {
                                println("ID: "+t.getId() + "; Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Date de début: "+t.getDateDebut() + "; Date de fin: " + t.getDateFin());
                                println("");
                            }
                        }
                        println("Appuyer sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "2":
                        while (true) {
                            println("Filtrer par 1) Quartier; 2) Type de travail; 3) Annuler");
                            print("Votre choix > ");
                            // Filtrer les travaux par quartier
                            switch (reader.nextLine()) {
                                case "1":
                                    clearConsole();
                                    print("Entrez le quartier désiré > ");
                                    ArrayList<Travail> travauxFiltres = TravailController.getTravauxByQuartier(reader.nextLine());

                                    for (Travail t:travauxFiltres) {
                                        println("ID: "+t.getId() + "Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Status: "+t.getStatus());
                                    }
                                    continue;
                                // Filtrer les travaux par type.    
                                case "2":
                                    clearConsole();
                                    for (int i = 0; i < TypeTravail.values().length; i++) {
                                        println((i+1) + ") " + TypeTravail.values()[i].toString());
                                    }
                                    print("Entrez l'index du type de travail désiré > ");
                                    try {
                                        int index = Integer.parseInt(reader.nextLine());
                                        TypeTravail s =TypeTravail.values()[index - 1];
                                        ArrayList<Travail> travauxFiltresByType = TravailController.getTravauxByType(s.toString());
                                        for (Travail t:travauxFiltresByType) {
                                            println("ID: "+t.getId() + "Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Status: "+t.getStatus());
                                        }
                                        continue;
                                    } catch (Exception e) {
                                        println("Mauvais choix, veuillez réessayer");
                                        continue;
                                    }

                                default:
                                    break;

                            }
                            break;
                        }
                    break;

                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }

        }
    };

    /**
     * Affiche les options quant aux requêtes de travail, c'est à partir de cette page que l'utilisateur pourra accéder à la soumission de requête et au suivi de requêtes
     */
    public void pageRequeteTravail() {
        while (true) {
            clearConsole();
            while (true) {
                println("1) Soumettre une requête de travail");
                println("2) Faire le suivi de vos requêtes de travail");
                println("3) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        soumettreRequeteTravail();
                        break;
                    case "2":
                        suiviRequetes();
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        break;

                }
            }

        }

    }


    /**
     * Permet aux résidents de faire le suivi de leurs requêtes. Ils pourront ainsi voir les requêtes qu'ils ont envoyé, voir les candidatures qui ont été soumises, ainsi que fermer une requête.
     */
    public void suiviRequetes() {
        while (true) {
            clearConsole();
            println("Mes requêtes: ");
            for (Requete r:RequeteController.getRequeteByUser(MaVille.getCurUser().getAdresseCourriel())) {
                println("ID: "+r.getRequeteId());
                println("Titre: "+r.getTitre());
                println("Description: "+r.getDescription());
                println("Type de travail: " + r.getType().toString());
                println("Date de début espéré: " + r.getDateDebutEspere());
                println("Quartier: "+r.getQuartier());
                ArrayList<Candidature> candidatures = CandidatureController.getCandidaturesByRequete(String.valueOf(r.getRequeteId()));
                if (candidatures.size() == 0) {
                    println("Statut: Aucune candidature soumise");
                } else if (candidatures.size() == 1){
                    println("Statut: 1 candidature a été soumise");
                } else {
                    println("Statut: " + candidatures.size() + " candidatures ont été soumises.");
                }

                for (int i = 0; i < 60;i++) {
                    print("=");
                }
                println("");
            }

            while (true) {
                println("1) Voir candidatures");
                println("2) Fermer requête");
                println("3) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        println("Affichage de page pour voir les candidatures");
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "2":
                        clearConsole();
                        println("Mes requêtes\n");
                        ArrayList<Requete> mesRequetes = RequeteController.getRequeteByUser(MaVille.getCurUser().getAdresseCourriel());
                        for (int i = 0; i < mesRequetes.size(); i++) {
                            println((i+1) + ") Titre: " + mesRequetes.get(i).getTitre());
                        }
                        println("");
                        print("Entrez l'index de la requête que vous désirez fermer > ");
                        try {

                            Requete requeteAEnlever = mesRequetes.get(Integer.parseInt(reader.nextLine()) - 1);
                            println("1) Confirmer; 2) Annuler");
                            print("Votre choix > ");
                            switch (reader.nextLine()) {
                                case "1":
                                    ArrayList<Candidature> candidatures = CandidatureController.getCandidaturesByRequete(String.valueOf(requeteAEnlever.getRequeteId()));
                                    candidatures.forEach(c -> CandidatureController.removeCandidature(c));
                                    RequeteController.fermerRequete(requeteAEnlever);
                                    println("La candidature a été fermée avec succès.");
                                    println("Appuyez sur n'importe quelle touche pour continuer");
                                    reader.nextLine();
                                    continue;
                                default:
                                    continue;
                            }


                        } catch (Exception e) {
                            println("Vous n'avez pas entré un index valide. Veuillez réessayer");
                            println("Appuyez sur n'importe quelle touche pour continuer.");
                            reader.nextLine();
                            continue;
                        }

                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }
        }
    }

    /**
     * Permet aux utilisateurs de soumettre une requête de travail aux intervenants.
     */
    public void soumettreRequeteTravail(){
        while (true) {
            clearConsole();
            println("Soumettre une nouvelle requête de travail:\n\n");
            print("Titre du travail à réaliser: ");
            String titre = reader.nextLine();
            println("Description détaillée: ");
            String desc = reader.nextLine();
            print("Type de travail: ");
            String type = reader.nextLine();

            print("Date de début espéré (YYYY-MM-DD): ");
            String date = reader.nextLine();
            while (true) {
                println("1) Confirmer; 2) Modifier; 3) Revenir au menu");
                switch (reader.nextLine()) {
                    case "1":
                        Resident r = (Resident) MaVille.getCurUser();
                        if (RequeteController.creerRequete(titre, desc, type, date, r.getAdresseCourriel(), r.getQuartier())) {
                            println("Requête envoyée avec succès!");
                            println("Appuyez sur n'importe quelle touche pour continuer");
                            reader.nextLine();
                        } else {
                            println("Il y a eu un problème lors de l'envoi de la requête.");
                            println("Appuyez sur n'importe quelle touche pour continuer");
                            reader.nextLine();
                        };
                        return;
                    case "2":
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }
        }
    };

    /**
     * Affiche la plage horaire de l'utilisateur et permet à l'utilisateur de la modifier ou de voir les plages horaires des autres résidents du quartier.
     */
    public void plagesHoraires(){
        while (true) {
            clearConsole();
            println("Voici votre plage horaire:\n");
            PlageHoraire plage = PlageHoraireController.getPlageHoraire(MaVille.getCurUser().getAdresseCourriel());
            println(String.format("Lundi: " + ((plage.getLundi()[0] == plage.getLundi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getLundi()[0]/60, plage.getLundi()[0]%60, plage.getLundi()[1]/60, plage.getLundi()[1]%60));
            println(String.format("Mardi: " + ((plage.getMardi()[0] == plage.getMardi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getMardi()[0]/60, plage.getMardi()[0]%60, plage.getMardi()[1]/60, plage.getMardi()[1]%60));
            println(String.format("Mercredi: " + ((plage.getMercredi()[0] == plage.getMercredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getMercredi()[0]/60, plage.getMercredi()[0]%60, plage.getMercredi()[1]/60, plage.getMercredi()[1]%60));
            println(String.format("Jeudi: " + ((plage.getJeudi()[0] == plage.getJeudi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getJeudi()[0]/60, plage.getJeudi()[0]%60, plage.getJeudi()[1]/60, plage.getJeudi()[1]%60));
            println(String.format("Vendredi: " + ((plage.getVendredi()[0] == plage.getVendredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getVendredi()[0]/60, plage.getVendredi()[0]%60, plage.getVendredi()[1]/60, plage.getVendredi()[1]%60));
            println(String.format("Samedi: " + ((plage.getSamedi()[0] == plage.getSamedi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getSamedi()[0]/60, plage.getSamedi()[0]%60, plage.getSamedi()[1]/60, plage.getSamedi()[1]%60));
            println(String.format("Dimanche: " + ((plage.getDimanche()[0] == plage.getDimanche()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getDimanche()[0]/60, plage.getDimanche()[0]%60, plage.getDimanche()[1]/60, plage.getDimanche()[1]%60));
            println("\n");
            while (true) {
                println("1) Modifier la plage horaire; 2) Voir les plages horaires des autres résidents du quartier; 3) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        while (true) {
                            print("Entrez la journée que vous voulez changer (Lundi=1 à Dimanche=7) ou 8 pour revenir: ");
                            int code = 8;
                            try {
                                code = Integer.parseInt(reader.nextLine());
                            } catch (Exception e) {
                                println("Mauvaise entrée, veuillez réessayer");
                                continue;
                            }
                            switch (code) {
                                case 8:
                                    break;
                                default:
                                    print("Entrez l'heure de début (HH:MM): ");
                                    String startTime = reader.nextLine();
                                    print("Entrez l'heure de fin (HH:MM): ");
                                    String endTime = reader.nextLine();
                                    if (!PlageHoraireController.updatePlageHoraire(PlageHoraireController.getPlageHoraire(MaVille.getCurUser().getAdresseCourriel()), code, startTime, endTime)) {
                                        println("Erreur lors de la mise à jour de la plage horaire, assurez-vous de bien suivre le format indiqué et d'entrer une heure valide.");
                                    } else {
                                        println("La plage horaire a été modifée avec succès.");
                                    }
                                    continue;
                            }
                            break;
                        }
                        break;
                    case "2":
                        clearConsole();
                        println("Voici les pages horaires des autres résidents de votre quartier: \n");
                        Resident curResident = (Resident) MaVille.getCurUser();
                        for (Resident r: ResidentController.getResidentsByQuartier(curResident.getQuartier())) {
                            if (!r.getAdresseCourriel().equals(curResident.getAdresseCourriel())) {
                                println("Voici la page horaire de: " + r.getNomComplet());
                                plage = PlageHoraireController.getPlageHoraire(r.getAdresseCourriel());
                                println(String.format("Lundi: " + ((plage.getLundi()[0] == plage.getLundi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getLundi()[0]/60, plage.getLundi()[0]%60, plage.getLundi()[1]/60, plage.getLundi()[1]%60));
                                println(String.format("Mardi: " + ((plage.getMardi()[0] == plage.getMardi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getMardi()[0]/60, plage.getMardi()[0]%60, plage.getMardi()[1]/60, plage.getMardi()[1]%60));
                                println(String.format("Mercredi: " + ((plage.getMercredi()[0] == plage.getMercredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getMercredi()[0]/60, plage.getMercredi()[0]%60, plage.getMercredi()[1]/60, plage.getMercredi()[1]%60));
                                println(String.format("Jeudi: " + ((plage.getJeudi()[0] == plage.getJeudi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getJeudi()[0]/60, plage.getJeudi()[0]%60, plage.getJeudi()[1]/60, plage.getJeudi()[1]%60));
                                println(String.format("Vendredi: " + ((plage.getVendredi()[0] == plage.getVendredi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getVendredi()[0]/60, plage.getVendredi()[0]%60, plage.getVendredi()[1]/60, plage.getVendredi()[1]%60));
                                println(String.format("Samedi: " + ((plage.getSamedi()[0] == plage.getSamedi()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getSamedi()[0]/60, plage.getSamedi()[0]%60, plage.getSamedi()[1]/60, plage.getSamedi()[1]%60));
                                println(String.format("Dimanche: " + ((plage.getDimanche()[0] == plage.getDimanche()[1]) ? "Aucune": "%02d:%02d-%02d:%02d"), plage.getDimanche()[0]/60, plage.getDimanche()[0]%60, plage.getDimanche()[1]/60, plage.getDimanche()[1]%60));
                                println("============================================================================");

                            }

                        }
                        println("Appuyez sur n'importe quelle touche pour continuer.");
                        reader.nextLine();
                        break;
                    case "3":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }


        }
    };
    /**
     * Affiche les notifications qu'un résident a recu et permet d'accéder à la fonctionnalité de modifier les abonnements aux notifications.
     */
    public void pageNotifications(ArrayList<Notification> notifications){
        ResidentController.updateSeenNotifications(notifications);
        while (true) {
            clearConsole();

            println("Nouvelles notifications: ");
            for (int i = notifications.size() - 1; i >= 0; i--) {
                println("\t-Titre:" + notifications.get(i).getTitre());
                println("\t-Description: " + notifications.get(i).getDescription());
                println("\t-Date: "+notifications.get(i).getDate().split("T")[0]);
                println("");
            }
            while (true) {
                println("1) Voir toutes les notifications");
                println("2) Revenir");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        ArrayList<Notification> notis = NotificationController.getAllNotifications();
                        for (int i = notis.size() - 1; i >= 0; i--) {
                            println("\t-Titre:" + notis.get(i).getTitre());
                            println("\t-Description: " + notis.get(i).getDescription());
                            println("\t-Date: "+notis.get(i).getDate().split("T")[0]);
                            println("");
                        }
                        println("Appuyez sur n'importe quelle touche pour continuer");
                        reader.nextLine();
                        break;
                    case "2":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer.\n");
                }
                break;
            }


        }
    };

    @Override
    public void afficherProfil(){
        while (true) {
            Resident curUser = (Resident) MaVille.getCurUser();
            clearConsole();
            println("Profil: ");
            println("Compte: Résident");
            println("Adresse résidentielle: " + curUser.getAdresseResidentielle());
            println("Nom: "+curUser.getNomComplet());
            println("Email: "+curUser.getAdresseCourriel());
            println("Numéro de téléphone: " + curUser.getNumTelephone());
            println("Date de naissance: " + curUser.getDateDeNaissance());
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
