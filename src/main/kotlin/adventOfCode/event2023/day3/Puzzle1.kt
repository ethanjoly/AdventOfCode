package adventOfCode.event2023.day3

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day3/puzzle1" }

    override val result: String by lazy {
        //Regexs to find numbers
        var numberRegex = "\\d+".toRegex()
        //Regexs to find symbols
        var symbolRegex = "[^\\d.]".toRegex()

        val (numbers, symbols) =
            //Split the input into lines
            input.split("\n").mapIndexed { index, line ->
                //For each line find the numbers and symbols
                Pair(
                    numberRegex.findAll(line).map { Triple(it.value.toInt(), index, it.range) },
                    symbolRegex.findAll(line).map { Pair(index, it.range.first) }
                )
            }
                //Format the result to get a list of numbers and a list of symbols with their coordinates
                .unzip()
                .let { Pair(it.first.flatMap { it }, it.second.flatMap { it }.groupBy({ it.first }) { it.second }) }

        //Keep only numbers that have an adjacent symbol
        numbers.filter { number ->
            //Get the previous, current and next lines
            IntRange(number.second - 1, number.second + 1).forEach {
                //A symbol is adjacent to a number if their line coordinates are adjacent
                symbols[it]?.find { IntRange(number.third.first - 1, number.third.last + 1).contains(it) }?.let {
                    return@filter true
                }
            }
            false
        }
            //Sum valid numbers
            .sumOf { it.first }.toString()
    }
}