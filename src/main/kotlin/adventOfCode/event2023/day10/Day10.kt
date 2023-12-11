package adventOfCode.event2023.day10

import adventOfCode.AdventOfCodeDay
import adventOfCode.AdventOfCodePuzzle

object Day10 : AdventOfCodeDay() {

    override val name: String by lazy {
        "Day 10: Pipe Maze"
    }

    override val puzzles: List<AdventOfCodePuzzle> by lazy {
        listOf(
            Puzzle1,
            Puzzle2
        )
    }
}