package org.prototype.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.prototype.API.ApiCaller;
import org.prototype.Models.Entrave;


import java.util.ArrayList;

public class EntraveController {

    private static ArrayList<Entrave> parseEntraveApiCall(String response) {
        ArrayList<Entrave> apiEntraves = new ArrayList<>();
        JSONObject res = new JSONObject(response);
        JSONArray travaux = res.getJSONObject("result").getJSONArray("records");
        for (int i = 0; i < travaux.length(); i++) {
            JSONObject cur = travaux.getJSONObject(i);
            apiEntraves.add(new Entrave(cur.getString("id_request"), cur.getString("streetid").substring(0, cur.getString("streetid").length() - 1),cur.getString("streetimpacttype")));
        }
        return apiEntraves;
    }
    public static ArrayList<Entrave> getEntraves() {
        String apiRes = ApiCaller.get("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd");
        ArrayList<Entrave> entraves = parseEntraveApiCall(apiRes);
        return entraves;
    }

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
