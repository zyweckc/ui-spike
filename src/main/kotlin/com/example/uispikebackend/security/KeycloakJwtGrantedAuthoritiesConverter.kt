package com.example.uispikebackend.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class KeycloakJwtGrantedAuthoritiesConverter : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess = jwt.getClaim<Map<String, Any>>("realm_access") ?: emptyMap()
        val roles = realmAccess["roles"]
        if (roles == null || !MutableCollection::class.java.isAssignableFrom(roles.javaClass)) {
            return emptySet()
        }

        return (roles as Collection<*>)
            .filterIsInstance<String>()
            .map { SimpleGrantedAuthority("ROLE_$it") }
            .toSet()
    }
}
