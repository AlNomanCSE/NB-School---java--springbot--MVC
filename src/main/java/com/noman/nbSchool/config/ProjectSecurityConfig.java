package com.noman.nbSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/home","/about","/courses","/error","/assets/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/contact").authenticated()
                .requestMatchers(HttpMethod.GET,"/holidays/**").permitAll()
        );
        http.formLogin(withDefaults());
        return http.build();
    }
}
