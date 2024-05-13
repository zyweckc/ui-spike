package com.example.uispikebackend.address.adapter.inbound

import com.example.uispikebackend.address.domain.AddressService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

private const val INDEX = "index"
private const val DETAILS = "details"

@Controller
@RequestMapping("/ui")
class AddressWebController(
    private val addressService: AddressService
) {

    @RequestMapping("/")
    fun index(model: Model): String {
        model.addAttribute("addresses", addressService.getAll())
        return INDEX
    }

    @RequestMapping("/details")
    fun details(@RequestParam id: UUID, model: Model): String {
        model.addAttribute("address", addressService.getById(id))
        return DETAILS
    }
}
