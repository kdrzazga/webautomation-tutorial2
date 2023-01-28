package org.kd.anotherspringtutorial.email;

import org.kd.anotherspringtutorial.test.utils.TestType;
import org.kd.anotherspringtutorial.test.utils.report.Stats;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

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
    public String createRecipients() {
        return "tlv3koee@mailosaur.net";
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
