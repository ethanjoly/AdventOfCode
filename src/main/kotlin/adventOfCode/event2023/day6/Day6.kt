package adventOfCode.event2023.day6

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day6 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 6: Wait For It"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}