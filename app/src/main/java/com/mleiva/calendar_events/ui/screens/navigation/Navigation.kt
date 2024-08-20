package com.mleiva.calendar_events.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mleiva.calendar_events.data.database.CalendarEventsDao
import com.mleiva.calendar_events.data.database.CalendarEventsDatabase
import com.mleiva.calendar_events.data.database.DatabaseProvider
import com.mleiva.calendar_events.data.datasource.EventsLocalDataSource
import com.mleiva.calendar_events.data.datasource.EventsServerDataSource
import com.mleiva.calendar_events.data.remote.EventsClient
import com.mleiva.calendar_events.data.remote.EventsService
import com.mleiva.calendar_events.data.repository.EventsRepository
import com.mleiva.calendar_events.ui.screens.events_selected.EventsSelectedScreen
import com.mleiva.calendar_events.ui.screens.events_selected.EventsSelectedViewModel
import com.mleiva.calendar_events.ui.screens.home.HomeScreen
import com.mleiva.calendar_events.use_cases.FetchEventsUseCase

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.navigation
 * Creted by: Marcelo Leiva on 15-08-2024 at 15:29
 ***/
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val db = DatabaseProvider.getDatabase(LocalContext.current)
    val dao = db.calendarEventsDao()
    val eventsLocalDataSource = EventsLocalDataSource(dao)
    val eventsServerDataSource = EventsServerDataSource(EventsClient.instance)
    val repository = EventsRepository(eventsLocalDataSource, eventsServerDataSource)

    val fetchEventsUseCase = FetchEventsUseCase(repository)
    //val eventsViewModel = EventsSelectedViewModel(fetchEventsUseCase)

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(onDayClick = { date, countryCode ->
                navController.navigate(Screen.Events.createRoute(date, countryCode))
            })
        }

        composable(
            route = Screen.Events.route,
            arguments = listOf(
                navArgument(NavArgs.SelectedDate.key) { type = NavType.StringType },
                navArgument(NavArgs.CountryCode.key) { type = NavType.StringType }
            )
        ) { back ->
            EventsSelectedScreen(
                fetchEventsUseCase = fetchEventsUseCase,
                onBack = { navController.popBackStack() },
                backStackEntry = back
            )
        }
    }

}

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Events : Screen("events_selected/{${NavArgs.SelectedDate.key}}/{${NavArgs.CountryCode.key}}"){
        fun createRoute(selectedDate: String, countryCode: String) = "events_selected/$selectedDate/$countryCode"
    }

}

enum class NavArgs(val key: String) {
    SelectedDate("selectedDate"),
    CountryCode("countryCode")
}