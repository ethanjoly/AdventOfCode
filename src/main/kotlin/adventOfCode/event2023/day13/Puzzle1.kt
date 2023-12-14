package adventOfCode.event2023.day13

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day13/puzzle1" }

    override val result: String by lazy {
        fun List<List<Char>>.findReflexion(): Int {
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
                    return position + 1
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
                    return (position + 1) * 100
                }
                position++
            }
            return 0
        }
        input.split("\n\n").sumOf {
            it.split('\n').map {
                it.toList()
            }.let { map ->
                map.findReflexion()
            }
        }.toString()
    }
}