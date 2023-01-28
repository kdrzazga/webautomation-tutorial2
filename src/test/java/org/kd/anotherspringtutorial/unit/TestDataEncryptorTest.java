package org.kd.anotherspringtutorial.unit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.kd.anotherspringtutorial.test.utils.encrypt.TestDataEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class TestDataEncryptorTest {

    @Autowired
    private TestDataEncryptor jasyptEncryptor;
    private final Logger logger = Logger.getLogger(TestDataEncryptorTest.class.getSimpleName());

    @Test
    public void testEncryptAndDecrypt() {
        var password = "T@jn3 H@slo";

        String encrypted = jasyptEncryptor.encrypt(password);
        logger.log(Level.INFO, "Encrypted: " + encrypted);

        var decrypted = jasyptEncryptor.decrypt(encrypted);
        logger.log(Level.INFO, "Decrypted: " + decrypted);

        assertThat(password, Matchers.equalToObject(decrypted));
    }
}
