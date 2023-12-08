package adventOfCode.event2023

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodeEvent
import adventOfCode.event2023.day1.Day1
import adventOfCode.event2023.day2.Day2
import adventOfCode.event2023.day3.Day3
import adventOfCode.event2023.day4.Day4
import adventOfCode.event2023.day5.Day5
import adventOfCode.event2023.day6.Day6
import adventOfCode.event2023.day7.Day7

object Event2023 : AdventOfCodeEvent() {

    override val name: String by lazy {
        "2023"
    }

    override val days: List<AdventOfCodeDay> by lazy {
        listOf(
            Day1,
            Day2,
            Day3,
            Day4,
            Day5,
            Day6,
            Day7
        )
    }
}