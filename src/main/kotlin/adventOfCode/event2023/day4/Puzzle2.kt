package adventOfCode.event2023.day4

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day4/puzzle2" }

    override val result: String by lazy {
        val headerSeparatorRegex = ": +".toRegex()
        val spaceRegex = " +".toRegex()

        val copiesPile = mutableListOf<Int>()
        input.split("\n").sumOf {
            it.split(headerSeparatorRegex).last().split(" | ").let {
                (copiesPile.getOrNull(0)?.apply {
                    copiesPile.removeAt(0)
                } ?: 1).let { copies ->
                    it.first().split(spaceRegex).let { winningNumbers ->
                        it.last().split(spaceRegex).filter { winningNumbers.contains(it) }.size
                            .takeIf { it > 0 }?.let { wins ->
                                (0 until wins).forEach { index ->
                                    copiesPile.getOrNull(index)?.let {
                                        copiesPile[index] = it + 1 * copies
                                    } ?: run {
                                        copiesPile.add(1 + copies)
                                    }
                                }
                            }
                    }
                    copies
                }
            }
        }.toString()
    }
}