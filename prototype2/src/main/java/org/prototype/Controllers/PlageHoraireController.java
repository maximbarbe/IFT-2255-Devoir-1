package org.prototype.Controllers;

import org.prototype.Models.PlageHoraire;
import org.prototype.Models.Resident;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * Classe responsable des opérations sur les objets {@link PlageHoraire}, 
 * telles que la création, la modification et la sauvegarde dans un fichier.
 */
public class PlageHoraireController {

    /**
     * Chemin du fichier contenant les plages horaires.
     */
    private static String plagesFile ="src/plageHoraire.csv";


     /**
     * Obtient le chemin du fichier des plages horaires.
     *
     * @return Le chemin du fichier des plages horaires.
     */
    public static String getPlagesFile() {
        return plagesFile;
    }


    /**
     * Définit le chemin du fichier des plages horaires.
     *
     * @param file Le nouveau chemin du fichier des plages horaires.
     */
    public static void setPlagesFile(String file) {
        plagesFile = file;
    }

    /**
     * Récupère toutes les plages horaires depuis le fichier CSV.
     *
     * @return Une liste de {@link PlageHoraire}.
     */
    private static ArrayList<PlageHoraire> getPlagesHoraires() {
        ArrayList<PlageHoraire> plages = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(plagesFile));
            String line;
            ArrayList<ArrayList<Integer>> times = new ArrayList<ArrayList<Integer>>();
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(",");
                String id = data[0];
                for (int i = 1; i < data.length; i++) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    if (data[i].equals("null")) {

                        temp.add(0);
                        temp.add(0);
                        times.add(temp);
                    } else {
                        String[] startAndEnd = data[i].split("-");
                        temp.add(Integer.parseInt(startAndEnd[0]));
                        temp.add(Integer.parseInt(startAndEnd[1]));
                        times.add(temp);
                    }
                }
            plages.add(new PlageHoraire(id, times.get(0).get(0), times.get(0).get(1), times.get(1).get(0), times.get(1).get(1), times.get(2).get(0), times.get(2).get(1), times.get(3).get(0), times.get(3).get(1), times.get(4).get(0), times.get(4).get(1), times.get(5).get(0), times.get(5).get(1), times.get(6).get(0), times.get(6).get(1)));
            }

            reader.close();
            return plages;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Récupère une plage horaire spécifique pour un utilisateur donné.
     *
     * @param userID L'identifiant de l'utilisateur.
     * @return La {@link PlageHoraire} associée à l'utilisateur.
     */
    public static PlageHoraire getPlageHoraire(String userID) {
        PlageHoraire plage = null;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(plagesFile));
            String line;
            ArrayList<ArrayList<Integer>> times = new ArrayList<ArrayList<Integer>>();
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(",");
                if (data[0].equals(userID)) {
                    for (int i = 1; i < data.length; i++) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        if (data[i].equals("null")) {

                            temp.add(0);
                            temp.add(0);
                            times.add(temp);
                        } else {
                            String[] startAndEnd = data[i].split("-");
                            temp.add(Integer.parseInt(startAndEnd[0]));
                            temp.add(Integer.parseInt(startAndEnd[1]));
                            times.add(temp);
                        }
                    }
                }



            }

            reader.close();
            return new PlageHoraire(userID, times.get(0).get(0), times.get(0).get(1), times.get(1).get(0), times.get(1).get(1), times.get(2).get(0), times.get(2).get(1), times.get(3).get(0), times.get(3).get(1), times.get(4).get(0), times.get(4).get(1), times.get(5).get(0), times.get(5).get(1), times.get(6).get(0), times.get(6).get(1));
        } catch (Exception e) {
            return new PlageHoraire(userID, 0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }
    }



    /**
 * Vérifie si une chaîne de caractères respecte le format d'heure HH:mm.
 *
 * @param time La chaîne de caractères représentant l'heure au format HH:mm.
 * @return {@code true} si le format est correct, sinon {@code false}.
 */
    private static boolean isTimeFormatCorrect(String time) {
        Pattern timeFormat = Pattern.compile("^[0-9][0-9]:[0-9][0-9]$");
        return timeFormat.matcher(time).matches();
    }


