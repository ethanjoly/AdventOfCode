package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day1 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 1: Trebuchet?!"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}