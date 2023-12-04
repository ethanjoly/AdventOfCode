package adventOfCode.event2023.day4

import adventOfCode.AdventOfCodePuzzle
import kotlin.math.pow

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day4/puzzle1" }

    override val result: String by lazy {
        val headerSeparatorRegex = ": +".toRegex()
        val spaceRegex = " +".toRegex()

        input.split("\n").sumOf {
            it.split(headerSeparatorRegex).last().split(" | ").let {
                it.first().split(spaceRegex).let { winningNumbers ->
                    it.last().split(spaceRegex).filter { winningNumbers.contains(it) }.size
                        .takeIf { it > 0 }?.let {
                            2.0.pow(it.toDouble() - 1).toInt()
                        } ?: 0
                }
            }
        }.toString()
    }
}