/**
 * Vérifie si les heures de début et de fin spécifiées sont valides.
 *
 * @param startHour   L'heure de début (entre 0 et 23).
 * @param startMinute Les minutes de début (entre 0 et 59).
 * @param endHour     L'heure de fin (entre 0 et 23).
 * @param endMinute   Les minutes de fin (entre 0 et 59).
 * @return {@code true} si les heures de début et de fin sont valides, sinon {@code false}.
 */
    private static boolean isHourValid(int startHour, int startMinute, int endHour, int endMinute) {
        if (startHour >= 24 || startHour < 0 || startMinute < 0 || startMinute >= 60 || endHour >= 24 || endHour < 0 || endMinute >= 60 || endMinute < 0 || endHour < startHour || (endHour == startHour && endMinute < startMinute) || (endHour == startHour && endMinute == startMinute)) {
            return false;
        }
        return true;
    }


    /**
     * Met à jour une plage horaire pour un jour spécifique.
     *
     * @param plage La {@link PlageHoraire} à mettre à jour.
     * @param day   Le jour de la semaine (1 pour lundi, 7 pour dimanche).
     * @param start L'heure de début au format HH:mm.
     * @param end   L'heure de fin au format HH:mm.
     * @return {@code true} si la mise à jour réussit, sinon {@code false}.
     */
    public static boolean updatePlageHoraire(PlageHoraire plage, int day, String start, String end) {
        if (!isTimeFormatCorrect(start) || !isTimeFormatCorrect(end)) {
            return false;
        }

        String[] startTimes = start.split(":");
        String[] endTimes = end.split(":");

        int startHour = Integer.parseInt(startTimes[0]);
        int startMinute = Integer.parseInt(startTimes[1]);

        int endHour = Integer.parseInt(endTimes[0]);
        int endMinute = Integer.parseInt(endTimes[1]);
        if (!isHourValid(startHour, startMinute, endHour, endMinute)) {
            return false;
        }
        int startTime = startHour * 60 + startMinute;
        int endTime = endHour * 60 + endMinute;

        switch (day) {
            case 1:
                plage.setLundi(new int[]{startTime, endTime});
                break;
            case 2:
                plage.setMardi(new int[]{startTime, endTime});
                break;
            case 3:
                plage.setMercredi(new int[]{startTime, endTime});
                break;
            case 4:
                plage.setJeudi(new int[]{startTime, endTime});
                break;
            case 5:
                plage.setVendredi(new int[]{startTime, endTime});
                break;
            case 6:
                plage.setSamedi(new int[]{startTime, endTime});
                break;
            case 7:
                plage.setDimanche(new int[]{startTime, endTime});
                break;
        }

        savePlageHoraire(plage);
        return true;
    }


    /**
     * Sauvegarde une plage horaire dans le fichier CSV.
     *
     * @param plage La {@link PlageHoraire} à sauvegarder.
     * @return {@code true} si la sauvegarde réussit, sinon {@code false}.
     */
    public static boolean savePlageHoraire(PlageHoraire plage) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(getPlagesFile()));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(plage.getId())) {
                    continue;
                } else {
                    lines.add(line);
                }
            }


            lines.add(plage.toString());
            BufferedWriter writer = new BufferedWriter(new FileWriter(getPlagesFile()));
            writer.write(String.join("\n", lines) + "\n");
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


 /**
 * Crée une nouvelle plage horaire à partir des plages horaires spécifiées pour chaque jour de la semaine.
 *
 * @param lundi     La plage horaire du lundi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param mardi     La plage horaire du mardi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param mercredi  La plage horaire du mercredi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param jeudi     La plage horaire du jeudi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param vendredi  La plage horaire du vendredi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param samedi    La plage horaire du samedi au format "HH:mm-HH:mm" ou une chaîne vide.
 * @param dimanche  La plage horaire du dimanche au format "HH:mm-HH:mm" ou une chaîne vide.
 * @return Une {@link PlageHoraire} créée avec les plages horaires spécifiées, ou {@code null} si une des plages horaires est invalide.
 */
    public static PlageHoraire creerPlageHoraire(String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, String dimanche) {
        String[] times = {lundi, mardi, mercredi, jeudi, vendredi, samedi, dimanche};
        ArrayList<ArrayList<Integer>> parsedTimes = new ArrayList<>();
        Pattern p = Pattern.compile("^[0-9][0-9]:[0-9][0-9]-[0-9][0-9]:[0-9][0-9]$");
        for (String time:times) {
            ArrayList<Integer> timesAsMinutes = new ArrayList<>();
            if (!p.matcher(time).matches() && !time.equals("")) {
                return null;
            } else {
                if (time.equals("")) {
                    timesAsMinutes.add(0);
                    timesAsMinutes.add(0);
                } else {
                    String[] separatedTimes = time.split("-");
                    String[] startHourAndMinute = separatedTimes[0].split(":");
                    String[] endHourAndMinute = separatedTimes[1].split(":");
                    int startHour = Integer.parseInt(startHourAndMinute[0]);
                    int startMinute = Integer.parseInt(startHourAndMinute[1]);

                    int endHour = Integer.parseInt(endHourAndMinute[0]);
                    int endMinute = Integer.parseInt(endHourAndMinute[1]);
                    if (!isHourValid(startHour, startMinute, endHour, endMinute)) {
                        return null;
                    }
                    int startTime = startHour * 60 + startMinute;
                    int endTime = endHour * 60 + endMinute;
                    timesAsMinutes.add(startTime);
                    timesAsMinutes.add(endTime);
                }
                parsedTimes.add(timesAsMinutes);

            }
        }
        return new PlageHoraire("temp", parsedTimes.get(0).get(0), parsedTimes.get(0).get(1), parsedTimes.get(1).get(0), parsedTimes.get(1).get(1), parsedTimes.get(2).get(0), parsedTimes.get(2).get(1), parsedTimes.get(3).get(0), parsedTimes.get(3).get(1), parsedTimes.get(4).get(0), parsedTimes.get(4).get(1), parsedTimes.get(5).get(0), parsedTimes.get(5).get(1), parsedTimes.get(6).get(0), parsedTimes.get(6).get(1));
    }


