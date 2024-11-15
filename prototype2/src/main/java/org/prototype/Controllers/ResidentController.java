package org.prototype.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.prototype.Models.Resident;

/**
 * Classe qui s'occupe des opérations concernant les résidents
 */
public class ResidentController {


    private static String residentFile = "src/residents.csv";

    public static String getResidentFile() {
        return residentFile;
    }

    public static void setResidentFile(String file) {
        residentFile = file;
    }

    /**
     * Fetch la liste des résidents à partir d'un fichier prédéfini
     * @return - La liste des résidents
     */
    public static ArrayList<Resident> getResidents(){
        try {
            ArrayList<Resident> residents = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(residentFile));
            String line;
            while ((line = reader.readLine())!=null) {
                String[] data = line.split(",");
                residents.add(new Resident(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
            }
            reader.close();
            return residents;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
