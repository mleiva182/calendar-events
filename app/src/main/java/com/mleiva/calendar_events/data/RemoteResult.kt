package com.mleiva.calendar_events.data

import android.media.Image
import com.mleiva.calendar_events.domain.Accessibility
import com.mleiva.calendar_events.domain.AgeRestrictions
import com.mleiva.calendar_events.domain.Embedded
import com.mleiva.calendar_events.domain.EmbeddedXX
import com.mleiva.calendar_events.domain.ImageX
import com.mleiva.calendar_events.domain.PriceRange
import com.mleiva.calendar_events.domain.Sales
import com.mleiva.calendar_events.domain.Seatmap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.domain
 * Creted by: Marcelo Leiva on 15-08-2024 at 16:16
 ***/
@Serializable
data class RemoteResult(
    @SerialName("_embedded") val Embedded: Embedded2
)

@Serializable
data class Embedded2(
    val events: List<RemoteEvent>
)

@Serializable
data class RemoteEvent(
    val id: String,
    val name: String,
    val url: String,
    val locale: String,
    val sales: Sales,
    val info: String? = null,
    val priceRanges: List<PriceRange>? = null,
    val seatmap: Seatmap? = null,
    val accessibility: Accessibility? = null,
    val ageRestrictions: AgeRestrictions? = null,
    val _embedded: EmbeddedXX? = null,
    val images: List<ImageX>? = null,
)