/**
 * Récupère les plages horaires des résidents vivant dans les quartiers spécifiés.
 *
 * @param quartiers Un tableau de noms de quartiers.
 * @return Une liste de {@link PlageHoraire} associées aux résidents des quartiers donnés.
 */ 
    public static ArrayList<PlageHoraire> getPlageHoraireByQuartiers(String[] quartiers) {
        ArrayList<Resident> residents = new ArrayList<>();
        for (String q:quartiers) {
            residents.addAll(ResidentController.getResidentsByQuartier(q.toLowerCase()));
        }
        HashSet<String> residentIds = new HashSet<>();
        for (Resident r:residents) {
            residentIds.add(r.getAdresseCourriel());
        }
        ArrayList<PlageHoraire> plagesHoraires = getPlagesHoraires();
        plagesHoraires.removeIf(p -> !residentIds.contains(p.getId()));

        return plagesHoraires;
    }


 /**
 * Compte le nombre de conflits entre une plage horaire de travail et une liste de plages horaires de résidents.
 *
 * @param horaireTravail  La {@link PlageHoraire} représentant l'horaire de travail.
 * @param plagesResidents Une liste de {@link PlageHoraire} représentant les horaires des résidents.
 * @return Le nombre de jours où il y a des conflits d'horaires.
 */ 
    public static int nombreConflits(PlageHoraire horaireTravail, ArrayList<PlageHoraire> plagesResidents) {
        int count = 0;
        for (PlageHoraire p:plagesResidents) {
            if (p.getLundi()[0] != p.getLundi()[1] && horaireTravail.getLundi()[0] != horaireTravail.getLundi()[1] && (horaireTravail.getLundi()[0] < p.getLundi()[0] || horaireTravail.getLundi()[1] > p.getLundi()[1])) {
                count += 1;
                continue;
            }
            if (p.getMardi()[0] != p.getMardi()[1] && horaireTravail.getMardi()[0] != horaireTravail.getMardi()[1]&& (horaireTravail.getMardi()[0] < p.getMardi()[0] || horaireTravail.getMardi()[1] > p.getMardi()[1])) {
                count += 1;
                continue;
            }
            if (p.getMercredi()[0] != p.getMercredi()[1] && horaireTravail.getMercredi()[0] != horaireTravail.getMercredi()[1] &&(horaireTravail.getMercredi()[0] < p.getMercredi()[0] || horaireTravail.getMercredi()[1] > p.getMercredi()[1])) {
                count += 1;
                continue;
            }
            if (p.getJeudi()[0] != p.getJeudi()[1] && horaireTravail.getJeudi()[0] != horaireTravail.getJeudi()[1] &&(horaireTravail.getJeudi()[0] < p.getJeudi()[0] || horaireTravail.getJeudi()[1] > p.getJeudi()[1])) {
                count += 1;
                continue;
            }
            if (p.getVendredi()[0] != p.getVendredi()[1] && horaireTravail.getVendredi()[0] != horaireTravail.getVendredi()[1]&& (horaireTravail.getVendredi()[0] < p.getVendredi()[0] || horaireTravail.getVendredi()[1] > p.getVendredi()[1])) {
                count += 1;
                continue;
            }
            if (p.getSamedi()[0] != p.getSamedi()[1] && horaireTravail.getSamedi()[0] != horaireTravail.getSamedi()[1]&& (horaireTravail.getSamedi()[0] < p.getSamedi()[0] || horaireTravail.getSamedi()[1] > p.getSamedi()[1])) {
                count += 1;
                continue;
            }
            if (p.getDimanche()[0] != p.getDimanche()[1]&& horaireTravail.getDimanche()[0] != horaireTravail.getDimanche()[1] && (horaireTravail.getDimanche()[0] < p.getDimanche()[0] || horaireTravail.getDimanche()[1] > p.getDimanche()[1])) {
                count += 1;
                continue;
            }
        }
        return count;
    }
}
