package org.prototype;

import io.javalin.Javalin;
import org.prototype.Controllers.EntraveController;
import org.prototype.Controllers.TravailController;



public class Routes {

    public static void setUp() {
        var app = Javalin.create().start(7070);
        app.get("/travaux", ctx -> {ctx.json(TravailController.getTravaux());});
        app.get("/travaux/type=<type>", ctx -> {ctx.json(TravailController.getTravauxByType(ctx.pathParam("type")));});
        app.get("/travaux/quartier=<quartier>", ctx -> {ctx.json(TravailController.getTravauxByQuartier(ctx.pathParam("quartier")));});
        app.get("/entraves", ctx -> {ctx.json(EntraveController.getEntraves());});
        app.get("/entraves/id=<id>", ctx -> {ctx.json(EntraveController.getEntravesByID(ctx.pathParam("id")));});
        app.get("/entraves/street=<street>", ctx -> {ctx.json(EntraveController.getEntravesByStreet(ctx.pathParam("street")));});
    }

}
