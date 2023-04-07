package org.kd.anotherspringtutorial.webapp.controller;

import feign.Feign;
import org.kd.anotherspringtutorial.webapp.controller.clients.AuthorsClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
class SirThaddeusControllerHelper {
    public HttpHeaders createDefaultHeaders() {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        headers.add("Date", Calendar.getInstance().getTime().toString());
        return headers;
    }

    public String findAuthor() {
        AuthorsClient authorsClient = Feign.builder()
                .target(AuthorsClient.class, "http://localhost:8080/");

        return authorsClient.findAuthor("Sir Thaddeus");
    }
}
