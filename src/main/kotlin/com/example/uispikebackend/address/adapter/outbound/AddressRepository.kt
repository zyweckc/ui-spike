package com.example.uispikebackend.address.adapter.outbound

import com.example.uispikebackend.address.model.Address
import io.github.oshai.kotlinlogging.KotlinLogging.logger
import net.datafaker.Faker
import org.springframework.stereotype.Component
import java.util.*

private const val SEED = 42781L
private const val ADDRESS_POOL_SIZE = 100

/**
 * Fake repository for addresses.

 */
@Component
class AddressRepository {
    private val logger = logger {}
    private val faker: Faker = Faker(Random(SEED))
    private val mailProviders = arrayOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "mail.com")
    private lateinit var addresses: List<Address>

    init {
        addresses = initializeAddresses()
    }


    fun getAll(): List<Address> {
        logger.info { "Retrieving all addresses." }
        return addresses
    }

    fun getById(id: UUID): Address {
        logger.info { "Retrieving address by id. [id=$id]" }
        return addresses.firstOrNull { it.id == id } ?: run {
            logger.error { "Requested address not found. [id=$id]" }
            throw NoSuchElementException("Address not found")
        }
    }

    private fun initializeAddresses(): List<Address> {
        logger.info { "Initializing addresses" }
        val random = kotlin.random.Random(SEED)
        return List(ADDRESS_POOL_SIZE) {
            val firstName = faker.name().firstName()
            val lastName = faker.name().lastName()
            Address(
                id = UUID.randomUUID(),
                firstname = firstName,
                lastname = lastName,
                age = random.nextInt(from = 19, until = 89),
                email = "${firstName.first().lowercase()}.${lastName.lowercase()}@" + mailProviders.random(random)
            )
        }
    }
}
