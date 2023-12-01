package adventOfCode

abstract class AdventOfCodeEvent {
    abstract val name: String
    abstract val days: List<AdventOfCodeDay>
}