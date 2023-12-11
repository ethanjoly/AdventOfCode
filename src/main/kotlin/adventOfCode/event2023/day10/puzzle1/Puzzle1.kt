package adventOfCode.event2023.day10.puzzle1

import adventOfCode.AdventOfCodePuzzle

//-start-
private sealed class Tile(val row: Int, val col: Int) {
    val linkedTiles = mutableListOf<Tile>()
    var stepsFromStart = Int.MAX_VALUE
    abstract fun setConnectedTiles(map: List<List<Tile>>)
    class Vertical(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row - 1][col]))
        }
    }

    class Horizontal(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row][col - 1], map[row][col + 1]))
        }
    }

    class NorthEast(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row - 1][col], map[row][col + 1]))
        }
    }

    class NorthWest(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row - 1][col], map[row][col - 1]))
        }
    }

    class SouthWest(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row][col - 1]))
        }
    }

    class SouthEast(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row][col + 1]))
        }
    }

    class Ground(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {}
    }

    class Start(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            map.getOrNull(row - 1)?.getOrNull(col)?.let {
                if (it is Vertical || it is SouthEast || it is SouthWest) linkedTiles.add(it)
            }
            map.getOrNull(row + 1)?.getOrNull(col)?.let {
                if (it is Vertical || it is NorthEast || it is NorthWest) linkedTiles.add(it)
            }
            map.getOrNull(row)?.getOrNull(col - 1)?.let {
                if (it is Horizontal || it is NorthEast || it is SouthEast) linkedTiles.add(it)
            }
            map.getOrNull(row)?.getOrNull(col + 1)?.let {
                if (it is Horizontal || it is NorthWest || it is SouthWest) linkedTiles.add(it)
            }
        }
    }

    companion object {
        fun from(char: Char, row: Int, col: Int) = when (char) {
            '|' -> Vertical(row, col)
            '-' -> Horizontal(row, col)
            'L' -> NorthEast(row, col)
            'J' -> NorthWest(row, col)
            '7' -> SouthWest(row, col)
            'F' -> SouthEast(row, col)
            'S' -> Start(row, col)
            else -> Ground(row, col)
        }
    }
}
//-end-

object Puzzle1 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day10/puzzle1" }

    override val result: String by lazy {
        lateinit var start: Tile.Start
        val map = input.split("\n").mapIndexed { row, string ->
            string.mapIndexed { col, char ->
                Tile.from(char, row, col).apply {
                    if (this is Tile.Start) {
                        start = this
                    }
                }
            }
        }
        val loopTiles = mutableListOf<Tile>(start)
        start.setConnectedTiles(map)
        start.stepsFromStart = 0
        var currentTile: Tile = start.linkedTiles.first()
        var previousTile: Tile = start
        var cpt = 1
        while (currentTile != start) {
            loopTiles.add(currentTile)
            currentTile.setConnectedTiles(map)
            if (cpt < currentTile.stepsFromStart) currentTile.stepsFromStart = cpt
            cpt++
            currentTile.linkedTiles.find { it != previousTile }?.let {
                previousTile = currentTile
                currentTile = it
            }
        }
        currentTile = start.linkedTiles.last()
        previousTile = start
        cpt = 1
        while (currentTile != start) {
            if (cpt < currentTile.stepsFromStart) currentTile.stepsFromStart = cpt
            cpt++
            currentTile.linkedTiles.find { it != previousTile }?.let {
                previousTile = currentTile
                currentTile = it
            }
        }
        loopTiles.maxOf { it.stepsFromStart }.toString()
    }
}