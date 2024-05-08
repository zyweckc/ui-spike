package adapter.outbound

import model.Address
import org.springframework.stereotype.Component

@Component
class AddressResourceLoader {
    fun loadAddress(): List<Address> {
        return List(5) { idx ->
            Address("John $idx", "Doe", idx+20, "j.doe@email.com")
        }
    }
}
