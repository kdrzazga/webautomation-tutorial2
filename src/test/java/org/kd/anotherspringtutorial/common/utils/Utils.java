package org.kd.anotherspringtutorial.common.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Utils {

    public String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMMdd_kkmmssSSS");
        Date now = Calendar.getInstance().getTime();
        return sdf.format(now);
    }

    public static String readProperty(String key) {
        var logger = Logger.getLogger(Utils.class.getSimpleName());

        try {
            var prop = new Properties();
            var loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("application.properties");
            prop.load(stream);
            return prop.get(key).toString();
        } catch (IOException ioex) {
            logger.severe("Error reading properties file: " + ioex.getMessage());
            return "";
        }
    }

    public static void clearDirectory(String path) {
        var dir = new File(path);

        Optional<String[]> entries = Optional.ofNullable(dir.list());

        if (entries.isPresent()) {
            for (String s : entries.get()) {
                var currentFile = new File(dir.getPath(), s);
                currentFile.delete();
            }
        }
    }

    public static void clearScreenshots() {
        var logger = Logger.getLogger(Utils.class.getSimpleName());

        if (!"true".equals(readProperty("clear-screenshots-on-start")))
            return;

        String path = readProperty("screenshots.dir");
        logger.log(Level.INFO, "Deleting content of directory: " + path);
        clearDirectory(path);
    }
}
