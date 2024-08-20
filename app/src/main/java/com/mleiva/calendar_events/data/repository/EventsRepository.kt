package com.mleiva.calendar_events.data.repository

import com.mleiva.calendar_events.data.datasource.EventsLocalDataSource
import com.mleiva.calendar_events.data.datasource.EventsServerDataSource
import com.mleiva.calendar_events.data.remote.EventsService
import com.mleiva.calendar_events.data.remote.RemoteEvent
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
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data
 * Creted by: Marcelo Leiva on 15-08-2024 at 16:26
 ***/
class EventsRepository(
    private val eventslocalDataSource: EventsLocalDataSource,
    private val eventsServerDataSource: EventsServerDataSource
) {

    fun fetchEvents(country: String, startDateTime: String, endDateTime: String): Flow<List<Event>> = eventslocalDataSource.fetchEvents(country, startDateTime, endDateTime)
        .onEach { localAnimes ->
            if (localAnimes.isEmpty()) {
                val remoteAnimes = eventsServerDataSource.fetchEvents(country, startDateTime, endDateTime)
                eventslocalDataSource.insertEventIfCondition(remoteAnimes, "")
            }else{
                val remoteAnimes = eventsServerDataSource.fetchEvents(country, startDateTime, endDateTime)
                remoteAnimes.forEach { remoteAnime ->
                    eventslocalDataSource.insertEventIfCondition(remoteAnimes, remoteAnime.id)
                }
            }

        }

}