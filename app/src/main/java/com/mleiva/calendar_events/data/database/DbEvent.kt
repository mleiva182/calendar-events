package com.mleiva.calendar_events.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mleiva.calendar_events.domain.Accessibility
import com.mleiva.calendar_events.domain.AgeRestrictions
import com.mleiva.calendar_events.domain.Country
import com.mleiva.calendar_events.domain.Dates
import com.mleiva.calendar_events.domain.EmbeddedXX
import com.mleiva.calendar_events.domain.ImageX
import com.mleiva.calendar_events.domain.PriceRange
import com.mleiva.calendar_events.domain.Sales
import com.mleiva.calendar_events.domain.Seatmap

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data.database
 * Creted by: Marcelo Leiva on 19-08-2024 at 20:46
 ***/
@Entity
data class DbEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idApi: String,
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
    val dates: Dates
)
