package adventOfCode.event2023.day1

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val description: String by lazy {
        this.javaClass.getResource("/adventOfCode/event2023/day1/puzzle1/description.txt")?.readText() ?: ""
    }

    override val result: String by lazy {
        val input = this.javaClass.getResource("/adventOfCode/event2023/day1/input.txt")?.readText() ?: ""
        val regex = "[0-9]".toRegex()
        input.split('\n')
            .map {
                val results = regex.findAll(it)
                (results.first().value + results.last().value).toInt()
            }
            .sum().toString()
    }
}