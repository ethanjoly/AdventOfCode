package adventOfCode.event2023.day14

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day14/puzzle1" }

    override val result: String by lazy {
        input.split("\n").map { it.toMutableList() }.toMutableList().apply {
            (0 until size).forEach { row ->
                (0 until first().size).forEach { col ->
                    if (this[row][col] == 'O') {
                        var position = row
                        while (position > 0 && this[position - 1][col] == '.') {
                            val tmp = this[position][col]
                            this[position][col] = this[position - 1][col]
                            this[position - 1][col] = tmp
                            position--
                        }
                    }
                }
            }
        }.reversed().mapIndexed { index, chars -> chars.count { it == 'O' } * (index + 1) }.sum().toString()
    }
}