package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val countryCode: String = "",
    val name: String = ""
)