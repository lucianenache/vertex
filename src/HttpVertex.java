/**
 * Created with IntelliJ IDEA.
 * User: lucian
 * Date: 12/14/13
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.security.AccessController.getContext;

public class HttpVertex extends Verticle {

    public void start() {


        RouteMatcher routeMatcher = new RouteMatcher();

        routeMatcher.get("/branding", new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {

                System.out.println();
                System.out.println(req.headers().get("User-Agent"));
                System.out.println(req.headers().get("HTTP_CLIENT_IP"));


                req.response().headers().add("Content-Type", "application/json; charset-UTF-8");
                // try {
                //System.out.println(loadJSONFromAsset())  ;
                req.response().end("{\n" +
                        "    \"activityStream\": \"0\",\n" +
                        "    \"blog.url.en_US\": \"http://blog.zanox.com/en/zanox/feed/\",\n" +
                        "    \"brandLogo\": \"zanox_logo.jpg\",\n" +
                        "    \"characterLimit.profileDescription\": \"20000\",\n" +
                        "    \"characterLimit.termsAndConditions\": \"20000\",\n" +
                        "    \"dateLocalized\": \"0\",\n" +
                        "    \"defaultCurrency\": \"EUR\",\n" +
                        "    \"defaultLocale\": \"en_US\",\n" +
                        "    \"favicon\": \"favicon-zanox.ico\",\n" +
                        "    \"getSatisfaction.color\": \"#fe9900\",\n" +
                        "    \"getSatisfaction.company\": \"zanox\",\n" +
                        "    \"getSatisfaction.product\": \"zanox_zanox_marketplace\",\n" +
                        "    \"getSatisfaction.tag\": \"marketplace\",\n" +
                        "    \"googleAnalyticsTrackingId\": \"UA-24761743-1\",\n" +
                        "    \"hostName\": \"https://zanox-marketplace.gonzalog.dev.affiliatewindow.com\",\n" +
                        "    \"imagesHostName\": \"\",\n" +
                        "    \"locales\": [\"en_US\", \"de_DE\", \"nl_NL\", \"es_ES\", \"pt_BR\", \"sv_SE\", \"fr_FR\", \"it_IT\", \"pl_PL\",\"tr_TR\"],\n" +
                        "    \"localized\": \"1\",\n" +
                        "    \"loginURL\": \"/login\",\n" +
                        "    \"logo\": \"zanox_marketplace_logo.jpg\",\n" +
                        "    \"logoutURL\": \"http://www.zanox.com/us/\",\n" +
                        "    \"name\": \"zanox Marketplace\",\n" +
                        "    \"network\": \"zanox\",\n" +
                        "    \"session.cookie_domain\": \"\",\n" +
                        "    \"setHomePage\": \"0\",\n" +
                        "    \"showSupportLink\": \"0\",\n" +
                        "    \"staticHostName\": \"https://zanox-marketplace.gonzalog.dev.affiliatewindow.com\",\n" +
                        "    \"supportEmail\": \"service.de@zanox.com\",\n" +
                        "    \"twitter.url.en_US\": \"https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=zanox_affiliate\",\n" +
                        "    \"wikiUrl\": \"http://help.zanox.com/\"\n" +
                        "}");
                // } catch (IOException e) {
                //    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                // }

                //req.response().headers().add("Content-Type", "text/html; charset-UTF-8");
                //req.response().end("No service invoked");
            }
        });
        vertx.createHttpServer().requestHandler(routeMatcher).listen(8888);

        /*
        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {




                System.out.println(req.absoluteURI().getPath());
                System.out.println(req.headers().get("User-Agent"));
                System.out.println(req.headers().get("HTTP_CLIENT_IP"));


                if (req.absoluteURI().getPath().equals("/json")) {
                    req.response().headers().add("Content-Type", "application/json; charset-UTF-8");
                    req.response().end( / zanox.json);
                } else {
                    req.response().headers().add("Content-Type", "text/html; charset-UTF-8");
                    req.response().end("No service invoked");
                }


            }
        }).listen(8080);     */

    }

    /*
    public String loadJSONFromAsset() throws IOException {
        String json = null;
        final String[] result = new String[1];


       System.out.println(vertx.fileSystem().readDirSync("/").toString());


            vertx.fileSystem().readFile("zanox.json", new AsyncResultHandler<Buffer>() {
                public void handle(AsyncResult<Buffer> ar) {
                    if (ar.succeeded()) {
                        System.out.println("File contains: " + ar.result().length() + " bytes");
                        result[0] = ar.result().toString("UTF-8");
                        //return ar.result().toString();
                    } else {
                        System.out.println("Failed to read" + ar.cause());
                    }
                }
            });


        return result[0];
    }
    */

}