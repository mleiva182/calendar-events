package com.mleiva.calendar_events.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data.database
 * Creted by: Marcelo Leiva on 19-08-2024 at 20:48
 ***/
@Database(entities = [DbEvent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CalendarEventsDatabase: RoomDatabase() {

    abstract fun calendarEventsDao(): CalendarEventsDao

}

object DatabaseProvider {
    @Volatile
    private var INSTANCE: CalendarEventsDatabase? = null

    fun getDatabase(context: Context): CalendarEventsDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CalendarEventsDatabase::class.java,
                "calendar-events-name"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}