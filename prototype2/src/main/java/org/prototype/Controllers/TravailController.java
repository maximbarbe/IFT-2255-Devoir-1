package org.prototype.Controllers;

import io.javalin.http.Context;
import org.prototype.ApiCaller;
import org.prototype.Models.Travail;
import org.json.*;
import org.prototype.Models.TypeTravail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class TravailController {



    private static ArrayList<Travail> parseApiCall(String response) {
        ArrayList<Travail> apiTravaux = new ArrayList<>();
        JSONObject res = new JSONObject(response);
        JSONArray travaux = res.getJSONObject("result").getJSONArray("records");
        for (int i =0; i< travaux.length();i ++) {
            JSONObject travail = travaux.getJSONObject(i);
            ArrayList<String> quartiers = new ArrayList<>();
            quartiers.add(travail.getString("boroughid"));
            apiTravaux.add(new Travail(travail.getString("id"), travail.getString("reason_category"), travail.getString("occupancy_name"), quartiers, null, travail.getString("duration_start_date").split("T")[0], travail.getString("duration_end_date").split("T")[0], travail.get("organizationname").toString()));
        }
        return apiTravaux;
    }
    public static void getTravaux(Context ctx) {
        String apiRes = ApiCaller.get("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b");
        ArrayList<Travail> travaux = parseApiCall(apiRes);
        ctx.json(travaux);
    }


}
