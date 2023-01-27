package com.nrk.yathra.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // Permit All Requests inside the Web Application
        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/User/newUserRegister").permitAll()
                .requestMatchers("/User/**").authenticated()
                .requestMatchers("/Post/**").authenticated().
                requestMatchers("/Admin/**").hasRole("ADMIN").
                and().formLogin()
                .and().httpBasic();

        // Deny All Requests inside the Web Application
//            http.authorizeHttpRequests().anyRequest().denyAll().
//                    and().formLogin()
//                    .and().httpBasic();

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
