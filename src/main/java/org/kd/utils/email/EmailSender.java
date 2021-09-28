package org.kd.utils.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class EmailSender {

    @Value("${smtp.host}")
    private String smptHost;

    @Value("${smtp.port}")
    private int smptPort;

    private EmailBuilder emailBuilder;
    private Logger logger = Logger.getLogger(EmailSender.class.getSimpleName());

    @Autowired
    public EmailSender(EmailBuilder emailBuilder) {
        this.emailBuilder = emailBuilder;
    }

    public void sendDummyEmail() {
        Session session = Session.getDefaultInstance(getEmailProps(), null);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailBuilder.getSender()));

            message.addRecipients(Message.RecipientType.TO, emailBuilder.createRecipients());
            message.setSubject(emailBuilder.createEmailTitle());
            message.setContent(emailBuilder.createEmailBody());

            Transport.send(message);

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
}
