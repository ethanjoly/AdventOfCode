package adventOfCode.event2023.day2

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day2/puzzle1" }

    override val result: String by lazy {
        //Define the color thresholds
        val (redThreshold, greenThreshold, blueThreshold) = listOf(12, 13, 14)

        //Regexs to find numbers of colored cubes
        val (maxRedRegex, maxGreenRegex, maxBlueRegex) = listOf("red", "green", "blue").map { "(\\d+) $it".toRegex() }
        //Regex to find game ID
        val gameIdRegex = "Game (\\d+):".toRegex()

        //Split the input into lines to separate games
        input.split("\n").sumOf { game ->
            //Find the minimum cubes used in this game for each color
            val minRed = maxRedRegex.findAll(game).map { it.groupValues[1].toInt() }.max()
            val minGreen = maxGreenRegex.findAll(game).map { it.groupValues[1].toInt() }.max()
            val minBlue = maxBlueRegex.findAll(game).map { it.groupValues[1].toInt() }.max()

            //Compare the minimum cubes used for each color, If the game is possible add the ID
            //If one exceed the relative threshold the game is impossible
            if (minRed> redThreshold || minGreen>greenThreshold || minBlue>blueThreshold) 0
            //If the game is possible find the game ID and add it
            else gameIdRegex.find(game)?.groupValues?.get(1)?.toInt()?:0
        }.toString()
    }
}