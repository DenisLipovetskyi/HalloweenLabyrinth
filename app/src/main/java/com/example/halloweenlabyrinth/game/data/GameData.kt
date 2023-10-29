package com.example.halloweenlabyrinth.game.data

data class Point(val x: Int, val y: Int)
data class Tile(
    val type: String = "P",
    val isMovable: Boolean = false,
    val treasureType: String? = null,
    val playerOnTile: Player? = null
)
data class Player(
    val name: String,
    var position: Point = Point(0, 0),
    val startingPosition: Point = Point(0, 0),  // Add this line
    val cards: List<Treasure> = listOf()
)
data class Treasure(val type: String, val position: Point)
