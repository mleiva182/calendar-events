package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class Seatmap(
    val id: String,
    val staticUrl: String
)