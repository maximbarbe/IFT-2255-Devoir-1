package org.prototype.Controllers;

import org.prototype.Models.Candidature;

import java.util.ArrayList;


/**
 * Classe qui s'occupe de contrôler la création, la modification et le "fetching" des candidatures
 */
public class CandidatureController {


    /**
     * Fetch les candidatures dans le fichier csv "candidatures.csv".
     * @return - La liste de toutes les candidatures
     */
    public static ArrayList<Candidature> getCandidatures(){
        return new ArrayList<>();
    }
}
