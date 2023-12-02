package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day1/puzzle1" }

    override val result: String by lazy {
        //Regex to find digits
        val regex = "[0-9]".toRegex()

        //Split the input into lines
        input.split('\n')
            //For each line calculate the calibration value
            .sumOf {
                //Apply the regex to find all the digits
                val results = regex.findAll(it)
                //Take the first and last digit to get the calibration value and add it
                (results.first().value + results.last().value).toInt()
            }.toString()
    }
}