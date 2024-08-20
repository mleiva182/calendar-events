package com.mleiva.calendar_events.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data
 * Creted by: Marcelo Leiva on 15-08-2024 at 16:23
 ***/
interface EventsService {

    @GET("events.json")
    suspend fun fetchEvents(
        @Query("countryCode") countryCode: String = "AU",
        @Query("startDateTime") startDateTime: String,
        @Query("endDateTime") endDateTime: String
    ): RemoteResult

}