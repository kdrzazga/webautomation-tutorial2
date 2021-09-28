package org.kd.utils.email;

import org.kd.utils.HelperMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@Component
public class ProtonmailEmailBuilder implements EmailBuilder {

    @Autowired
    private HelperMethods helperMethods;

    @Override
    public Multipart createEmailBody() throws MessagingException {
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(createMainMessage());
        return multipart;
    }

    private BodyPart createMainMessage() throws MessagingException {
        BodyPart messageBody = new MimeBodyPart();
        messageBody.setContent("Ala ma kota", "text/html");
        return messageBody;
    }

    @Override
    public String getSender() {
        return "zmwn@protonmail.com";
    }

    @Override
    public String createEmailTitle() {
        return "Test Email " + helperMethods.getCurrentDate();
    }

    @Override
    public String createRecipients() {
        return "zmwn@protonmail.com";
    }
}
