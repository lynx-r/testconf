package com.mycompany.myapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.Collections;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User admin = new User("admin", "admin", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        User presenter = new User("tim", "user", Collections.singletonList(new SimpleGrantedAuthority("ROLE_PRESENTER")));
        return new InMemoryUserDetailsManager(Arrays.asList(
                admin, presenter
        ));
    }
}