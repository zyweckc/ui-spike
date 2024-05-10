package com.example.uispikebackend.address.adapter.inbound

import com.example.uispikebackend.address.domain.AddressService
import com.example.uispikebackend.address.model.Address
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/address")
class AddressRestController(
    private val addressService: AddressService
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<List<Address>> {
        return ResponseEntity.ok(
            addressService.getAll()
        )
    }

    @Suppress("UnusedParameter") // accepted for now
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOne(@PathVariable id: Int): ResponseEntity<Address> {
        return ResponseEntity.ok(
            addressService.getOne()
        )
    }
}
