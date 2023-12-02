package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day1/puzzle2" }

    override val result: String by lazy {
        //Regex to find digits and literal digits
        val regex = "[0-9]|one|two|three|four|five|six|seven|eight|nine".toRegex()

        //Mapping fun to convert literal digits to number digits
        fun String.toDigit(): String =
            when (this) {
                "one" -> "1"
                "two" -> "2"
                "three" -> "3"
                "four" -> "4"
                "five" -> "5"
                "six" -> "6"
                "seven" -> "7"
                "eight" -> "8"
                "nine" -> "9"
                else -> this
            }

        //Split the input into lines
        input.split('\n')
            //For each line calculate the calibration value
            .sumOf {
                //Apply the regex to find all the digits
                //You cannot use findAll like before because you need to handle mixed literal digits like "oneight"
                val results = mutableListOf<MatchResult>()
                var matchResult = regex.find(it)
                while (matchResult != null) {
                    results.add(matchResult)
                    matchResult = regex.find(it, matchResult.range.first + 1)
                }
                //Take the first and last digit to get the calibration value and add it
                (results.first().value.toDigit() + results.last().value.toDigit()).toInt()
            }.toString()
    }
}