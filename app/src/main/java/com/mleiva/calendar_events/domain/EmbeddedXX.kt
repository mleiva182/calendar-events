package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class EmbeddedXX(
    //val attractions: List<Attraction>,
    val venues: List<Venue>
)