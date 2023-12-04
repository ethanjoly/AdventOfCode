package adventOfCode.event2023.day3

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day3/puzzle2" }

    override val result: String by lazy {
        var numberRegex = "\\d+".toRegex()
        var symbolRegex = "\\*".toRegex()

        val (numbers, symbols) = input.split("\n").mapIndexed { index, line ->
            Pair(
                numberRegex.findAll(line).map { Triple(it.value.toInt(), index, it.range) },
                symbolRegex.findAll(line).map { Pair(index, it.range.first) }
            )
        }.unzip().let {
            Pair(
                it.first.flatMap { it }.groupBy({ it.second }) { Pair(it.first, it.third) },
                it.second.flatMap { it }
            )
        }

        symbols.sumOf { symbol ->
            IntRange(symbol.first - 1, symbol.first + 1).map {
                numbers[it]?.filter { IntRange(it.second.first - 1, it.second.last + 1).contains(symbol.second) }
                    ?: emptyList()
            }.flatten().takeIf { it.size == 2 }?.let { it.first().first * it.last().first } ?: 0
        }.toString()
    }
}