package org.prototype.Controllers;

import org.prototype.MaVille;
import org.prototype.Models.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Contrôleur responsable des opérations sur les objets {@link Notification}, 
 * telles que la création, la récupération et la sauvegarde des notifications.
 */
public class NotificationController {

    /**
     * Chemin du fichier contenant les notifications de travaux.
     */
    private static String workNotificationsFile = "src/worknotifications.csv";

    /**
     * Chemin du fichier contenant les notifications de candidatures.
     */
    private static String candidatureNotificationsFile = "src/candidatureNotifications.csv";


   /**
     * Récupère les notifications de travaux pour un quartier donné.
     *
     * @param quartier Le quartier pour lequel les notifications doivent être récupérées.
     * @return Une liste de {@link Notification} de travaux.
     */ 
    private static ArrayList<Notification> getWorkNotifications(String quartier) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(workNotificationsFile));
            String line;
            ArrayList<Notification> notifications = new ArrayList<>();
            ArrayList<Travail> travaux = TravailController.getTravauxFromFile();
            while ((line = reader.readLine())!=null) {

                String[] data = line.split(",");
                for (Travail t:travaux) {
                    if (t.getId().equals(data[3])) {
                        if (t.getQuartiers().contains(quartier)) {
                            notifications.add(new NotificationDeTravail(data[0], data[1], data[2], data[3], data[4], data[5]));
                        } else {
                            break;
                        }
                    }
                }

            }





            return notifications;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Récupère les notifications de candidatures pour un résident donné.
     *
     * @param curUser Le résident pour lequel les notifications doivent être récupérées.
     * @return Une liste de {@link Notification} de candidatures.
     */
    private static ArrayList<Notification> getCandidatureNotifications(Resident curUser) {
        ArrayList<Requete> requetes = RequeteController.getRequeteByUser(curUser.getAdresseCourriel());
        HashSet<String> requetesID = new HashSet<>();
        for (Requete r:requetes) {
            requetesID.add(String.valueOf(r.getRequeteId()));
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(candidatureNotificationsFile));
            String line;
            ArrayList<Notification> notifications = new ArrayList<>();
            while ((line= reader.readLine()) != null) {
                String[] data = line.split(",");
                notifications.add(new NotificationDeCandidature(data[0], data[1], data[2], data[3], data[4], data[5]));
            }
            notifications.removeIf(n -> !requetesID.contains(((NotificationDeCandidature)n).getRequeteID()));
            return notifications;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * Récupère toutes les notifications pertinentes pour un résident donné.
     *
     * @param curUser Le résident pour lequel les notifications doivent être récupérées.
     * @return Une liste de {@link Notification} filtrée et triée.
     */
    public static ArrayList<Notification> getNotificationsForResident(Resident curUser) {
        ArrayList<Notification> notifications = getWorkNotifications(curUser.getQuartier());
        notifications.addAll(getCandidatureNotifications((Resident)MaVille.getCurUser()));

        // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class DateTimeFormatter. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html.

        notifications.removeIf(e -> LocalDateTime.parse(e.getDate()).isBefore(LocalDateTime.parse(curUser.getCreationDate())));

        if (notifications.size() == 0) {
            return notifications;
        }
        return sortNotifications(0, notifications.size() - 1, notifications);
    }


    /**
     * Récupère toutes les notifications disponibles.
     *
     * @return Une liste de toutes les {@link Notification}.
     */
    private static ArrayList<Notification> getAllNotifications() {
        try {
            ArrayList<Notification> notifications = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(workNotificationsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                notifications.add(new NotificationDeTravail(data[0], data[1], data[2], data[3], data[4], data[5]));
            }
            reader = new BufferedReader(new FileReader(candidatureNotificationsFile));
            while ((line= reader.readLine()) != null) {
                String[] data = line.split(",");
                notifications.add(new NotificationDeCandidature(data[0], data[1], data[2], data[3], data[4], data[5]));
            }
            if (notifications.size() == 0) {
                return notifications;
            }
            return sortNotifications(0, notifications.size() - 1, notifications);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


     /**
     * Trie une liste de notifications en utilisant un merge sort.
     *
     * @param start        Index de début.
     * @param end          Index de fin.
     * @param notifications La liste de notifications à trier.
     * @return Une liste de {@link Notification} triée.
     */
    private static ArrayList<Notification> sortNotifications(int start, int end, ArrayList<Notification> notifications) {
        if (start == end) {
            ArrayList<Notification> notis = new ArrayList<>();
            notis.add(notifications.get(start));
            return notis;
        } else {
            ArrayList<Notification> merged = new ArrayList<>();
            ArrayList<Notification> leftSublist = sortNotifications(start, start + (end-start)/2, notifications);
            ArrayList<Notification> rightSublist = sortNotifications(start + (end-start)/2 + 1, end, notifications);
            int i = 0; int j = 0;
            while (i != leftSublist.size() && j != rightSublist.size()) {
                if (Integer.parseInt(leftSublist.get(i).getNotificationID()) <= Integer.parseInt(rightSublist.get(j).getNotificationID())) {
                    merged.add(leftSublist.get(i));
                    i++;
                } else {
                    merged.add(rightSublist.get(j));
                    j ++ ;
                }
            }
            while (i != leftSublist.size()) {
                merged.add(leftSublist.get(i));
                i ++;
            }
            while (j != rightSublist.size()) {
                merged.add(rightSublist.get(j));
                j++;
            }
            return merged;
        }
    }


/**
 * Récupère les notifications non vues pour un résident donné.
 *
 * @param curUser Le résident pour lequel les notifications non vues doivent être récupérées.
 * @return Une liste de {@link Notification} qui n'ont pas encore été vues par le résident.
 */  
    public static ArrayList<Notification> getUnseenNotifications(Resident curUser) {
        HashSet<String> seenNotifications = curUser.getSeenNotifications();
        ArrayList<Notification> notifications = getNotificationsForResident(curUser);
        notifications.removeIf(n -> seenNotifications.contains(n.getNotificationID()));

        return notifications;

    }


     /**
     * Crée une notification pour un travail.
     *
     * @param titre        Le titre de la notification.
     * @param description  La description de la notification.
     * @param intervenant  L'intervenant associé.
     * @param travailID    L'identifiant du travail.
     * @param date         La date de la notification.
     */
    public static void createWorkNotification(String titre, String description, String intervenant, String travailID, String date) {
        try {
            ArrayList<Notification> notis = getAllNotifications();
            int id = 0;
            if (notis.size() != 0) {
                id = Integer.parseInt(notis.get(notis.size() - 1).getNotificationID()) + 1;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(workNotificationsFile, true));
            writer.append(String.join(",", new String[]{titre, description,intervenant,travailID,date,String.valueOf(id)}) +"\n");
            writer.close();
            return;
        } catch (Exception e) {
            return;
        }
    }


/**
 * Crée et enregistre une notification de candidature dans le fichier des notifications de candidatures.
 *
 * @param titre        Le titre de la notification.
 * @param description  La description de la notification.
 * @param intervenant  Le nom de l'intervenant associé à la candidature.
 * @param requete      L'identifiant de la requête concernée par la candidature.
 * @param date         La date de la notification au format {@link String}.
 */
    public static void pushCandidatureNotifications(String titre, String description, String intervenant, String requete, String date) {
        try {
            ArrayList<Notification> notis = getAllNotifications();
            int id = 0;
            if (notis.size() != 0) {
                id = Integer.parseInt(notis.get(notis.size() - 1).getNotificationID()) + 1;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(candidatureNotificationsFile, true));
            writer.append(String.join(",", new String[]{titre, description,intervenant,requete,date,String.valueOf(id)}) + "\n");
            writer.close();
            return;
        } catch (Exception e) {return;}
    };

}
