package com.example.uispikebackend.address.domain

import com.example.uispikebackend.address.adapter.outbound.AddressResourceLoader
import org.springframework.stereotype.Service

@Service
class AddressService(
    private val addressResourceLoader: AddressResourceLoader
) {
    fun getAll() = addressResourceLoader.loadAddress()
    fun getOne() = addressResourceLoader.loadOne()
}
