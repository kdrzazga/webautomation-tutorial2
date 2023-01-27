package org.kd.anotherspringtutorial.util.encrypt;

public interface PasswordEncryptor {

    String encrypt(String text);

    String decrypt(String encryptedText);
}
