package org.kd.anotherspringtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class AnotherSpringTutorialApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(AnotherSpringTutorialApplication.class, args);
        Logger logger = Logger.getLogger(AnotherSpringTutorialApplication.class.getSimpleName());
        logger.log(Level.INFO, "context: " + context);
        logger.log(Level.INFO, "SERVER STARTED");
        logger.log(Level.INFO, "http://localhost:8080/");
        logger.log(Level.INFO, "http://localhost:8080/read");
        logger.log(Level.INFO, "http://localhost:8080/read/4");
        logger.log(Level.INFO, "http://localhost:8080/read/b@d_r3que$t");
    }

}
