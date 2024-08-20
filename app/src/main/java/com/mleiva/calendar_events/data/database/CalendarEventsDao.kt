package com.mleiva.calendar_events.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.data.database
 * Creted by: Marcelo Leiva on 19-08-2024 at 20:49
 ***/
@Dao
interface CalendarEventsDao {

    @Query("SELECT * FROM DbEvent")
    fun getEvents(): Flow<List<DbEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: List<DbEvent>)

    @Query("SELECT COUNT(*) FROM DbEvent WHERE idApi = :condition")
    suspend fun getEventsWithCondition(condition: String): Int

    suspend fun insertEventIfCondition(event: List<DbEvent>, condition: String) {
        val existingEvents = getEventsWithCondition(condition)
        println("existingEvents: $existingEvents")
        if (existingEvents==0) {
            insertEvent(event)
        }
    }
}