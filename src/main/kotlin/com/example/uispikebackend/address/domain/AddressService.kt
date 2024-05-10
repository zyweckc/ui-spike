package com.example.uispikebackend.address.domain

import com.example.uispikebackend.address.adapter.outbound.AddressResourceLoader
import jakarta.inject.Named
import java.util.UUID

@Named
class AddressService(
    private val addressResourceLoader: AddressResourceLoader
) {
    fun getAll() = addressResourceLoader.getAll()
    fun getById(id: UUID) = addressResourceLoader.getById(id)
}
