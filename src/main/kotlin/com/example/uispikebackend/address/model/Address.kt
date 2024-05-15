package com.example.uispikebackend.address.model

import java.util.*

data class Address(
    val id: UUID,
    val firstname: String,
    val lastname: String,
    val age: Int,
    val email: String,
)
