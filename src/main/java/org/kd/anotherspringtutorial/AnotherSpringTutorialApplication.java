package org.kd.anotherspringtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class AnotherSpringTutorialApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(AnotherSpringTutorialApplication.class, args);

        var logger = Logger.getLogger(AnotherSpringTutorialApplication.class.getSimpleName());

        logger.log(Level.INFO, "context: " + context);
        logger.log(Level.INFO, "SERVER STARTED");
        logger.log(Level.INFO, "http://localhost:" + getServerPort() + "/");
        logger.log(Level.INFO, "http://localhost:" + getServerPort() + "/read");
        logger.log(Level.INFO, "http://localhost:" + getServerPort() + "/read/4");
        logger.log(Level.INFO, "http://localhost:" + getServerPort() + "/read/b@d_r3que$t");
    }

    public static int getServerPort() {
        return ((AnnotationConfigServletWebServerApplicationContext) context).getWebServer().getPort();
    }

}
