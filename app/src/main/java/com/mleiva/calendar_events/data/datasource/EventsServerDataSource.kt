package com.mleiva.calendar_events.data.datasource

import com.mleiva.calendar_events.data.remote.EventsService
import com.mleiva.calendar_events.data.remote.RemoteEvent
import com.mleiva.calendar_events.domain.Accessibility
import com.mleiva.calendar_events.domain.AgeRestrictions
import com.mleiva.calendar_events.domain.Country
import com.mleiva.calendar_events.domain.Dates
import com.mleiva.calendar_events.domain.EmbeddedXX
import com.mleiva.calendar_events.domain.Event
import com.mleiva.calendar_events.domain.ImageX
import com.mleiva.calendar_events.domain.PriceRange
import com.mleiva.calendar_events.domain.Seatmap
import com.mleiva.calendar_events.domain.Start
import com.mleiva.calendar_events.domain.Venue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data.datasource
 * Creted by: Marcelo Leiva on 19-08-2024 at 21:39
 ***/
class EventsServerDataSource(
    private val eventsService: EventsService
): AnimesRemoteDataSource {

    override suspend fun fetchEvents(
        country: String,
        startDateTime: String,
        endDateTime: String
    ): List<Event> = withContext(Dispatchers.IO) {
        eventsService.fetchEvents(country, startDateTime, endDateTime)
            .Embedded.events
            .map { it.toDomainModel() }
    }
}

private fun RemoteEvent.toDomainModel() = Event(
    id = id,
    name = name,
    url = url,
    locale = locale,
    sales = sales,
    info = info ?: "",
    priceRanges = priceRanges ?: listOf(PriceRange("", 0.0, 0.0, "")),
    seatmap = seatmap ?: Seatmap("", ""), // Asigna un valor por defecto si es null
    accessibility = accessibility ?: Accessibility(""),
    ageRestrictions = ageRestrictions ?: AgeRestrictions("", false),
    _embedded = _embedded ?: EmbeddedXX(listOf(Venue(Country("", ""),""))),
    images = images ?: listOf(ImageX(false, 0, "", "", 0)),
    dates = dates ?: Dates(Start(false, false, "", "", "", false, false))
)

interface AnimesRemoteDataSource {
    suspend fun fetchEvents(country: String, startDateTime: String, endDateTime: String): List<Event>
}