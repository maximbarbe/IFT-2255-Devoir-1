package org.example.Controllers;
import org.example.Models.Resident;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
public class ResidentController {

    public static int createResident(String nom, String birthday, String email, String password, String telephone, String adresse) {
        if (!isEmailAddressUnique(email)) {
            return 1;
        } else if (!formatDateValide(birthday)) {
            return 2;
        } else if (!ageRequis(birthday)) {
            return 3;
        }
        Resident.addResident(new Resident(nom, email, password, birthday, telephone, adresse));
        return 0;
    };

    private static boolean isEmailAddressUnique(String email) {
        for (Resident r:Resident.getResidents()) {
            if (r.getAdresseCourriel().equals(email)) {
                return false;
            }
        }
        return true;
    }
    private static boolean formatDateValide(String birthday) {
        Pattern formatDemande = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        Matcher match = formatDemande.matcher(birthday);
        if (!match.find()){return false;}
        return true;
    };

    private static boolean ageRequis(String birthday) {
        LocalDate dateDeNaissance;
        try {
            dateDeNaissance = LocalDate.parse(birthday);
        } catch (Exception e) {
            return false;
        }

        LocalDate dateCourante = LocalDate.now();
        if (dateCourante.getYear() > dateDeNaissance.getYear() + 16) {
            return true;
        } else if(dateCourante.getYear() == dateDeNaissance.getYear() + 16) {
            if (dateCourante.getMonth().getValue() > dateDeNaissance.getMonth().getValue()) {
                return true;
            } else if (dateCourante.getMonth().getValue() == dateDeNaissance.getMonth().getValue()) {
                if (dateCourante.getDayOfMonth() >= dateDeNaissance.getDayOfMonth()) {
                    return true;
                }
            }
        }
        return false;
    }


}
