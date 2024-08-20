package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class Start(
    val dateTBA: Boolean = false,
    val dateTBD: Boolean = false,
    val dateTime: String = "",
    val localDate: String = "",
    val localTime: String = "",
    val noSpecificTime: Boolean = false,
    val timeTBA: Boolean = false
)