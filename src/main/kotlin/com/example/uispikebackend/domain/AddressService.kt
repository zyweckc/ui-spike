package com.example.uispikebackend.domain

import com.example.uispikebackend.adapter.outbound.AddressResourceLoader
import org.springframework.stereotype.Service

@Service
class AddressService(
    private val addressResourceLoader: AddressResourceLoader
) {
    fun getAll() = addressResourceLoader.loadAddress()
    fun getOne() = addressResourceLoader.loadOne()
}
