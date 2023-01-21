package org.kd.anotherspringtutorial.webapp.controller;

import org.kd.anotherspringtutorial.webapp.model.SirThaddeusText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SirThaddeusController {

    private final SirThaddeusText sirThaddeusText;
    private final SirThaddeusControllerHelper helper;

    @Autowired
    public SirThaddeusController(SirThaddeusText sirThaddeusText, SirThaddeusControllerHelper helper) {
        this.sirThaddeusText = sirThaddeusText;
        this.helper = helper;
    }

    private final Logger logger = Logger.getLogger(SirThaddeusController.class.getSimpleName());

    @GetMapping(path = "/read")
    public String readAll() {
        logger.log(Level.INFO, "Full text requested");
        return sirThaddeusText.readAll().replaceAll("\n", "<BR/>");
    }

    @GetMapping(path = "/read/{index}")
    public ResponseEntity<String> read(@PathVariable String index) {
        String body;
        HttpStatus status;
        HttpHeaders headers = helper.createDefaultHeaders();

        try {
            int i = Integer.parseInt(index);
            body = sirThaddeusText.read(i);
            status = HttpStatus.OK;

            logger.log(Level.INFO, "Read line [" + index + "]: " + body);
        } catch (NumberFormatException nfe) {
            logger.log(Level.INFO, "Wrong line requested - number: " + index);
            body = "";
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status)
                .headers(headers)
                .body(body);
    }

}
