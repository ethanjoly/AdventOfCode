package adventOfCode.event2023.day16

import adventOfCode.AdventOfCodePuzzle

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day16/puzzle1" }

    override val result: String by lazy {
        input.split("\n").map { it.toList() }.let { grid ->
            val energyMap = MutableList(grid.size) {
                MutableList(grid.first().size) { false }
            }
            val steps = MutableList(grid.size) {
                MutableList(grid.first().size) { mutableListOf<Char>() }
            }

            fun moveBeam(position: Pair<Int, Int>, direction: Char) {
                grid.getOrNull(position.first)?.getOrNull(position.second)?.let { case ->
                    val stepsForPosition = steps[position.first][position.second]
                    if (!stepsForPosition.contains(direction)) {
                        stepsForPosition.add(direction)
                        energyMap[position.first][position.second] = true
                        when (case) {
                            '.' -> {
                                when (direction) {
                                    'n' -> moveBeam(position.copy(first = position.first - 1), direction)
                                    's' -> moveBeam(position.copy(first = position.first + 1), direction)
                                    'e' -> moveBeam(position.copy(second = position.second + 1), direction)
                                    'o' -> moveBeam(position.copy(second = position.second - 1), direction)
                                }
                            }

                            '/' -> {
                                when (direction) {
                                    'n' -> moveBeam(position.copy(second = position.second + 1), 'e')
                                    's' -> moveBeam(position.copy(second = position.second - 1), 'o')
                                    'e' -> moveBeam(position.copy(first = position.first - 1), 'n')
                                    'o' -> moveBeam(position.copy(first = position.first + 1), 's')
                                }
                            }

                            '\\' -> {
                                when (direction) {
                                    'n' -> moveBeam(position.copy(second = position.second - 1), 'o')
                                    's' -> moveBeam(position.copy(second = position.second + 1), 'e')
                                    'e' -> moveBeam(position.copy(first = position.first + 1), 's')
                                    'o' -> moveBeam(position.copy(first = position.first - 1), 'n')
                                }
                            }

                            '|' -> {
                                when (direction) {
                                    'n' -> moveBeam(position.copy(first = position.first - 1), direction)
                                    's' -> moveBeam(position.copy(first = position.first + 1), direction)
                                    'e', 'o' -> {
                                        moveBeam(position.copy(first = position.first - 1), 'n')
                                        moveBeam(position.copy(first = position.first + 1), 's')
                                    }
                                }
                            }

                            '-' -> {
                                when (direction) {

                                    'n', 's' -> {
                                        moveBeam(position.copy(second = position.second + 1), 'e')
                                        moveBeam(position.copy(second = position.second - 1), 'o')
                                    }

                                    'e' -> moveBeam(position.copy(second = position.second + 1), direction)
                                    'o' -> moveBeam(position.copy(second = position.second - 1), direction)
                                }
                            }
                        }
                    }

                }
            }
            moveBeam(Pair(0, 0), 'e')
            energyMap.sumOf {
                it.count { it }
            }
        }.toString()
    }
}