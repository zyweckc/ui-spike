package com.example.uispikebackend.config

import com.example.uispikebackend.config.security.KeycloakJwtAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Order(1)
    @Bean
    fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .securityMatcher("/api/**")
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/address/**").hasRole("user")
            }.oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt -> jwt.jwtAuthenticationConverter(KeycloakJwtAuthenticationConverter()) }
            }.build()
    }

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it
                    // actuators
                    .requestMatchers("/actuator/health").permitAll()
                    // OpenAPI
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/swagger-ui.html").permitAll()
                    // web frontend
                    .requestMatchers("/ui/").fullyAuthenticated()
                    .requestMatchers("/ui/details").fullyAuthenticated()
                    // block all other requests
                    .anyRequest().denyAll()
            }.oauth2Login(Customizer.withDefaults()).build()
    }
}
