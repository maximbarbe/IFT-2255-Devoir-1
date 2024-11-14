package org.prototype.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.prototype.API.ApiCaller;
import org.prototype.Models.Entrave;


import java.util.ArrayList;



/**
 * Classe qui s'occupe des opérations sur les entraves
 */
public class EntraveController {

    /**
     * Parse le JSON retourné par l'appel à l'API
     * @param response - La réponse JSON de l'appel à l'API sous forme de String
     * @return - Une liste d'entraves créé à partir du JSON
     */
    private static ArrayList<Entrave> parseEntraveApiCall(String response) {

        // La procédure du parsing de JSON est basé sur:
        // Source: obataku. (2012, 9 août). See my comment. You need to include the full org.json library when running as android.jar only contains stubs to compile [Commentaire sur le post de forum en ligne Parsing JSON string in Java.]. StackOverflow. https://stackoverflow.com/a/11875002.
        // Ce commentaire a essentiellement été utilisé juste pour apprendre comment utiliser la librairie en pratique.
        ArrayList<Entrave> apiEntraves = new ArrayList<>();
        JSONObject res = new JSONObject(response);
        JSONArray travaux = res.getJSONObject("result").getJSONArray("records");
        for (int i = 0; i < travaux.length(); i++) {
            JSONObject cur = travaux.getJSONObject(i);
            apiEntraves.add(new Entrave(cur.getString("id_request"), cur.getString("streetid").substring(0, cur.getString("streetid").length() - 1),cur.getString("streetimpacttype")));
        }
        return apiEntraves;
    }

    /**
     * Fetch l'entièreté des entraves
     * @return - La liste d'entraves brute, non filtrée
     */
    public static ArrayList<Entrave> getEntraves() {
        String apiRes = ApiCaller.get("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd");
        if (apiRes == null) {
            return new ArrayList<>();
        }
        ArrayList<Entrave> entraves = parseEntraveApiCall(apiRes);
        return entraves;
    }

    /**
     * Fetch les entraves par travail
     * @param id - L'ID du travail
     * @return - La liste des entraves causées par ce travail
     */
    public static ArrayList<Entrave> getEntravesByID(String id) {
        ArrayList<Entrave> entraves= getEntraves();
        ArrayList<Entrave> filtered = new ArrayList<>();
        for (Entrave e:entraves) {
            if (e.getTravailId().equals(id)) {
                filtered.add(e);
            }
        }
        return filtered;
    }


    /**
     * Fetch les entraves par rue
     * @param street - La rue par laquelle on souhaite filtrer 
     * @return - La liste des entraves qui affectent cette rue
     */
    public static ArrayList<Entrave> getEntravesByStreet(String street) {
        ArrayList<Entrave> entraves= getEntraves();
        ArrayList<Entrave> filtered = new ArrayList<>();
        for (Entrave e:entraves) {
            if (e.getStreetId().toLowerCase().equals(street.toLowerCase())) {
                filtered.add(e);
            }
        }
        return filtered;
    }
}
