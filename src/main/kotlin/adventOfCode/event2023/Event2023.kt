package adventOfCode.event2023

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodeEvent
import adventOfCode.event2023.day1.Day1
import adventOfCode.event2023.day2.Day2

object Event2023 : AdventOfCodeEvent() {

    override val name: String by lazy {
        "2023"
    }

    override val days: List<AdventOfCodeDay> by lazy {
        listOf(
            Day1,
            Day2
        )
    }
}