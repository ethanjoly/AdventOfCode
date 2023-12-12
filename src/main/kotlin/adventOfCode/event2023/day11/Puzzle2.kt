package adventOfCode.event2023.day11

import adventOfCode.AdventOfCodePuzzle
import kotlin.math.absoluteValue

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day11/puzzle2" }

    override val result: String by lazy {
        input.split("\n").map { it.map { it }.toMutableList() }.toMutableList().let { map ->
            val emptyRows = mutableListOf<Int>()
            map.size.minus(1).downTo(0).reversed().forEach { index ->
                if (map.get(index).all { it == '.' }) {
                    emptyRows.add(index)
                }
            }

            val emptyCols = mutableListOf<Int>()
            map.first().size.minus(1).downTo(0).reversed().forEach { index ->
                if (map.all { it.get(index) == '.' }) {
                    emptyCols.add(index)
                }
            }
            var rowOffset = 0L
            map.mapIndexedNotNull { row, chars ->
                if (emptyRows.contains(row)) rowOffset += 999999L
                var colOffset = 0L
                chars.mapIndexedNotNull { col, c ->
                    if (emptyCols.contains(col)) colOffset += 999999L
                    if (c == '#') Pair(row + rowOffset, col + colOffset) else null
                }
            }.flatten().let { galaxies ->
                val remainingGalaxies = mutableListOf<Pair<Long, Long>>().apply {
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