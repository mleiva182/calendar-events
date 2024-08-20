package com.mleiva.calendar_events.ui.screens.events_selected

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.calendar_events.data.EventsClient
import com.mleiva.calendar_events.data.repository.EventsRepository
import com.mleiva.calendar_events.domain.Event
import kotlinx.coroutines.launch

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.events_selected
 * Creted by: Marcelo Leiva on 19-08-2024 at 14:59
 ***/
class EventsSelectedViewModel: ViewModel() {

    private val repository = EventsRepository(EventsClient.instance)

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, events = repository.fetchPopularEvents(
                "2024-08-19T00:00:00Z",
                "2024-08-19T23:59:59Z"
                )
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val events: List<Event> = emptyList()
    )

}