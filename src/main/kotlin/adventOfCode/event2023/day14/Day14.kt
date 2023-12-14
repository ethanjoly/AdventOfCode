package adventOfCode.event2023.day14

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day14 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 14: Parabolic Reflector Dish"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}