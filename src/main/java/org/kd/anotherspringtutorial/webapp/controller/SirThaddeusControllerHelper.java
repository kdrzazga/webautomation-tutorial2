package org.kd.anotherspringtutorial.webapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class SirThaddeusControllerHelper {
    public HttpHeaders createDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        return headers;
    }
}
