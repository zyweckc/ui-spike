package com.example.uispikebackend.address.model

import java.util.*

data class Address(
    val id: UUID,
    var firstname: String,
    var lastname: String,
    var age: Int,
    var email: String,
)
