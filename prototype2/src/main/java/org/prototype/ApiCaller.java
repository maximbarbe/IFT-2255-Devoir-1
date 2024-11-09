package org.prototype;

import java.net.http.*;
import java.net.URI;
public class ApiCaller {


    public static String get(String url) {

        try {
            HttpRequest req = HttpRequest.newBuilder().uri(new URI(url)).header("Accept", "application/json").GET().build();
            HttpResponse<String> res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) {
                return res.body();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }
}
