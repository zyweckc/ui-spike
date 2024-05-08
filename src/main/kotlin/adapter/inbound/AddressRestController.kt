package adapter.inbound

import adapter.outbound.AddressResourceLoader
import model.Address
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/address")
class AddressRestController (
    private val addressResourceLoader: AddressResourceLoader
) {
    @GetMapping (produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): ResponseEntity<List<Address>> {
        return ResponseEntity.ok(
            addressResourceLoader.loadAddress()
        )
    }
}
