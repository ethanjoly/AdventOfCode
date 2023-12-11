package adventOfCode.event2023.day10.puzzle2

import adventOfCode.AdventOfCodePuzzle

//-start-
private sealed class Tile(val row: Int, val col: Int) {
    val linkedTiles = mutableListOf<Tile>()
    abstract fun setConnectedTiles(map: List<List<Tile>>)

    abstract val type: Type

    enum class Type {
        VERTICAL,
        HORIZONTAL,
        NORTH_EAST,
        NORTH_WEST,
        SOUTH_WEST,
        SOUTH_EAST,
        GROUND
    }

    enum class Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    class Vertical(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row - 1][col]))
        }

        override val type: Type = Type.VERTICAL
    }

    class Horizontal(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row][col - 1], map[row][col + 1]))
        }

        override val type: Type = Type.HORIZONTAL
    }

    class NorthEast(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row - 1][col], map[row][col + 1]))
        }

        override val type: Type = Type.NORTH_EAST
    }

    class NorthWest(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row - 1][col], map[row][col - 1]))
        }

        override val type: Type = Type.NORTH_WEST
    }

    class SouthWest(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row][col - 1]))
        }

        override val type: Type = Type.SOUTH_WEST
    }

    class SouthEast(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {
            linkedTiles.addAll(listOf(map[row + 1][col], map[row][col + 1]))
        }

        override val type: Type = Type.SOUTH_EAST
    }

    class Ground(row: Int, col: Int) : Tile(row, col) {
        override fun setConnectedTiles(map: List<List<Tile>>) {}
        override val type: Type = Type.GROUND
    }

    class Start(row: Int, col: Int) : Tile(row, col) {
        private var _type: Type = Type.GROUND

        override val type: Type
            get() = _type

        override fun setConnectedTiles(map: List<List<Tile>>) {
            val orientations = mutableListOf<Direction>()
            map.getOrNull(row - 1)?.getOrNull(col)?.let {
                if (it is Vertical || it is SouthEast || it is SouthWest) {
                    orientations.add(Direction.NORTH)
                    linkedTiles.add(it)
                }
            }
            map.getOrNull(row + 1)?.getOrNull(col)?.let {
                if (it is Vertical || it is NorthEast || it is NorthWest) {
                    orientations.add(Direction.SOUTH)
                    linkedTiles.add(it)
                }
            }
            map.getOrNull(row)?.getOrNull(col - 1)?.let {
                if (it is Horizontal || it is NorthEast || it is SouthEast) {
                    orientations.add(Direction.WEST)
                    linkedTiles.add(it)
                }
            }
            map.getOrNull(row)?.getOrNull(col + 1)?.let {
                if (it is Horizontal || it is NorthWest || it is SouthWest) {
                    orientations.add(Direction.EAST)
                    linkedTiles.add(it)
                }
            }
            _type = if (orientations.contains(Direction.NORTH)) {
                if (orientations.contains(Direction.SOUTH)) Type.VERTICAL
                else if (orientations.contains(Direction.EAST)) Type.NORTH_EAST
                else if (orientations.contains(Direction.WEST)) Type.NORTH_WEST
                else Type.GROUND
            } else if (orientations.contains(Direction.SOUTH)) {
                if (orientations.contains(Direction.EAST)) Type.SOUTH_EAST
                else if (orientations.contains(Direction.WEST)) Type.SOUTH_WEST
                else Type.GROUND
            } else if (orientations.contains(Direction.EAST) && orientations.contains(Direction.WEST))
                Type.HORIZONTAL
            else Type.GROUND
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

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day10/puzzle2" }

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
        var currentTile: Tile = start.linkedTiles.first()
        var previousTile: Tile = start
        while (currentTile != start) {
            loopTiles.add(currentTile)
            currentTile.setConnectedTiles(map)
            currentTile.linkedTiles.find { it != previousTile }?.let {
                previousTile = currentTile
                currentTile = it
            }
        }
        var cpt = 0
        map.forEach {
            it.forEach { tile ->
                if (!loopTiles.contains(tile)) {
                    var intersections = 0
                    var buffer: Tile.Direction? = null
                    (tile.row - 1).downTo(0).forEach {
                        val tileToCheck = map.get(it).get(tile.col)
                        if (loopTiles.contains(tileToCheck)) {
                            when (tileToCheck.type) {
                                Tile.Type.HORIZONTAL -> {
                                    intersections++
                                }

                                Tile.Type.NORTH_EAST -> {
                                    buffer = Tile.Direction.EAST
                                }

                                Tile.Type.NORTH_WEST -> {
                                    buffer = Tile.Direction.WEST
                                }

                                Tile.Type.SOUTH_WEST -> {
                                    if (buffer == Tile.Direction.EAST) {
                                        intersections++
                                    }
                                    buffer = null
                                }

                                Tile.Type.SOUTH_EAST -> {
                                    if (buffer == Tile.Direction.WEST) {
                                        intersections++
                                    }
                                    buffer = null
                                }

                                else -> {}
                            }
                        }
                    }
                    if (intersections % 2 == 1) {
                        cpt++
                    }

                }
            }
        }
        cpt.toString()
    }
}