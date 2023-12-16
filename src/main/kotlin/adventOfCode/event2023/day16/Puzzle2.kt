package adventOfCode.event2023.day16

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day16/puzzle2" }

    override val result: String by lazy {
        input.split("\n").map { it.toList() }.let { grid ->
            val possibilities = mutableListOf<Pair<Pair<Int, Int>, Char>>()
            (0 until grid.size).forEach { row ->
                possibilities.add(Pair(Pair(row, 0), 'e'))
                possibilities.add(Pair(Pair(row, grid.first().size - 1), 'o'))
            }
            (0 until grid.first().size).forEach { col ->
                possibilities.add(Pair(Pair(0, col), 's'))
                possibilities.add(Pair(Pair(grid.size - 1, col), 'n'))
            }

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
            possibilities.map {
                energyMap.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { colIndex, _ ->
                        energyMap[rowIndex][colIndex] = false
                    }
                }
                steps.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { colIndex, _ ->
                        steps[rowIndex][colIndex] = mutableListOf()
                    }
                }
                moveBeam(it.first, it.second)
                energyMap.sumOf {
                    it.count { it }
                }
            }.max()
        }.toString()
    }
}