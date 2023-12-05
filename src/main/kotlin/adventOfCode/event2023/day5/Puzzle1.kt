package adventOfCode.event2023.day5

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day5/puzzle1" }

    override val result: String by lazy {
        input.split("\n\n").let {
            var values = it.first().split(" ").drop(1).map { it.toLong() }
            it.drop(1).forEach { mapText ->
                mapText.split("\n").drop(1).map { entry ->
                    entry.split(" ").map { it.toLong() }.let {
                        Pair(LongRange(it[0], it[0] + it[2] - 1), LongRange(it[1], it[1] + it[2] - 1))
                    }
                }.let { map ->
                    values = values.map { value ->
                        map.find {
                            it.second.contains(value)
                        }?.let {
                            it.first.first + value - it.second.first
                        } ?: value
                    }
                }
            }
            values.min()
        }.toString()
    }
}