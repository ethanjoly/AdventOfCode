package adventOfCode.event2023.day12

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day12 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 12: Hot Springs"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}