package adventOfCode.event2023.day2

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day2 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 2: Cube Conundrum"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}