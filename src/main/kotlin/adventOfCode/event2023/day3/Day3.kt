package adventOfCode.event2023.day3

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day3 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 3: Gear Ratios"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}