package org.kd.anotherspringtutorial.test.utils.email;

import org.kd.anotherspringtutorial.test.utils.TestType;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.util.List;

public interface EmailBuilder {

    Multipart createEmailBody() throws MessagingException;
    String getSender();
    String createEmailTitle(TestType testType);
    List<String> createRecipients();
    List<String> createCCRecipients();
}

