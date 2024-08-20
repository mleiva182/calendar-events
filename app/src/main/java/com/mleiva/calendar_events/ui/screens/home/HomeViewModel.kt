package com.mleiva.calendar_events.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.YearMonth

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.home
 * Creted by: Marcelo Leiva on 15-08-2024 at 15:40
 ***/
class HomeViewModel: ViewModel() {

    private val _selectedDate = mutableStateOf<LocalDate?>(null)
    val selectedDate: State<LocalDate?> = _selectedDate

    private val _currentMonth = mutableStateOf(YearMonth.now())
    val currentMonth: State<YearMonth> = _currentMonth

    val minDate = LocalDate.now().minusMonths(1)
    val maxDate = LocalDate.now().plusMonths(6)

    fun onDateSelected(date: LocalDate) {
        if (date in minDate..maxDate) {
            _selectedDate.value = date
        }
    }

    fun onMonthChanged(yearMonth: YearMonth) {
        _currentMonth.value = yearMonth
    }

}