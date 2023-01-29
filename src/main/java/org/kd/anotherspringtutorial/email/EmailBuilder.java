package org.kd.anotherspringtutorial.email;

import org.kd.anotherspringtutorial.test.utils.TestType;

import javax.mail.MessagingException;
import javax.mail.Multipart;

public interface EmailBuilder {

    Multipart createEmailBody() throws MessagingException;
    String getSender();
    String createEmailTitle(TestType testType);
    String createRecipients();
}

