package org.kd.anotherspringtutorial.webapp.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginData {

    @Bean
    public UserDetails admin() {
        return User.withUsername(Roles.ADMIN.getLogin())
                .password(Roles.ADMIN.getPassword())
                .roles(Roles.ADMIN.name())
                .build();
    }
}
