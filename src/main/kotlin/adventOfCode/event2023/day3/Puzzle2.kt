package adventOfCode.event2023.day3

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day3/puzzle2" }

    override val result: String by lazy {
        //Regexs to find numbers
        var numberRegex = "\\d+".toRegex()
        //Regexs to find symbols
        var symbolRegex = "\\*".toRegex()

        //Split the input into lines
        val (numbers, symbols) = input.split("\n").mapIndexed { index, line ->
            //For each line find the numbers and symbols
            Pair(
                numberRegex.findAll(line).map { Triple(it.value.toInt(), index, it.range) },
                symbolRegex.findAll(line).map { Pair(index, it.range.first) }
            )
        }
            //Format the result to get a list of numbers and a list of symbols with their coordinates
            .unzip().let {
                Pair(
                    it.first.flatMap { it }.groupBy({ it.second }) { Pair(it.first, it.third) },
                    it.second.flatMap { it }
                )
            }

        //Keep only valid gears and then add them
        symbols.sumOf { symbol ->
            //Get the adjacent numbers in the previous, current and next lines
            IntRange(symbol.first - 1, symbol.first + 1).map {
                //A number is adjacent to a symbol if their line coordinates are adjacent
                numbers[it]?.filter { IntRange(it.second.first - 1, it.second.last + 1).contains(symbol.second) }
                    ?: emptyList()
            }.flatten()
                //If there are exactly 2 adjacent number it's a valid gear so calculate  its ratio
                .takeIf { it.size == 2 }?.let {
                    it.first().first * it.last().first
                } ?: 0
        }.toString()
    }
}