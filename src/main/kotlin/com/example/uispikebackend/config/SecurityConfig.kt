package com.example.uispikebackend.config

import com.example.uispikebackend.config.security.KeycloakJwtAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests {
            it
                // actuators
                .requestMatchers("/actuator/health").permitAll()
                // OpenAPI
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                // domain api
                .requestMatchers("/api/address/**").hasRole("user")
                // block all other requests
                .anyRequest().denyAll()
        }.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwt -> jwt.jwtAuthenticationConverter(KeycloakJwtAuthenticationConverter()) }
        }.build()
    }
}
