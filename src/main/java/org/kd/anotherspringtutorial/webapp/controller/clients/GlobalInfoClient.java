package org.kd.anotherspringtutorial.webapp.controller.clients;

import feign.RequestLine;

public interface GlobalInfoClient {
    @RequestLine("GET /")
    String getInfo();
}
