package com.example.halloweenlabyrinth.game.data

data class Point(val x: Int, val y: Int)
data class Tile(val type: String = "path", val isMovable: Boolean = true)
data class Player(val name: String, var position: Point = Point(0, 0), val cards: List<Treasure> = listOf())
data class Treasure(val type: String, val position: Point)
