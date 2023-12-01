package navigation

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodeEvent
import androidx.compose.runtime.*
import ui.day.DayScreen
import ui.event.EventScreen
import ui.home.HomeScreen

enum class Route(route: String) {
    HOME("home"),
    EVENT("event"),
    DAY("day"),
}


@Composable
fun AdventOfCodeNavHost() {
    var destination by remember {
        mutableStateOf(Route.HOME)
    }

    var event by remember {
        mutableStateOf<AdventOfCodeEvent?>(null)
    }

    var day by remember {
        mutableStateOf<AdventOfCodeDay?>(null)
    }

    when (destination) {
        Route.HOME -> {
            HomeScreen(
                goToEvent = {
                    event = it
                    destination = Route.EVENT
                }
            )
        }

        Route.EVENT -> {
            event?.let {
                EventScreen(
                    event = it,
                    goToDay = {
                        day = it
                        destination = Route.DAY
                    },
                    goBack = {
                        event = null
                        destination = Route.HOME
                    }
                )
            }
        }

        Route.DAY -> {
            day?.let {
                DayScreen(
                    day = it,
                    goBack = {
                        day = null
                        destination = Route.EVENT
                    }
                )
            }
        }
    }
}