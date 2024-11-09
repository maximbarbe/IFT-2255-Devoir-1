package org.prototype;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    public static void setUp() {
        var app = Javalin.create().start(7070);


    }

}
