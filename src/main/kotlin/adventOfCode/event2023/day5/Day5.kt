package adventOfCode.event2023.day5

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day5 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 5: If You Give A Seed A Fertilizer"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}