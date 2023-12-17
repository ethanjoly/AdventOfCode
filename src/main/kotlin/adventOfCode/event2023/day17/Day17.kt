package adventOfCode.event2023.day17

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day17 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 17: Clumsy Crucible"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}