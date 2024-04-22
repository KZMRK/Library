package com.kazmiruk.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/books/**", "/api/authors/**", "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/books", "/api/authors", "/api/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/books/*", "/api/authors/*", "/api/categories/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/books/*/borrow", "api/books/*/return").hasRole("READER")
                        .requestMatchers(HttpMethod.GET, "/api/readers/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
