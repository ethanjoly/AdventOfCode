package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val description: String by lazy {
        this.javaClass.getResource("/adventOfCode/event2023/day1/puzzle2/description.txt")?.readText() ?: ""
    }

    private fun String.toDigit(): String =
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

    override val result: String by lazy {
        val input = this.javaClass.getResource("/adventOfCode/event2023/day1/input.txt")?.readText() ?: ""
        val regex = "[0-9]|one|two|three|four|five|six|seven|eight|nine".toRegex()
        input.split('\n').sumOf {
            val results = mutableListOf<MatchResult>()
            var matchResult = regex.find(it)
            while (matchResult != null) {
                results.add(matchResult)
                matchResult = regex.find(it, matchResult.range.first + 1)
            }
            (results.first().value.toDigit() + results.last().value.toDigit()).toInt()
        }.toString()
    }
}