package com.example.uispikebackend.config.rest

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.OAuthFlow
import io.swagger.v3.oas.models.security.OAuthFlows
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Value("\${open-api-config.auth-server-url}")
    private lateinit var keycloakServer: String

    @Value("\${open-api-config.realm}")
    private lateinit var keycloakRealm: String

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("UI Spike Backend API")
                    .description("REST API Dokumentation des Spike Backends"),
            )
            .components(Components().addSecuritySchemes("OAuth2", oAuth2SecurityScheme()))
            .addSecurityItem(oAuth2SecurityRequirement())
    }

    private fun oAuth2SecurityRequirement(): SecurityRequirement {
        return SecurityRequirement().addList("OAuth2")
    }

    private fun oAuth2SecurityScheme(): SecurityScheme {
        val tokenAuthUrl =
            keycloakServer.addTrailingSlashIfNecessary() + "realms/" + keycloakRealm + "/protocol/openid-connect/auth"
        return SecurityScheme()
            .type(SecurityScheme.Type.OAUTH2)
            .flows(
                OAuthFlows()
                    .implicit(
                        OAuthFlow()
                            .authorizationUrl(tokenAuthUrl),
                    ),
            )
    }

    private fun String.addTrailingSlashIfNecessary(): String {
        return when (this.endsWith("/")) {
            true -> this
            false -> "$this/"
        }
    }

}


