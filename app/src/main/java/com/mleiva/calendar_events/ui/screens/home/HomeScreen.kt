package com.mleiva.calendar_events.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.yearMonth
import com.mleiva.calendar_events.R
import com.mleiva.calendar_events.ui.common.Screen
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.home
 * Creted by: Marcelo Leiva on 15-08-2024 at 15:31
 ***/
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    onDayClick: (String) -> Unit,
    vm: HomeViewModel = viewModel()
) {

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                calendario(viewModel = vm, onDayClick = onDayClick)

                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}

@Composable
fun calendario(
    viewModel: HomeViewModel,
    onDayClick: (String) -> Unit
){

    val selectedDate by viewModel.selectedDate
    val currentMonth by viewModel.currentMonth

    val calendarState = rememberCalendarState(
        startMonth = viewModel.minDate.yearMonth,
        endMonth = viewModel.maxDate.yearMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = DayOfWeek.MONDAY
    )

    var isYearDropdownExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Actualizar el mes visible en el ViewModel
    LaunchedEffect(calendarState.firstVisibleMonth) {
        viewModel.onMonthChanged(calendarState.firstVisibleMonth.yearMonth)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                coroutineScope.launch {
                    calendarState.scrollToMonth(calendarState.firstVisibleMonth.yearMonth.minusMonths(1))
                }
            }) {
                Text("Anterior")
            }

            Box {
                Text(
                    text = "${currentMonth.month} ${currentMonth.year}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.clickable { isYearDropdownExpanded = true }
                )

                DropdownMenu(
                    expanded = isYearDropdownExpanded,
                    onDismissRequest = { isYearDropdownExpanded = false },
                    modifier = Modifier.width(100.dp)
                ) {
                    (currentMonth.year - 10..currentMonth.year + 10).forEach { year ->
                        DropdownMenuItem(
                            text = { Text(text = year.toString()) },
                            onClick = {
                                coroutineScope.launch {
                                    calendarState.scrollToMonth(YearMonth.of(year, currentMonth.month))
                                }
                                isYearDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            TextButton(onClick = {
                coroutineScope.launch {
                    calendarState.scrollToMonth(calendarState.firstVisibleMonth.yearMonth.plusMonths(1))
                }
            }) {
                Text("Siguiente")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                DayContent(day,
                    selectedDate,
                    { clickedDate ->
                        viewModel.onDateSelected(clickedDate)
                    },
                    { onDayClick(selectedDate.toString()) }
                )
            }
        )
    }

}

@Composable
fun DayContent(day: CalendarDay, selectedDate: LocalDate?, onClick: (LocalDate) -> Unit, onDayClick: (String) -> Unit) {
    val isSelected = day.date == selectedDate
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground

    println("DayContent: $selectedDate")
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(backgroundColor, CircleShape)
            .clickable(enabled = day.position == DayPosition.MonthDate) {
                onClick(day.date)
                onClick
                onDayClick(selectedDate.toString())
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            textAlign = TextAlign.Center
        )

    }
}