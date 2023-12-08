package adventOfCode.event2023.day7

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day7 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 7: Camel Cards"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}