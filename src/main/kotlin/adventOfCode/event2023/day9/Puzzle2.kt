package adventOfCode.event2023.day9

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day9/puzzle2" }

    override val result: String by lazy {
        input.split("\n").sumOf {
            it.split(" ").map { it.toInt() }.toMutableList().let {
                val prediction = mutableListOf(it)
                var index = 0
                while (prediction[index].any { it != 0 }) {
                    val sequence = mutableListOf<Int>()
                    var previousValue: Int? = null
                    prediction[index].forEach { value ->
                        previousValue?.let {
                            sequence.add(value - it)
                        }
                        previousValue = value
                    }
                    prediction.add(sequence)
                    index++
                }
                prediction.size.downTo(0).forEach { sequenceIndex ->
                    prediction.getOrNull(sequenceIndex + 1)?.let {
                        prediction[sequenceIndex].add(0, prediction[sequenceIndex].first() - it.first())
                    }
                }
                prediction.first().first()
            }
        }.toString()
    }
}