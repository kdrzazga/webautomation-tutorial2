package org.kd.anotherspringtutorial.test.utils.data;

import com.google.gson.Gson;
import org.kd.anotherspringtutorial.test.utils.encrypt.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Component
public class UsersData {

    private PasswordEncryptor passwordEncryptor;
    private String usersPath;
    private Map<String, User> users;
    private final Logger logger = Logger.getLogger(UsersData.class.getSimpleName());

    @Autowired
    public UsersData(@Value("users.file") String usersPath, PasswordEncryptor passwordEncryptor) {
        this.usersPath = "target\\classes\\test\\data\\users.json";//TODO
        this.passwordEncryptor = passwordEncryptor;
        //TODO:

        var gson = new Gson();
        BufferedReader bufferedReader = null;
/*        try {
            var fullPath = System.getProperty("user.dir") + "\\" + this.usersPath;
            Reader reader = Files.newBufferedReader(Path.of(fullPath));
            Type type = new TypeToken<List<User>>() {
            }.getType();

            List<User> users = gson.fromJson(reader, type);
            Map finalData = gson.fromJson(bufferedReader, Map.class);
            logger.log(Level.INFO, "Read data: " + finalData);

        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/
        users = new HashMap<>(1);
        users.put("admin", new User("admin", "OfnF+1o49/TaK7kP62Lq/w=="));
    }

    public String getPassword(String userName) {
        var encryptedPassword = users.get(userName).getPassword();
        return this.passwordEncryptor.decrypt(encryptedPassword);
    }
}
