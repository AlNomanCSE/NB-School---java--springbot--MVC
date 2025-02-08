package com.noman.nbSchool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/home", "/about", "/courses", "/error", "/assets/**", "/login","/saveMsg").permitAll()
                .requestMatchers("contact").permitAll()
                .requestMatchers("dashboard","/displayMessages").authenticated()
                .requestMatchers( "holidays/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .anyRequest().denyAll()
        );
        http.csrf(csrfConfig->csrfConfig.ignoringRequestMatchers("/saveMsg","/public/**"));
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .permitAll());
        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
        );
        http.httpBasic(withDefaults());
        return http.build();
    }


}
