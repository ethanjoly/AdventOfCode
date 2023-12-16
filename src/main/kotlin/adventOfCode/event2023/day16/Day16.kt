package adventOfCode.event2023.day16

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day16 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 16: The Floor Will Be Lava"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}