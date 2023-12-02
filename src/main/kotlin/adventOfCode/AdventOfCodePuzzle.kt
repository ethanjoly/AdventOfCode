package adventOfCode

abstract class AdventOfCodePuzzle {

    protected abstract val basePath: String

    val description: String by lazy {
        this.javaClass.getResource("$basePath/description.txt")?.readText() ?: ""
    }

    val input: String by lazy {
        this.javaClass.getResource("$basePath/input.txt")?.readText() ?: ""
    }

    val solution: String by lazy {
        var solution = ""
        var keepLine = false
        val startRegex = "^ {4}override val result: String by lazy \\{$".toRegex()
        val endRegex = "^ {4}}$".toRegex()
        val replaceRegex = "^ {8}".toRegex()
        this.javaClass.getResource("$basePath/solution.txt")?.readText()?.split("\n")?.forEach { line ->
            if (endRegex.containsMatchIn(line)) keepLine = false
            if (keepLine) {
                solution += "${line.replace(replaceRegex, "")}\n"
            }
            if (startRegex.containsMatchIn(line)) keepLine = true
        }
        solution
    }

    abstract val result: String
}