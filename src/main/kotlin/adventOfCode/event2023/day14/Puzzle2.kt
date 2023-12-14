package adventOfCode.event2023.day14

import adventOfCode.AdventOfCodePuzzle

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day14/puzzle2" }

    override val result: String by lazy {
        input.split("\n").map { it.toMutableList() }.toMutableList().let { grid ->
            var steps: MutableList<List<List<Char>>> = mutableListOf()
            var repeatedStep: List<List<Char>>? = null
            while (repeatedStep == null) {
                (0 until grid.size).forEach { row ->
                    (0 until grid.first().size).forEach { col ->
                        if (grid[row][col] == 'O') {
                            var position = row
                            while (position > 0 && grid[position - 1][col] == '.') {
                                val tmp = grid[position][col]
                                grid[position][col] = grid[position - 1][col]
                                grid[position - 1][col] = tmp
                                position--
                            }
                        }
                    }
                }
                (0 until grid.first().size).forEach { col ->
                    (0 until grid.size).forEach { row ->
                        if (grid[row][col] == 'O') {
                            var position = col
                            while (position > 0 && grid[row][position - 1] == '.') {
                                val tmp = grid[row][position]
                                grid[row][position] = grid[row][position - 1]
                                grid[row][position - 1] = tmp
                                position--
                            }
                        }
                    }
                }
                (0 until grid.size).reversed().forEach { row ->
                    (0 until grid.first().size).forEach { col ->
                        if (grid[row][col] == 'O') {
                            var position = row
                            while (position < grid.size - 1 && grid[position + 1][col] == '.') {
                                val tmp = grid[position][col]
                                grid[position][col] = grid[position + 1][col]
                                grid[position + 1][col] = tmp
                                position++
                            }
                        }
                    }
                }
                (0 until grid.first().size).reversed().forEach { col ->
                    (0 until grid.size).forEach { row ->
                        if (grid[row][col] == 'O') {
                            var position = col
                            while (position < grid.first().size - 1 && grid[row][position + 1] == '.') {
                                val tmp = grid[row][position]
                                grid[row][position] = grid[row][position + 1]
                                grid[row][position + 1] = tmp
                                position++
                            }
                        }
                    }
                }
                repeatedStep = steps.find { grid == it }
                if (repeatedStep == null) {
                    steps.add(grid.map { it.toMutableList() }.toMutableList())
                }
            }
            val indexOfRepeatedStep = steps.indexOf(repeatedStep)
            steps[indexOfRepeatedStep - 1 + ((1000000000 - indexOfRepeatedStep) % (steps.size - indexOfRepeatedStep))]
        }.reversed().mapIndexed { index, chars -> chars.count { it == 'O' } * (index + 1) }.sum().toString()
    }
}