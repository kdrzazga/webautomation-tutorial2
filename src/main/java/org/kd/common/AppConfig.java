package org.kd.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(List.of(User
                .withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .build()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }
}
