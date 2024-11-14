package org.prototype.API;

import java.net.http.*;
import java.net.URI;

/**
 * Classe qui s'occupe de faire les appels à l'API de la ville de Montréal.
 * Basé sur le code présenté en démo, mais modifié pour l'alléger un peu et pour garder uniquement ce qu'on avait de besoin.
 */
public class ApiCaller {


    /**
     * Effectue une requête HTTP GET à une URL donnée et retourne la réponse JSON sous forme de String.
     * @param url - L'URL qu'on souhaite envoyer notre requête
     * @return - La réponse JSON sous forme de String
     */
    public static String get(String url) {

        try {
            // Construit la requête
            HttpRequest req = HttpRequest.newBuilder().uri(new URI(url)).header("Accept", "application/json").GET().build();
            // Envoie la requête et recoit la réponse
            HttpResponse<String> res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) {
                return res.body();
            } else {
                // Si notre statut n'est pas 200, alors il y a eu une erreur et on retourne rien.
                return null;
            }
        } catch (Exception e) {
            // Si on a une exception, alors on n'a qu'à rien retourner.
            return null;
        }

    }
}
