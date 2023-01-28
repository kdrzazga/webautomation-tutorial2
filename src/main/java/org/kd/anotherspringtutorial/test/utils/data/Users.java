package org.kd.anotherspringtutorial.test.utils.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Users {

    private String usersPath;

    public Users(@Value("users.file") String usersPath) {
        this.usersPath = usersPath;
    }
}
