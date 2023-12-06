package adventOfCode.event2023.day6

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day6/puzzle1" }

    override val result: String by lazy {
        val spaceRegex = " +".toRegex()
        input.split("\n").map { it.split(spaceRegex).drop(1).map { it.toInt() } }
            .let { it.first().zip(it.last()) }
            .fold(1) { accumulator, value ->
                accumulator * value.first.downTo(0).count { time -> time * (value.first - time) > value.second }
            }.toString()
    }
}