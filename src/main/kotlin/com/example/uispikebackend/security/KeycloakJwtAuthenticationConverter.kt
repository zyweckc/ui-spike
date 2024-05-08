package com.example.uispikebackend.security

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

class KeycloakJwtAuthenticationConverter : JwtAuthenticationConverter() {
    init {
        setJwtGrantedAuthoritiesConverter(KeycloakJwtGrantedAuthoritiesConverter())
        setPrincipalClaimName("preferred_username")
    }


}
