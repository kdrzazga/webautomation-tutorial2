package org.kd.anotherspringtutorial.test.utils.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.kd.anotherspringtutorial.test.utils.encrypt.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UsersData {

    private PasswordEncryptor passwordEncryptor;
    private Path usersPath;
    private Map<String, User> allUsers;
    private final Logger logger = Logger.getLogger(UsersData.class.getSimpleName());

    @Autowired
    public UsersData(@Value("${userdata.file}") String usersPath, PasswordEncryptor passwordEncryptor) {
        this.usersPath = Path.of(System.getProperty("user.dir") + "\\target\\classes\\" + usersPath);
        this.passwordEncryptor = passwordEncryptor;
        var userMapType = new TypeToken<Map<String, User>>() {
        }.getType();

        try {
            var fullJson = new String(Files.readAllBytes(this.usersPath));
            allUsers = new Gson().fromJson(fullJson, userMapType);

        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    public String getPassword(String userName) {
        var encryptedPassword = allUsers.get(userName).getPassword();
        return this.passwordEncryptor.decrypt(encryptedPassword);
    }
}
