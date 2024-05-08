package com.example.uispikebackend.adapter.outbound

import com.example.uispikebackend.model.Address
import org.springframework.stereotype.Component

@Component
class AddressResourceLoader {
    private val addresses = List(100) { idx ->
        Address("John $idx", "Doe", idx + 20, "j.doe@email.com")
    }

    fun loadAddress(): List<Address> {
        return addresses
    }

    fun loadOne(): Address {
        return addresses.first()
    }
}
