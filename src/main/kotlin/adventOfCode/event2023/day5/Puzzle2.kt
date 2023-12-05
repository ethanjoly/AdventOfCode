package adventOfCode.event2023.day5

import adventOfCode.AdventOfCodePuzzle
import java.lang.Long.max

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day5/puzzle2" }

    override val result: String by lazy {
        input.split("\n\n").let {
            var values = it.first().split(" ").drop(1).map { it.toLong() }.chunked(2)
                .map { LongRange(it.first(), it.first() + it.last() - 1) }
            it.drop(1).forEach { mapText ->
                mapText.split("\n").drop(1).map { entry ->
                    entry.split(" ").map { it.toLong() }.let {
                        Pair(LongRange(it[0], it[0] + it[2] - 1), LongRange(it[1], it[1] + it[2] - 1))
                    }
                }.sortedBy { it.second.first }.let { map ->
                    values = values.map { value ->
                        var start = value.first
                        val newValue = mutableListOf<LongRange>()
                        map.forEach {
                            if (start <= it.second.last && value.last >= it.second.first) {
                                if (start < it.second.first) {
                                    newValue.add(
                                        LongRange(
                                            start,
                                            it.second.first - 1
                                        )
                                    )
                                    start = it.second.first
                                }
                                val endOffset = max(0, it.second.last - value.last)
                                newValue.add(
                                    LongRange(
                                        it.first.first + start - it.second.first,
                                        it.first.last - endOffset
                                    )
                                )
                                start = it.second.last - endOffset + 1
                            }
                        }
                        if (start <= value.last) {
                            newValue.add(LongRange(start, value.last))
                        }
                        newValue
                    }.flatten()
                }
            }
            values.map { it.first }.min()
        }.toString()
    }
}