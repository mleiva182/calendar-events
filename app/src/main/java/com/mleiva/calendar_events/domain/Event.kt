package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

data class Event(
    val id: String,
    val name: String,
    val url: String,
    val locale: String,
    val sales: Sales,
    val info: String,
    val priceRanges: List<PriceRange>,
    val seatmap: Seatmap,
    val accessibility: Accessibility,
    val ageRestrictions: AgeRestrictions,
    val _embedded: EmbeddedXX,
    val images: List<ImageX>,
    /*val _links: LinksXX,
    val classifications: List<ClassificationX>,
    val dates: Dates,
    val images: List<ImageX>,
    val pleaseNote: String,
    val test: Boolean,
    val ticketLimit: TicketLimit,
    val ticketing: Ticketing,
    val type: String,
    val url: String*/
)