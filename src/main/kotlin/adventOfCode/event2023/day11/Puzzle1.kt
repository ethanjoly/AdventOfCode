package adventOfCode.event2023.day11

import adventOfCode.AdventOfCodePuzzle
import kotlin.math.absoluteValue

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day11/puzzle1" }

    override val result: String by lazy {
        input.split("\n").map { it.map { it }.toMutableList() }.toMutableList().let { map ->
            var offset = 0
            map.size.minus(1).downTo(0).reversed().forEach { index ->
                val row = map.get(index + offset)
                if (row.all { it == '.' }) {
                    map.add(index + offset, MutableList(row.size) { '.' })
                    offset++
                }
            }
            offset = 0
            map.first().size.minus(1).downTo(0).reversed().forEach { index ->
                if (map.all { it.get(index + offset) == '.' }) {
                    map.forEach {
                        it.add(index + offset, '.')
                    }
                    offset++
                }
            }
            map.mapIndexedNotNull { row, chars ->
                chars.mapIndexedNotNull { col, c ->
                    if (c == '#') Pair(row, col) else null
                }
            }.flatten().let { galaxies ->
                val remainingGalaxies = mutableListOf<Pair<Int, Int>>().apply {
                    addAll(galaxies)
                }
                galaxies.sumOf { g1 ->
                    remainingGalaxies.remove(g1)
                    remainingGalaxies.sumOf { g2 ->
                        (g2.first - g1.first).absoluteValue + (g2.second - g1.second).absoluteValue
                    }
                }
            }
        }.toString()
    }
}