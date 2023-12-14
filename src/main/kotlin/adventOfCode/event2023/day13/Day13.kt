package adventOfCode.event2023.day13

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day13 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 13: Point of Incidence"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}