package com.example.uispikebackend.address.adapter.outbound

import com.example.uispikebackend.address.model.Address
import io.github.oshai.kotlinlogging.KotlinLogging.logger
import net.datafaker.Faker
import org.springframework.stereotype.Component
import java.util.*

@Component
class AddressResourceLoader {
    private val logger = logger {}
    private val seed = 42781L
    private val faker: Faker = Faker(Random(seed))
    private lateinit var addresses: List<Address>

    init {
        addresses = initializeAddresses()
    }


    fun loadAddress(): List<Address> {
        logger.info { "Retrieving all addresses" }
        return addresses
    }

    fun loadOne(): Address {
        logger.info { "Retrieving one address" }
        return addresses.first()
    }

    private fun initializeAddresses(): List<Address> {
        logger.info { "Initializing addresses" }
        val random = kotlin.random.Random(seed)
        return List(100) {
            val firstName = faker.name().firstName()
            val lastName = faker.name().lastName()
            Address(
                firstname = firstName,
                lastname = lastName,
                age = random.nextInt(from = 19, until = 89),
                email = "${firstName.first()}.$lastName@" + arrayOf(
                    "gmail.com",
                    "yahoo.com",
                    "hotmail.com"
                ).random(kotlin.random.Random(seed))
            )
        }
    }
}
