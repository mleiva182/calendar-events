package com.mleiva.calendar_events.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mleiva.calendar_events.ui.theme.CalendareventsTheme

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.common
 * Creted by: Marcelo Leiva on 15-08-2024 at 15:34
 ***/
@Composable
fun Screen(content: @Composable () -> Unit) {
    CalendareventsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}