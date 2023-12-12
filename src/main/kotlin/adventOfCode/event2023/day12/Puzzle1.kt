package adventOfCode.event2023.day12

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day12/puzzle1" }

    override val result: String by lazy {
        val notDamagedRegex = "[^#]+".toRegex()
        fun springsPossibilities(springs: String, pattern: List<Int>, springPossibility: String = ""): Int {
            if (springs.isEmpty()) return if (
                springPossibility.split(notDamagedRegex).mapNotNull { it.length.takeIf { it > 0 } } == pattern
            ) 1 else 0
            if (springs.first() == '?')
                return springsPossibilities(springs.drop(1), pattern, "$springPossibility.") +
                        springsPossibilities(springs.drop(1), pattern, "$springPossibility#")
            return springsPossibilities(springs.drop(1), pattern, springPossibility + springs.first())
        }
        input.split("\n").sumOf {
            it.split(' ').let {
                springsPossibilities(it.first(), it.last().split(',').map { it.toInt() })
            }
        }.toString()
    }
}