package adventOfCode.event2023.day4

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day4 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 4: Scratchcards"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}