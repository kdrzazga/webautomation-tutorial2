package org.kd.utils.email;

import javax.mail.MessagingException;
import javax.mail.Multipart;

public interface EmailBuilder {

    Multipart createEmailBody() throws MessagingException;
    String getSender();
    String createEmailTitle();
    String createRecipients();
}
