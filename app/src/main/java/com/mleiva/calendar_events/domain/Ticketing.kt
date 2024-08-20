package com.mleiva.calendar_events.domain

data class Ticketing(
    val allInclusivePricing: AllInclusivePricing,
    val id: String,
    val safeTix: SafeTix
)