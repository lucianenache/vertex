package com.zanox.brandingservice; /**
 *
 * User: lucian.enache
 * Date: 12/14/13
 * Time: 2:54 PM
 * Reads the zanox.json file inside the properties folder and returns
 * it's content in path../brand otherwise it just shoots an 404.
 */


import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

import java.io.InputStream;

public class HttpVertex extends Verticle {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpVertex.class);
    public void start() {


        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {

                String brand = null;

                if (req.absoluteURI().getPath().equals("/res")) {
                    logger.debug("--->/res url has been called<---");

                    try {
                        logger.debug("--->opening /zanox.json resource stream<---");

                        InputStream input = HttpVertex.class.getResourceAsStream("/config.json");
                        brand = convertStreamToString(input);
                    } catch (Exception e) {
                        logger.error("Cannot open the input stream: " +e);
                        req.response().headers().add("Content-Type", "text/html; charset-UTF-8");
                        req.response().setStatusCode(503).setStatusMessage("Service unavailable").end("Error 503 - Service unavailable");

                    }
                    logger.debug("--->setting content type and returning the json response<---");
                   
                    req.response().headers().add("Content-Type", "application/json; charset-UTF-8");
                    req.response().setStatusCode(200).setStatusMessage("ok!").end(brand);
                } else {
                    logger.debug("--->wrong /url has been called<---");

                    req.response().headers().add("Content-Type", "text/html; charset-UTF-8");
                    req.response().setStatusCode(404).setStatusMessage("No service invoked").end("404 - No service invoked");
                }

            }
        }).listen(8080);

    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\Z");
        return s.next();
    }

}
