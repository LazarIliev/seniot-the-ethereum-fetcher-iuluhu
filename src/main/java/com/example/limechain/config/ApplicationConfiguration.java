package com.example.limechain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails alice = User.withUsername("alice")
                .password(passwordEncoder().encode("alice"))
                .roles("USER")
                .build();
        UserDetails bob = User.withUsername("bob")
                .password(passwordEncoder().encode("bob"))
                .roles("USER")
                .build();
        UserDetails carol = User.withUsername("carol")
                .password(passwordEncoder().encode("carol"))
                .roles("USER")
                .build();
        UserDetails dave = User.withUsername("dave")
                .password(passwordEncoder().encode("dave"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(alice, bob, carol, dave);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
