package adventOfCode.event2023.day9

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day9 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 9: Mirage Maintenance"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}