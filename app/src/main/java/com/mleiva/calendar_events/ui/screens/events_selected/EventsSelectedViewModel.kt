package com.mleiva.calendar_events.ui.screens.events_selected

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mleiva.calendar_events.Constants
import com.mleiva.calendar_events.data.remote.EventsClient
import com.mleiva.calendar_events.data.repository.EventsRepository
import com.mleiva.calendar_events.domain.Event
import com.mleiva.calendar_events.use_cases.FetchEventsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.YearMonth

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.events_selected
 * Creted by: Marcelo Leiva on 19-08-2024 at 14:59
 ***/
class EventsSelectedViewModel(
    val country: String,
    val localDate: String,
    fetchEventsUseCase: FetchEventsUseCase
): ViewModel() {

    class MyViewModelFactory(
        private val country: String,
        private val localDate: String,
        private val fetchEventsUseCase: FetchEventsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventsSelectedViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventsSelectedViewModel(country, localDate, fetchEventsUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: List<Event> = emptyList()
    )

    val state: StateFlow<UiState> = fetchEventsUseCase(country,"$localDate${Constants.INIT_DATE}", "$localDate${Constants.END_DATE}")
        .map { events ->
            val filteredEvents = events.filter { event ->
                // Aplica aquí la condición de filtrado que desees
                event.dates.start.localDate == localDate &&
                event._embedded.venues[0].country.countryCode == country
            }
            UiState(events = filteredEvents)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )
}