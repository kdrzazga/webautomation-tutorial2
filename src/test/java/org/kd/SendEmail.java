package org.kd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kd.main.MainApp;
import org.kd.utils.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {MainApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SendEmail {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void testSendDummyEmail() {
        Assert.assertNotNull(emailSender);
        emailSender.sendDummyEmail();
    }
}
