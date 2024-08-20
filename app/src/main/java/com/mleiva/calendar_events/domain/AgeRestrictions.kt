package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class AgeRestrictions(
    val id: String,
    val legalAgeEnforced: Boolean
)