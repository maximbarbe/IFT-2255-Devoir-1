package org.prototype.Controllers;


import org.prototype.Models.Intervenant;
import org.prototype.Models.PlageHoraire;
import org.prototype.Models.TypeIntervenant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Classe qui s'occupe des opérations concernant l'objet <code>PlageHoraire</code> (i.e. la création, la modification ou sauvegarder dans un fichier)
 */
public class PlageHoraireController {
    private static String plagesFile ="src/plageHoraire.csv";

    public static String getPlagesFile() {
        return plagesFile;
    }

    public static void setPlagesFile(String file) {
        plagesFile = file;
    }

    public static PlageHoraire getPlageHoraire(String userID) {
        PlageHoraire plage = null;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(plagesFile));
            String line;
            ArrayList<ArrayList<Integer>> times = new ArrayList<ArrayList<Integer>>();
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(";");
                if (data[0].equals(userID)) {

                }

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

            reader.close();
            return new PlageHoraire(userID, times.get(0).get(0), times.get(0).get(1), times.get(1).get(0), times.get(1).get(1), times.get(2).get(0), times.get(2).get(1), times.get(3).get(0), times.get(3).get(1), times.get(4).get(0), times.get(4).get(1), times.get(5).get(0), times.get(5).get(1), times.get(6).get(0), times.get(6).get(1));
        } catch (Exception e) {
            return new PlageHoraire(userID, 0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        }
    }



    private static boolean isTimeFormatCorrect(String time) {
        Pattern timeFormat = Pattern.compile("^[0-9][0-9]:[0-9][0-9]$");
        return timeFormat.matcher(time).matches();
    }
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
        if (startHour >= 24 || startHour < 0 || startMinute < 0 || startMinute >= 60 || endHour >= 24 || endHour < 0 || endMinute >= 60 || endMinute < 0) {
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

    public static boolean savePlageHoraire(PlageHoraire plage) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getPlagesFile()));
            BufferedReader reader = new BufferedReader(new FileReader(getPlagesFile()));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.substring(0, plage.getUserId().length()).equals(plage.getUserId())) {
                    continue;
                } else {
                    lines.add(line);
                }
            }


            lines.add(plage.toString());
            writer.write(String.join(";", lines) + "\n");
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
