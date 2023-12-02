package adventOfCode.event2023.day2

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day2/puzzle2" }

    override val result: String by lazy {
        //Regexs to find numbers of colored cubes
        val (maxRedRegex, maxGreenRegex, maxBlueRegex) = listOf("red", "green", "blue").map { "(\\d+) $it".toRegex() }

        //Split the input into lines to separate games
        input.split("\n").sumOf { game ->
            //Find the minimum cubes used in this game for each color
            val minRed = maxRedRegex.findAll(game).map { it.groupValues[1].toInt() }.max()
            val minGreen = maxGreenRegex.findAll(game).map { it.groupValues[1].toInt() }.max()
            val minBlue = maxBlueRegex.findAll(game).map { it.groupValues[1].toInt() }.max()

            //Calculate the power of the minimum set of cubes of this game and add it
            minRed * minGreen * minBlue
        }.toString()
    }
}