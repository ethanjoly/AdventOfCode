package adventOfCode.event2023.day2

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day2/puzzle1" }

    override val result: String by lazy {
        val (redThreshold, greenThreshold, blueThreshold) = listOf(12, 13, 14)
        val (maxRedRegex, maxGreenRegex, maxBlueRegex) = listOf("red", "green", "blue").map { "(\\d+) $it".toRegex() }
        val gameIdRegex = "Game (\\d+):".toRegex()

        input.split("\n").sumOf { game ->
            if (
                maxRedRegex.findAll(game).map { it.groupValues[1].toInt() }.max() > redThreshold ||
                maxGreenRegex.findAll(game).map { it.groupValues[1].toInt() }.max() > greenThreshold ||
                maxBlueRegex.findAll(game).map { it.groupValues[1].toInt() }.max() > blueThreshold
            ) 0
            else gameIdRegex.find(game)?.groupValues?.get(1)?.toInt() ?: 0
        }.toString()
    }
}