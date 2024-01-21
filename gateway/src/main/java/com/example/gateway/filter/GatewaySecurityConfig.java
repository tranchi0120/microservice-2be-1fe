package com.example.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.GET, "/api/users","/api/posts","/api/comments").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users/{id}","/api/posts/{id}","/api/comments/{id}").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/users/addUser","/api/posts/addPost", "/api/comments/addComment").permitAll()
                        .pathMatchers(HttpMethod.PUT, "/api/users/update/{id}","/api/posts/update/{id}", "/api/comments/{id}").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/api/users/delete/{id}", "/api/posts/delete/{id}", "/api/posts/delete/{id}").permitAll()
                        .pathMatchers("/api/users/**","/api/posts/**","/api/comments/**").denyAll()
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
