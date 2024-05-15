package com.example.uispikebackend.address.adapter.inbound

import com.example.uispikebackend.address.domain.AddressService
import com.example.uispikebackend.address.model.Address
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

private const val INDEX = "index"
private const val DETAILS = "details"

@Controller
@RequestMapping("/api/address")
class AddressController(
    private val addressService: AddressService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getAll(): ResponseEntity<List<Address>> {
        return ResponseEntity.ok(
            addressService.getAll()
        )
    }

    @GetMapping(produces = [MediaType.TEXT_HTML_VALUE])
    fun getAll(model: Model): String {
        model.addAttribute("addresses", addressService.getAll())
        return INDEX
    }

    @Suppress("UnusedParameter") // accepted for now
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getOne(@PathVariable id: UUID): ResponseEntity<Address> {
        return ResponseEntity.ok(
            addressService.getById(id)
        )
    }

    @RequestMapping("/details")
    fun getOne(@RequestParam id: UUID, model: Model): String {
        model.addAttribute("address", addressService.getById(id))
        return DETAILS
    }
}
