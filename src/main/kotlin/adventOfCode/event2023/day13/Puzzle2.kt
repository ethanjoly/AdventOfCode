package adventOfCode.event2023.day13

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day13/puzzle2" }

    override val result: String by lazy {
        fun List<List<Char>>.findReflexion(excludeValue: Int? = null): Int? {
            var position = 0
            while (this.first().size - 1 > position) {
                if ((0..position).all { offset ->
                        val firstColumn =
                            this.mapNotNull { it.getOrNull(position + offset + 1) }
                                .takeIf { it.isNotEmpty() }
                        val secondColumn =
                            this.mapNotNull { it.getOrNull(position - offset) }
                                .takeIf { it.isNotEmpty() }
                        firstColumn == null || secondColumn == null || firstColumn == secondColumn
                    }) {
                    (position + 1).takeIf { it != excludeValue }?.let { return it }
                }
                position++
            }
            position = 0
            while (this.size - 1 > position) {
                if ((0..position).all { offset ->
                        val firstRow = this.getOrNull(position + offset + 1)
                        val secondRow = this.getOrNull(position - offset)
                        firstRow == null || secondRow == null || firstRow == secondRow
                    }) {
                    ((position + 1) * 100).takeIf { it != excludeValue }?.let { return it }
                }
                position++
            }
            return null
        }

        input.split("\n\n").sumOf {
            it.split('\n').map {
                it.toList()
            }.let { map ->
                var res: Int? = null
                var smudgeRow = 0
                val exludedValue = map.findReflexion()

                println()
                while (res == null && smudgeRow < map.size) {
                    var smudgeCol = 0
                    while (res == null && smudgeCol < map.first().size) {
                        res = map.mapIndexed { row, chars ->
                            chars.mapIndexed { col, c ->
                                if (smudgeRow == row && smudgeCol == col) {
                                    if (c == '.') '#' else '.'
                                } else c
                            }
                        }.findReflexion(exludedValue)
                        smudgeCol++
                    }
                    smudgeRow++
                }
                res ?: 0
            }
        }.toString()
    }
}