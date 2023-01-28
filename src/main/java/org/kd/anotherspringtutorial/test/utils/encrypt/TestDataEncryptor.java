package org.kd.anotherspringtutorial.test.utils.encrypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestDataEncryptor implements PasswordEncryptor {

    private String encryptionKey;
    private final StandardPBEStringEncryptor encryptor;

    public TestDataEncryptor(@Value("${security.key}") String encryptionKey) {
        this.encryptionKey = encryptionKey;
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(encryptionKey);
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
