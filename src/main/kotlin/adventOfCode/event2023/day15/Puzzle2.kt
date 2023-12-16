package adventOfCode.event2023.day15

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day15/puzzle2" }

    override val result: String by lazy {
        val endRegex = "[=-]\\d?".toRegex()
        input.split(',').let {
            val boxs = MutableList<MutableList<Pair<String, Int>>>(256) { mutableListOf() }
            it.forEach { line ->
                line.replace(endRegex, "").let { label ->
                    val boxIndex = label.fold<Int>(0) { value, char -> (value + char.code) * 17 % 256 }
                    boxs[boxIndex].apply {
                        if (line[label.length] == '=') {
                            val focalLength = line[label.length + 1].toString().toInt()
                            find { it.first == label }?.let {
                                set(indexOf(it), Pair(label, focalLength))
                            } ?: run {
                                add(Pair(label, focalLength))
                            }
                        } else {
                            find { it.first == label }?.let {
                                remove(it)
                            }
                        }
                    }
                }
            }
            boxs.mapIndexed { boxIndex, box ->
                box.mapIndexed { index, pair ->
                    (boxIndex + 1) * (index + 1) * pair.second
                }.sum()
            }.sum()
        }.toString()
    }
}