package org.prototype;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.apibuilder.ApiBuilder.*;
import org.prototype.Controllers.TravailController;



public class Routes {

    public static void setUp() {
        var app = Javalin.create().start(7070);
        app.get("/travaux", ctx -> {TravailController.getTravaux(ctx);});

    }

}
