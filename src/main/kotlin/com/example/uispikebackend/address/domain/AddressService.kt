package com.example.uispikebackend.address.domain

import com.example.uispikebackend.address.adapter.outbound.AddressResourceLoader
import jakarta.inject.Named

@Named
class AddressService(
    private val addressResourceLoader: AddressResourceLoader
) {
    fun getAll() = addressResourceLoader.loadAddress()
    fun getOne() = addressResourceLoader.loadOne()
}
