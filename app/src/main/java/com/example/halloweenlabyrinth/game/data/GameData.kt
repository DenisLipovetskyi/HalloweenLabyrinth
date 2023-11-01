package com.example.halloweenlabyrinth.game.data

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    fun moveFrom(point: Point): Point {
        return when (this) {
            UP -> Point(point.x, point.y - 1)
            DOWN -> Point(point.x, point.y + 1)
            LEFT -> Point(point.x - 1, point.y)
            RIGHT -> Point(point.x + 1, point.y)
        }
    }
}

data class Point(val x: Int, val y: Int)

enum class TileType { FIXED, MOVABLE, TREASURE, EMPTY }

data class Tile(
    val type: TileType,
    val position: Point,  // Added the position directly into Tile
    val treasure: Treasure? = null, // Updated to Treasure type instead of Point for clearer representation
    val player: Player? = null // Represents player on the tile, null if no player
)

data class Player(
    val id: String,
    var position: Point,
    val treasureCards: MutableList<Treasure> = mutableListOf()
)

data class Treasure(val type: String, val position: Point)

data class GameState(
    val tiles: Array<Array<Tile>>,
    val players: MutableList<Player>,
    val treasures: MutableList<Treasure>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState
        if (!tiles.contentDeepEquals(other.tiles)) return false
        if (players != other.players) return false
        if (treasures != other.treasures) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tiles.contentDeepHashCode()
        result = 31 * result + players.hashCode()
        result = 31 * result + treasures.hashCode()
        return result
    }
}
