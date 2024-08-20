package com.mleiva.calendar_events.data.datasource

import com.mleiva.calendar_events.data.database.CalendarEventsDao
import com.mleiva.calendar_events.data.database.DbEvent
import com.mleiva.calendar_events.domain.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data.datasource
 * Creted by: Marcelo Leiva on 19-08-2024 at 21:31
 ***/
class EventsLocalDataSource(
    private val calendarEventsDao: CalendarEventsDao
): AnimesLocalDataSource {

    override fun fetchEvents(
        country: String,
        startDateTime: String,
        endDateTime: String
    ): Flow<List<Event>> =
        calendarEventsDao.getEvents().map { events ->
            events.map { it.toDomainModel() }
        }

    override suspend fun save(events: List<Event>) {
        calendarEventsDao.insertEvent(events.map { it.toDbModel() })
    }

    override suspend fun insertEventIfCondition(event: List<Event>, condition: String) {
        calendarEventsDao.insertEventIfCondition(event.map { it.toDbModel() }, condition)
    }
}

private fun DbEvent.toDomainModel() = Event(
    id = idApi,
    name = name,
    url = url,
    locale = locale,
    sales = sales,
    info = info,
    priceRanges = priceRanges,
    seatmap = seatmap,
    accessibility = accessibility,
    ageRestrictions = ageRestrictions,
    _embedded = _embedded,
    images = images,
    dates = dates
)

private fun Event.toDbModel() = DbEvent(
    idApi = id,
    name = name,
    url = url,
    locale = locale,
    sales = sales,
    info = info,
    priceRanges = priceRanges,
    seatmap = seatmap,
    accessibility = accessibility,
    ageRestrictions = ageRestrictions,
    _embedded = _embedded,
    images = images,
    dates = dates
)

interface AnimesLocalDataSource {
    fun fetchEvents(country: String, startDateTime: String, endDateTime: String): Flow<List<Event>>

    suspend fun save(events: List<Event>)

    suspend fun insertEventIfCondition(event: List<Event>, condition: String)
}