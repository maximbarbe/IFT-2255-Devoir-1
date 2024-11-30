package org.prototype.Controllers;

import org.prototype.MaVille;
import org.prototype.Models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe qui s'occupe des opérations concernant l'objet <code>Notification</code> (i.e. la création, la modification ou sauvegarder dans un fichier)
 */
public class NotificationController {

    private static String workNotificationsFile = "src/worknotifications.csv";
    private static String candidatureNotificationsFile = "src/candidatureNotifications.csv";
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

    private static ArrayList<Notification> getCandidatureNotifications(ArrayList<Requete> requetes) {
        return new ArrayList<>();
    }

    public static ArrayList<Notification> getNotificationsForResident(Resident curUser) {
        ArrayList<Notification> notifications = getWorkNotifications(curUser.getQuartier());
        notifications.addAll(getCandidatureNotifications(RequeteController.getRequeteByUser(MaVille.getCurUser().getAdresseCourriel())));

        // Source: Java™ Platform, Standard Edition 8 API Specification. (s.d.). Class DateTimeFormatter. Oracle. https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html.

        notifications.removeIf(e -> LocalDateTime.parse(e.getDate()).isBefore(LocalDateTime.parse(curUser.getCreationDate())));

        if (notifications.size() == 0) {
            return notifications;
        }
        return sortNotifications(0, notifications.size() - 1, notifications);
    }

    public static ArrayList<Notification> getAllNotifications() {
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
    private static ArrayList<Notification> sortNotifications(int start, int end, ArrayList<Notification> notifications) {
        if (start == end) {
            ArrayList<Notification> notis = new ArrayList<>();
            notis.add(notifications.get(start));
            return notis;
        } else {
            ArrayList<Notification> merged = new ArrayList<>();
            ArrayList<Notification> leftSublist = sortNotifications(start, start + (start + end)/2, notifications);
            ArrayList<Notification> rightSublist = sortNotifications(start + (start + end)/2 + 1, end, notifications);
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
    public static ArrayList<Notification> getUnseenNotifications(Resident curUser) {
        HashSet<String> seenNotifications = curUser.getSeenNotifications();
        ArrayList<Notification> notifications = getNotificationsForResident(curUser);
        notifications.removeIf(n -> seenNotifications.contains(n.getNotificationID()));

        return notifications;

    }

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


}
