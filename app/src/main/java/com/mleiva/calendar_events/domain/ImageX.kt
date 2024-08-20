package com.mleiva.calendar_events.domain

import kotlinx.serialization.Serializable

@Serializable
data class ImageX(
    val fallback: Boolean = false,
    val height: Int = 0,
    val ratio: String = "",
    val url: String = "",
    val width: Int = 0
)