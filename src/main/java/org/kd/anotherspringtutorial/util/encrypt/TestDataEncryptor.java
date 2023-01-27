package org.kd.anotherspringtutorial.util.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class TestDataEncryptor implements PasswordEncryptor {

    private final String encryptionKey = "ZV3Fx1reAVWKD8GBA7CqVMNADQSYqNb7EccxTzcC";
    private final StandardPBEStringEncryptor encryptor;

    public TestDataEncryptor() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionKey);
    }

    @Override
    public String encrypt(String text) {
        return encryptor.encrypt(text);
    }

    @Override
    public String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}
