package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class PriceRange(
    val currency: String = "",
    val max: Double = 0.0,
    val min: Double = 0.0,
    val type: String = ""
)