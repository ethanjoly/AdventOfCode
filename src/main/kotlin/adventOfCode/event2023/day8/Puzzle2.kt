package adventOfCode.event2023.day8

import adventOfCode.AdventOfCodePuzzle
import kotlin.math.absoluteValue

object Puzzle2 : AdventOfCodePuzzle() {

    override val basePath: String by lazy { "/adventOfCode/event2023/day8/puzzle2" }

    override val result: String by lazy {
        fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

        fun lcm(a: Long, b: Long) = (a * b).absoluteValue / gcd(a, b)

        val nodeRegex = "(\\w{3}) = \\((\\w{3}), (\\w{3})\\)".toRegex()

        input.split("\n\n").let {
            var path = it.first().map {
                if (it == 'L') 0 else 1
            }
            val map = it.last().split("\n").mapNotNull {
                nodeRegex.find(it)?.groupValues?.drop(1)?.let {
                    Pair(it[0], it.drop(1))
                }
            }.toMap()
            map.keys.filter { it.last() == 'A' }.map {
                var count = 0L
                var position = it
                while (position.last() != 'Z') {
                    position = map.getValue(position)[path[(count % path.size).toInt()]]
                    count++
                }

                count
            }.reduce { acc, i -> lcm(acc, i) }
        }.toString()
    }
}