package adventOfCode.event2023.day6

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day6/puzzle2" }

    override val result: String by lazy {
        val nonDecimalRegex = "\\D".toRegex()
        input.split("\n").map { it.replace(nonDecimalRegex, "") }.map { it.toLong() }
            .let {
                it.first().downTo(0).count { time -> time * (it.first() - time) > it.last() }
            }.toString()
    }
}