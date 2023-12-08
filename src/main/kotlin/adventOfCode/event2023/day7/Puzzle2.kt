package adventOfCode.event2023.day7

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day7/puzzle2" }

    override val result: String by lazy {
        input.split("\n").map {
            it.split(" ").let {
                Triple(
                    it.first().map {
                        when (it) {
                            'A' -> 14
                            'K' -> 13
                            'Q' -> 12
                            'J' -> 1
                            'T' -> 10
                            else -> it.toString().toInt()
                        }
                    },
                    it.last(),
                    it.first().groupBy { it }.let {
                        it.filterKeys { it != 'J' }.values.map { it.count() }.sortedDescending().toMutableList()
                            .apply {
                                it.getOrDefault('J', emptyList()).count().let {
                                    if (this.isEmpty()) add(it) else set(0, get(0) + it)
                                }
                            }
                    }.let {
                        when (it[0]) {
                            5 -> 7
                            4 -> 6
                            3 -> if (it[1] == 2) 5 else 4
                            2 -> if (it[1] == 2) 3 else 2
                            else -> 1
                        }
                    })
            }
        }.sortedWith { o1, o2 ->
            (o1.third - o2.third).takeIf { it != 0 }
                ?: o1.first.zip(o2.first).map { it.first - it.second }.firstOrNull { it != 0 } ?: 0
        }.foldIndexed(0) { index, acc, value ->
            acc + (index + 1) * value.second.toInt()
        }.toString()

    }
}