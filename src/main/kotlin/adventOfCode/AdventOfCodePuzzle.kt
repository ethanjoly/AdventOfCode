package adventOfCode

abstract class AdventOfCodePuzzle {

    protected abstract val basePath: String

    val description: String by lazy {
        this.javaClass.getResource("$basePath/description.txt")?.readText() ?: ""
    }

    val input: String by lazy {
        this.javaClass.getResource("$basePath/input.txt")?.readText()?.replace("\r", "") ?: ""
    }

    val solution: String by lazy {
        var solution = ""
        var isInHeader = false
        var keepLine = false
        val headerStartRegex = "^//-start-$".toRegex()
        val headerEndRegex = "^//-end-$".toRegex()
        val startRegex = "^ {4}override val result: String by lazy \\{$".toRegex()
        val endRegex = "^ {4}}$".toRegex()
        val replaceRegex = "^ {8}".toRegex()
        this.javaClass.getResource("$basePath/solution.txt")?.readText()?.split("\n")?.forEach { line ->
            if (keepLine) {
                if (!isInHeader && endRegex.containsMatchIn(line)) {
                    keepLine = false
                } else if (headerEndRegex.containsMatchIn(line)) {
                    solution += "\n"
                    keepLine = false
                }
            }
            if (keepLine) {
                solution += if (isInHeader) {
                    "${line}\n"
                } else {
                    "${line.replace(replaceRegex, "")}\n"
                }
            }
            if (startRegex.containsMatchIn(line)) {
                isInHeader = false
                keepLine = true
            } else if (headerStartRegex.containsMatchIn(line)) {
                isInHeader = true
                keepLine = true
            }
        }
        solution
    }

    abstract val result: String
}