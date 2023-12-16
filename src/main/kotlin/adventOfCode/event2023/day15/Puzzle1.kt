package adventOfCode.event2023.day15

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day15/puzzle1" }

    override val result: String by lazy {
        input.split(',').sumOf {
            it.fold<Int>(0) { value, char -> (value + char.code) * 17 % 256 }
        }.toString()
    }
}