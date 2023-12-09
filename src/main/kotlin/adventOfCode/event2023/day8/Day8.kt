package adventOfCode.event2023.day8

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day8 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 8: Haunted Wasteland"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}