package org.kd.anotherspringtutorial.webapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class SirThaddeusControllerHelper {
    public HttpHeaders createDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        headers.add("Date", Calendar.getInstance().getTime().toString());
        return headers;
    }
}
