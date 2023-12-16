package adventOfCode.event2023.day15

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day15 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 15: Lens Library"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}