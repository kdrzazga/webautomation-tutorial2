package org.kd.anotherspringtutorial.webapp.controller;

import feign.Feign;
import org.kd.anotherspringtutorial.webapp.controller.clients.AuthorsClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
class SirThaddeusControllerHelper {

    private final Logger logger = Logger.getLogger(SirThaddeusControllerHelper.class.getSimpleName());

    public HttpHeaders createDefaultHeaders() {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        headers.add("Date", Calendar.getInstance().getTime().toString());
        return headers;
    }

    public String findAuthor() {
        AuthorsClient authorsClient = Feign.builder()
                .target(AuthorsClient.class, "http://localhost:8981/");

        String author = authorsClient.findAuthor("Sir_Thaddeus");
        logger.log(Level.INFO, "Read from AUTHOR service: " + author);
        return author;
    }
}
