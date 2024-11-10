package org.prototype.Views;

import org.prototype.Controllers.EntraveController;
import org.prototype.Controllers.TravailController;
import org.prototype.Models.Entrave;
import org.prototype.Models.StatutProjet;
import org.prototype.Models.Travail;
import org.prototype.Models.TypeTravail;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResidentView extends View implements ConnectedView{

    @Override
    public void menuPrincipal() {
        while (true) {
            clearConsole();
            println("Menu principal:\n");
            println("1) Travaux");
            println("2) Profil");
            println("3) Notifications");
            println("4) Quitter");
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
            println("3) Soumettre une requête de travail");
            println("4) Plages horaires");
            println("5) Revenir");
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
                        soumettreRequeteTravail();
                        break;
                    case "4":
                        plagesHoraires();
                        break;
                    case "5":
                        return;
                    default:
                        println("Mauvais choix, veuillez réessayer");
                        continue;
                }
                break;
            }


        }
    }

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
                 case "2":
                     print("Entrez l'ID du travail > ");
                     String id = reader.nextLine();
                     clearConsole();
                     ArrayList<Entrave> entravesFiltrees = EntraveController.getEntravesByID(id);
                     for (Entrave e:entravesFiltrees) {
                        println("ID du travail correspondant: " + e.getTravailId()+"; Nom de la rue: "+e.getStreetId() + "; Effet sur la rue: "+e.getStreetImpact());
                     }

                     continue;
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

    public void consulterTravaux(){
        while (true) {
            clearConsole();
            println("Travaux en cours ou futurs: \n\n");
            ArrayList<Travail> travaux = TravailController.getTravaux();
            for (Travail t:travaux) {
                println("ID: "+t.getId() + "Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Status: "+t.getStatus());
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
                            switch (reader.nextLine()) {
                                case "1":
                                    clearConsole();
                                    print("Entrez le quartier désiré > ");
                                    ArrayList<Travail> travauxFiltres = TravailController.getTravauxByQuartier(reader.nextLine());

                                    for (Travail t:travauxFiltres) {
                                        println("ID: "+t.getId() + "Titre: "+t.getTitre() + "; Intervenant: "+t.getIdentifiantIntervenant() + "; Quartiers affectés: "+t.getQuartiers().toString().substring(1, t.getQuartiers().toString().length()-1) + "; Status: "+t.getStatus());
                                    }
                                    continue;
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

    public void soumettreRequeteTravail(){};

    public void plagesHoraires(){};
    public void pageNotifications(){};
    @Override
    public void afficherProfil(){};
}
