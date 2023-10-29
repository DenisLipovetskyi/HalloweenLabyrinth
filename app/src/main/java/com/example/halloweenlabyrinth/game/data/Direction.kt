package com.example.halloweenlabyrinth.game.data

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun Direction.moveFrom(point: Point): Point {
    return when (this) {
        Direction.UP -> Point(point.x, point.y - 1)
        Direction.DOWN -> Point(point.x, point.y + 1)
        Direction.LEFT -> Point(point.x - 1, point.y)
        Direction.RIGHT -> Point(point.x + 1, point.y)
    }
}


