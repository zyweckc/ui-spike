package com.example.uispikebackend.address.adapter.outbound

import com.example.uispikebackend.address.model.Address
import io.github.oshai.kotlinlogging.KotlinLogging.logger
import org.springframework.stereotype.Component

@Component
class AddressResourceLoader {
    private val logger = logger {}
    private val addresses = initializeAddresses()

    fun loadAddress(): List<Address> {
        logger.info { "Retrieving all addresses" }
        return addresses
    }

    fun loadOne(): Address {
        logger.info { "Retrieving one address" }
        return addresses.first()
    }

    private fun initializeAddresses(): List<Address> {
        logger.info { "Initializing addresses" }
        return List(100) { idx ->
            Address("John $idx", "Doe", idx + 20, "j.doe@email.com")
        }
    }
}
