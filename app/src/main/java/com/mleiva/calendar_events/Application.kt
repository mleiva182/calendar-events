package com.mleiva.calendar_events

import android.app.Application
import androidx.room.Room
import com.mleiva.calendar_events.data.database.CalendarEventsDatabase

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events
 * Creted by: Marcelo Leiva on 19-08-2024 at 20:51
 ***/
class Application: Application() {

    lateinit var database: CalendarEventsDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CalendarEventsDatabase::class.java, "calendar-events-db").build()
    }

}