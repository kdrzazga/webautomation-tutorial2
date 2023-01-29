package org.kd.anotherspringtutorial.test.utils.email;

import org.kd.anotherspringtutorial.test.utils.TestType;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {

    private String smptHost = "smtp.mailosaur.net";
    //Email & SMS testing website is used https://mailosaur.com/

    private int smptPort = 587;

    private Logger logger = Logger.getLogger(EmailSender.class.getSimpleName());
    private final EmailBuilder emailBuilder = new ReportEmailBuilder();

    public void sendReport(TestType testType) {
        var session = Session.getDefaultInstance(getEmailProps(), null);

        try {
            var message = new MimeMessage(session);
            message.setFrom(emailBuilder.getSender());
            message.setSender(new InternetAddress(emailBuilder.getSender()));

            emailBuilder.createRecipients().forEach(r -> {
                try {
                    message.addRecipients(Message.RecipientType.TO, r);
                } catch (MessagingException e) {
                    logger.log(Level.SEVERE, "Error sending email: " + e.getMessage());
                }
            });
/*
            emailBuilder.createCCRecipients().forEach(r -> {
                try {
                    message.addRecipients(Message.RecipientType.CC, r);
                } catch (MessagingException e) {
                    logger.log(Level.SEVERE, "Error sending email: " + e.getMessage());
                }
            });
*/
            message.setSubject(emailBuilder.createEmailTitle(testType));
            message.setContent(createEmailBody());

            Transport.send(message);

            logger.log(Level.INFO, "Email Report sent successfully");

        } catch (Exception mex) {
            logger.log(Level.SEVERE, "Error sending email: " + mex.getMessage());
        }
    }

    private Properties getEmailProps() {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smptHost);
        props.put("mail.smtp.port", smptPort);

        return props;
    }

    public Multipart createEmailBody() throws MessagingException {
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(createMainMessage());
        return multipart;
    }

    private BodyPart createMainMessage() throws MessagingException {
        BodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(emailBuilder.createEmailBody(), "text/html");
        return messageBody;
    }
}
