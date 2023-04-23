package org.kd.anotherspringtutorial.webapp.controller.clients;

import feign.Param;
import feign.RequestLine;

public interface AuthorsClient {
    @RequestLine("GET /author/{bookTitle}")
    String findAuthor(@Param("bookTitle") String bookTitle);
}
