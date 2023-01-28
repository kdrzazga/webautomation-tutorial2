package org.kd.anotherspringtutorial.test.utils.encrypt;

public interface PasswordEncryptor {

    String encrypt(String text);

    String decrypt(String encryptedText);
}
