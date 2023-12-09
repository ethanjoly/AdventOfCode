package adventOfCode.event2023.day8

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day8/puzzle1" }

    override val result: String by lazy {
        val nodeRegex = "([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)".toRegex()
        input.split("\n\n").let {
            var position = "AAA"
            var path = it.first().map {
                if (it == 'L') 0 else 1
            }
            val map = it.last().split("\n").mapNotNull {
                nodeRegex.find(it)?.groupValues?.drop(1)?.let {
                    Pair(it[0], it.drop(1))
                }
            }.toMap()
            var count = 0
            while (position != "ZZZ") {
                position = map.getValue(position)[path[count % path.size]]
                count++
            }
            count
        }.toString()
    }
}