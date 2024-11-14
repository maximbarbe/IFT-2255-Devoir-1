package org.prototype.Views;

import org.prototype.Controllers.EntraveController;
import org.prototype.Controllers.RequeteController;
import org.prototype.Controllers.TravailController;
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
            clearConsole();
            println("Menu principal:\n");
            println("1) Travaux");
            println("2) Profil");
            println("3) Notifications");
            println("4) Requetes");
            println("5) Quitter");
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
                    pageNotifications();
                    continue;
                case "4":
                    pageRequeteTravail();
                    continue;
                case "5":
                    System.exit(0);
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

                // Filtre les entraves par travail via les ID.
                switch (reader.nextLine()) {
                    case "2":
                        print("Entrez l'ID du travail > ");
                        String id = reader.nextLine();
                        clearConsole();
                        ArrayList<Entrave> entravesFiltrees = EntraveController.getEntravesByID(id);
                        for (Entrave e:entravesFiltrees) {
                            println("ID du travail correspondant: " + e.getTravailId()+"; Nom de la rue: "+e.getStreetId() + "; Effet sur la rue: "+e.getStreetImpact());
                        }

                        continue;
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
            println("Travaux en cours ou futurs: \n\n");
            ArrayList<Travail> travaux = TravailController.getTravaux();
            for (Travail t:travaux) {
                println("ID: "+t.getId() + "; Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Status: "+t.getStatus());
            }

            while (true) {
                println("1) Rechercher; 2) Filtrer; 3) Revenir");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage de la barre de recherche...");
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
            for (Requete r:RequeteController.getRequetes()) {
                if (r.getUserID().equals(MaVille.getCurUser().getAdresseCourriel())) {
                    println("ID: "+r.getRequeteId());
                    println("Titre: "+r.getTitre());
                    println("Description: "+r.getDescription());
                    println("Type de travail: " + r.getType().toString());
                    println("Date de début espéré: " + r.getDateDebutEspere());
                    println("Quartier: "+r.getQuartier());
                    println("Statut: Aucune candidature soumise");
                    for (int i = 0; i < 60;i++) {
                        print("=");
                    }
                    println("");
                }
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
                        println("Affichage de page pour fermer candidature");
                        println("Appuyez sur n'importe quelle touche pour continuer");
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
            String type = "RESIDENTIELS";

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
            println("Lundi: 8:00-12:00");
            println("Mardi: 15:00-17:00");
            println("Mercredi: 12:00-15:00");
            println("Jeudi: Aucune");
            println("Vendredi: 16:00-18:00");
            println("Samedi: Aucune");
            println("Dimanche: Aucune");
            println("\n");
            while (true) {
                println("1) Modifier la plage horaire; 2) Voir les plages horaires des autres résidents du quartier; 3) Revenir");
                print("Votre choix > ");
                switch (reader.nextLine()) {
                    case "1":
                        println("Affichage de la page de modification des plages horaires...");
                        println("Appuyez sur n'importe quelle touche");
                        reader.nextLine();
                        break;
                    case "2":
                        println("Affichage de la page pour voir les plages horaires...");
                        println("Appuyez sur n'importe quelle touche");
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
    public void pageNotifications(){
        while (true) {
            clearConsole();
            println("Notifications: ");
            println("\t-Projet `Construction sur le pont Jacques-Cartier` a commencé dans votre quartier (ID:0000).");
            println("\t-Modification des détails du projet `Construction au pavillon Roger-Gaudry` (ID:0001).");
            println("\t-Projet `Rénovation au pavillon André-Aisenstadt` est terminé (ID:0002).");
            println("\t-Retour sur votre requête de travail `Besoin de rénovation au HEC.`");
            println("\n");
            while (true) {
                println("1) Modifier les abonnements aux notifications.");
                println("2) Revenir");
                print("Votre choix > ");
                String res = reader.nextLine();
                switch (res) {
                    case "1":
                        println("Affichage page pour modifier les notifications...");
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
