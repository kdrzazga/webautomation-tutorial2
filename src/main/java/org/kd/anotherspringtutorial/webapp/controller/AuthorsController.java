package org.kd.anotherspringtutorial.webapp.controller;


import org.kd.anotherspringtutorial.webapp.model.AuthorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorsController {

    private final AuthorsRepo authorsRepo;

    @Autowired
    public AuthorsController(AuthorsRepo authorsRepo) {
        this.authorsRepo = authorsRepo;
    }

    @GetMapping(path = "/author/{bookTitle}")
    public ResponseEntity<String> findAuthor(@PathVariable String bookTitle) {
        String author = this.authorsRepo.findAuthor(bookTitle);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body(author);
    }
}
