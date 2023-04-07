package org.kd.anotherspringtutorial.webapp.model;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class AuthorsRepo {

    final Map<String, List<String>> authorWorksMap = new HashMap<>();

    public AuthorsRepo() {
        authorWorksMap.put("Mickiewicz", List.of("Sir Thaddeus", "Dziady"));
        authorWorksMap.put("Homer", List.of("Illiada", "Odyssey"));
        authorWorksMap.put("Tolkien", List.of("Hobbit", "Lord of the Rings"));
    }

    public String findAuthor(String bookTitle) {
        AtomicReference<String> author = new AtomicReference<>("");
        for (String key : authorWorksMap.keySet()) {
            List<String> allBooks = authorWorksMap.get(key);
            if (allBooks.contains(bookTitle)) {
                author.set(key);
                break;
            }
        }

        return author.get();
    }

}
