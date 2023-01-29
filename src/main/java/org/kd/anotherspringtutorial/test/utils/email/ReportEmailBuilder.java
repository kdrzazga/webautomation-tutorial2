package org.kd.anotherspringtutorial.test.utils.email;

import org.kd.anotherspringtutorial.test.utils.TestType;
import org.kd.anotherspringtutorial.test.utils.report.Stats;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.List;

@Component
public class ReportEmailBuilder implements EmailBuilder {

    @Override
    public Multipart createEmailBody() throws MessagingException {
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(createMainMessage());
        return multipart;
    }

    private BodyPart createMainMessage() throws MessagingException {
        BodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(createExecutionReport(), "text/html");
        return messageBody;
    }

    @Override
    public String getSender() {
        return "Automation Test Pack";
    }

    @Override
    public String createEmailTitle(TestType testType) {
        return "Test Report [" + testType.name() + "]";
    }

    @Override
    public List<String> createRecipients() {
        return new RecipientsReader().read(Message.RecipientType.TO);
    }

    @Override
    public List<String> createCCRecipients() {
        return new RecipientsReader().read(Message.RecipientType.CC);
    }

    private String createExecutionReport() {
        String td = "<td style=\"border: 1px solid black;\">";

        return "<table>" +
                "<tr>" +
                td + "Passed&nbsp</td>" + td + Stats.getPassedTcCount() + "</td></tr>"
                + "<tr>" + td + "Failed&nbsp</td>" + td + Stats.getFailedTcCount() + "</td></tr>"
                + "</table>";
    }
}
