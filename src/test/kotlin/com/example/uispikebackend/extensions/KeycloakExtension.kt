package com.example.uispikebackend.extensions

import dasniko.testcontainers.keycloak.KeycloakContainer
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class KeycloakExtension : BeforeAllCallback {

    companion object {
        val keycloak = KeycloakContainer()

        @DynamicPropertySource
        fun registerKeycloakProperties(registry: DynamicPropertyRegistry) {
            keycloak.start()
            val authServerUrl = "${keycloak.authServerUrl}/realms/ui-spike"
            registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri") { authServerUrl }
            registry.add("spring.security.oauth2.client.provider.keycloak.issuer-uri") { authServerUrl }
        }
    }


    override fun beforeAll(context: ExtensionContext?) {
        if (!keycloak.isRunning) {
            keycloak.start()
        }
    }
}
