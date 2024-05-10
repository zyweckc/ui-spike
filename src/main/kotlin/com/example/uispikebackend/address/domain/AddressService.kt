package com.example.uispikebackend.address.domain

import com.example.uispikebackend.address.adapter.outbound.AddressRepository
import jakarta.inject.Named
import java.util.UUID

@Named
class AddressService(
    private val addressRepository: AddressRepository
) {
    fun getAll() = addressRepository.getAll()
    fun getById(id: UUID) = addressRepository.getById(id)
}
