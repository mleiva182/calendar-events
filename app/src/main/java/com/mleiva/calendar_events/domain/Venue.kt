package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    /*val _links: Links,
    val ada: Ada,
    val address: Address,
    val city: City,*/
    val country: Country,
    /*val dmas: List<Dma>,
    val id: String,
    val locale: String,
    val location: Location,
    val markets: List<Market>,
    val name: String,
    val postalCode: String,
    val state: State,
    val test: Boolean,
    val timezone: String,
    val type: String,
    val upcomingEvents: UpcomingEvents,*/
    val url: String = ""
)