package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class Public(
    val endDateTime: String = "",
    val startDateTime: String = "",
    val startTBA: Boolean = false,
    val startTBD: Boolean = false
)