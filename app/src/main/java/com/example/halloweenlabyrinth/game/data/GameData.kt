package com.example.halloweenlabyrinth.game.data

data class Point(val x: Int, val y: Int)
data class Tile(
    val type: String = "P",
    val isMovable: Boolean = false,
    val treasureType: String? = null,
    val playerOnTile: Player? = null
)
enum class TileType {
    PATH, WALL, Treasure
}
data class Player(
    val name: String,
    var position: Point = Point(0, 0),
    val startingPosition: Point = Point(0, 0),  // Add this line
    val cards: List<Treasure> = listOf()
)
data class Treasure(val type: String, val position: Point)


//creating hash code for Game State
data class GameState(
    val tiles: Array<Array<Tile>>,
    val players: List<Player>,
    val treasures: List<Treasure>
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
