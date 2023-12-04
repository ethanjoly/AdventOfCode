package adventOfCode.event2023.day3

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day3/puzzle1" }

    override val result: String by lazy {
        var numberRegex = "\\d+".toRegex()
        var symbolRegex = "[^\\d.]".toRegex()

        val (numbers, symbols) =
            input.split("\n").mapIndexed { index, line ->
                Pair(
                    numberRegex.findAll(line).map { Triple(it.value.toInt(), index, it.range) },
                    symbolRegex.findAll(line).map { Pair(index, it.range.first) }
                )
            }.unzip()
                .let { Pair(it.first.flatMap { it }, it.second.flatMap { it }.groupBy({ it.first }) { it.second }) }

        numbers.filter { number ->
            IntRange(number.second - 1, number.second + 1).forEach {
                symbols[it]?.find { IntRange(number.third.first - 1, number.third.last + 1).contains(it) }?.let {
                    return@filter true
                }
            }
            false
        }.sumOf { it.first }.toString()
    }
}