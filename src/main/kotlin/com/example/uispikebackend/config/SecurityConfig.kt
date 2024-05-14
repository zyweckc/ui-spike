package com.example.uispikebackend.config

import com.example.uispikebackend.config.security.KeycloakJwtAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.web.SecurityFilterChain

private const val CLAIM_REALM_ACCESS = "realm_access"
private const val CLAIM_ROLES = "roles"
private const val ROLE_USER = "user"

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Order(1)
    @Bean
    fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.disable() }
            .securityMatcher("/api/**")
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/address/**").hasRole(ROLE_USER)
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
                    // static resources
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/fonts/**").permitAll()
                    // web frontend
                    .requestMatchers("/ui/").hasRole(ROLE_USER)
                    .requestMatchers("/ui/details").hasRole(ROLE_USER)
                    // block all other requests
                    .anyRequest().denyAll()
            }.oauth2Login(Customizer.withDefaults()).build()
    }

    @Suppress("Unchecked_Cast")
    @Bean
    fun keycloakGrantedAuthoritiesMapper(): GrantedAuthoritiesMapper {
        return GrantedAuthoritiesMapper { authorities ->
            val mappedAuthorities = mutableSetOf<GrantedAuthority>()
            val authority = authorities.iterator().next()
            if (authority is OidcUserAuthority) {
                val userInfo = authority.userInfo
                if (userInfo.hasClaim(CLAIM_REALM_ACCESS)) {
                    val realmAccess = userInfo.getClaimAsMap(CLAIM_REALM_ACCESS)
                    val roles = realmAccess[CLAIM_ROLES] as Collection<String>
                    mappedAuthorities.addAll(roles.map { role -> SimpleGrantedAuthority("ROLE_$role") })
                }
            }
            mappedAuthorities
        }
    }
}
