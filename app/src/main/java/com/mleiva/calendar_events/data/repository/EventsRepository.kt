package com.mleiva.calendar_events.data.repository

import com.mleiva.calendar_events.data.EventsService
import com.mleiva.calendar_events.data.RemoteEvent
import com.mleiva.calendar_events.domain.Accessibility
import com.mleiva.calendar_events.domain.AgeRestrictions
import com.mleiva.calendar_events.domain.EmbeddedXX
import com.mleiva.calendar_events.domain.Event
import com.mleiva.calendar_events.domain.ImageX
import com.mleiva.calendar_events.domain.PriceRange
import com.mleiva.calendar_events.domain.Seatmap
import com.mleiva.calendar_events.domain.Venue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data
 * Creted by: Marcelo Leiva on 15-08-2024 at 16:26
 ***/
class EventsRepository(private val moviesService: EventsService) {

    suspend fun fetchPopularEvents(startDateTime: String, endDateTime: String): List<Event> = withContext(Dispatchers.IO) {
        moviesService.fetchEvents("US", startDateTime, endDateTime)
            .Embedded.events
            .map { it.toDomainModel() }
    }

    private fun RemoteEvent.toDomainModel() = Event(
        id = id,
        name = name,
        url = url,
        locale = locale,
        sales = sales,
        info = info ?: "",
        priceRanges = priceRanges ?: listOf(PriceRange("",0.0,0.0,"")),
        seatmap = seatmap ?: Seatmap("", ""), // Asigna un valor por defecto si es null
        accessibility = accessibility ?: Accessibility(""),
        ageRestrictions = ageRestrictions ?: AgeRestrictions("", false),
        _embedded = _embedded ?: EmbeddedXX(listOf(Venue(""))),
        images = images ?: listOf(ImageX(false, 0, "", "", 0))
    )
}