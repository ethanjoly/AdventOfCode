package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day1/puzzle1" }

    override val result: String by lazy {
        val regex = "[0-9]".toRegex()

        input.split('\n')
            .sumOf {
                regex.findAll(it).let { it.first().value + it.last().value }.toInt()
            }.toString()
    }
}