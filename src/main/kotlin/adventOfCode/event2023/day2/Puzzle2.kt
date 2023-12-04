package adventOfCode.event2023.day2

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day2/puzzle2" }

    override val result: String by lazy {
        val (maxRedRegex, maxGreenRegex, maxBlueRegex) = listOf("red", "green", "blue").map { "(\\d+) $it".toRegex() }

        input.split("\n").sumOf { game ->
            maxRedRegex.findAll(game).map { it.groupValues[1].toInt() }.max() *
                    maxGreenRegex.findAll(game).map { it.groupValues[1].toInt() }.max() *
                    maxBlueRegex.findAll(game).map { it.groupValues[1].toInt() }.max()
        }.toString()
    }
}