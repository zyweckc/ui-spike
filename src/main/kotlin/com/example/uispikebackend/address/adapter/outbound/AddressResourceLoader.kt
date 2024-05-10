package com.example.uispikebackend.address.adapter.outbound

import com.example.uispikebackend.address.model.Address
import io.github.oshai.kotlinlogging.KotlinLogging.logger
import net.datafaker.Faker
import org.springframework.stereotype.Component
import java.util.*

private const val SEED = 42781L

@Component
class AddressResourceLoader {
    private val logger = logger {}
    private val faker: Faker = Faker(Random(SEED))
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
        val random = kotlin.random.Random(SEED)
        @Suppress("MagicNumber") // this is not an issue
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
                ).random(kotlin.random.Random(SEED))
            )
        }
    }
}
