package com.mleiva.calendar_events.use_cases

import com.mleiva.calendar_events.data.repository.EventsRepository
import com.mleiva.calendar_events.domain.Event
import kotlinx.coroutines.flow.Flow

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.use_cases
 * Creted by: Marcelo Leiva on 19-08-2024 at 21:59
 ***/
class FetchEventsUseCase(
    private val eventsRepository: EventsRepository
) {
    operator fun invoke(
        country: String,
        startDateTime: String,
        endDateTime: String): Flow<List<Event>> = eventsRepository.fetchEvents(country, startDateTime, endDateTime)

}