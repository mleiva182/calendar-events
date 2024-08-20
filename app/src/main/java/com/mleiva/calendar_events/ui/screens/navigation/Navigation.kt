package com.mleiva.calendar_events.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mleiva.calendar_events.ui.screens.events_selected.EventsSelectedScreen
import com.mleiva.calendar_events.ui.screens.home.HomeScreen

/***
 * Project: calendar-events
 * From: com.mleiva.calendar_events.ui.screens.navigation
 * Creted by: Marcelo Leiva on 15-08-2024 at 15:29
 ***/
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(onDayClick = { date ->
                navController.navigate(Screen.Events.createRoute(date))
            })
        }

        composable(
            route = Screen.Events.route,
            arguments = listOf(navArgument(NavArgs.SelectedDate.key) { type = NavType.StringType })
        ) { back ->
            EventsSelectedScreen(
                onBack = { navController.popBackStack() },
                backStackEntry = back
            )
        }
    }

}

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Events : Screen("events_selected/{${NavArgs.SelectedDate.key}}"){
        fun createRoute(selectedDate: String) = "events_selected/$selectedDate"
    }

}

enum class NavArgs(val key: String) {
    SelectedDate("selectedDate")
}