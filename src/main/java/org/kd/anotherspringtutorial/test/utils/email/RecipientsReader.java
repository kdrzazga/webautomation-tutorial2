package org.kd.anotherspringtutorial.test.utils.email;

import org.yaml.snakeyaml.Yaml;

import javax.mail.Message;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class RecipientsReader {
    private final Logger logger = Logger.getLogger(RecipientsReader.class.getSimpleName());
    private final String defaultRecipient = "tlv3koee@mailosaur.net";

    List<String> read(Message.RecipientType type) {
        var filePath = "src\\main\\resources\\test\\email\\";
        filePath += Message.RecipientType.TO.equals(type) ? "recipients.yml" : "recipientsCC.yml";

        var yaml = new Yaml();
        HashMap<String, List<String>> recipientsMap;

        try {
            var inputStream = new FileInputStream(filePath);
            recipientsMap = yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Could not read email recipients from file: ");
            return List.of(defaultRecipient);
        }
        return recipientsMap.get("recipients");
    }
}
