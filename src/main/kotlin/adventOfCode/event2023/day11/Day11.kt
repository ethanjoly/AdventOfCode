package adventOfCode.event2023.day11

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day11 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 11: Cosmic Expansion"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}