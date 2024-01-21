//package com.example.gateway.filter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class GatewaySecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers(HttpMethod.GET, "/api/users/**").permitAll()
//                        .anyExchange().authenticated()
//                );
//        return http.build();
//    }
//
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
////        http
////                .authorizeExchange(exchanges -> exchanges
////                        // Cho phép tất cả các yêu cầu POST đến /api/users/** không cần xác thực
////                        .pathMatchers(HttpMethod.POST, "/api/users/**").permitAll()
////                        // Yêu cầu xác thực cho tất cả các yêu cầu khác
////                        .anyExchange().authenticated()
////                )
////
////        ;
////
////        return http.build();
////    }
//}
