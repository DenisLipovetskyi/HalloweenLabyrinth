package com.example.halloweenlabyrinth.game

import com.example.halloweenlabyrinth.game.data.*
import com.example.halloweenlabyrinth.game.gamecomposable.GameViewModel

class GameLogic(private val viewModel: GameViewModel) {

    private val currentPlayer: Player
        get() = viewModel.gameState.value.players.first()

    fun movePlayer(direction: Direction) {
        val newPosition = direction.moveFrom(currentPlayer.position)
        if (isValidMove(newPosition)) {
            currentPlayer.position = newPosition
            collectTreasureAt(newPosition)
        } else {
            println("Invalid move")
        }
    }

    private fun collectTreasureAt(position: Point) {
        viewModel.gameState.value.treasures.find { it.position == position }?.let {
            currentPlayer.treasureCards.add(it)
            viewModel.gameState.value.treasures.remove(it)
        }
    }

    private fun isValidMove(point: Point): Boolean {
        return point.isInBounds() && isMovableTile(point) && !playerCollision(point)
    }

    private fun Point.isInBounds(): Boolean {
        val size = viewModel.gameState.value.tiles.size
        return x in 0 until size && y in 0 until size
    }

    private fun isMovableTile(point: Point) =
        viewModel.gameState.value.tiles[point.y][point.x].type == TileType.MOVABLE

    private fun playerCollision(point: Point) =
        viewModel.gameState.value.players.any { it != currentPlayer && it.position == point }

    fun shiftMaze(direction: Direction) {
        when (direction) {
            Direction.UP -> rotateRow(true)
            Direction.DOWN -> rotateRow(false)
            Direction.LEFT -> rotateColumn(true)
            Direction.RIGHT -> rotateColumn(false)
        }
        handlePostMazeShift()
    }

    private fun rotateRow(upwards: Boolean) {
        val tiles = viewModel.gameState.value.tiles
        if (upwards) {
            val firstRow = tiles.first().copyOf()
            for (i in tiles.indices.dropLast(1)) tiles[i] = tiles[i + 1]
            tiles[tiles.lastIndex] = firstRow
        } else {
            val lastRow = tiles.last().copyOf()
            for (i in tiles.indices.reversed().drop(1)) tiles[i] = tiles[i - 1]
            tiles[0] = lastRow
        }
    }

    private fun rotateColumn(leftwards: Boolean) {
        val tiles = viewModel.gameState.value.tiles
        val gridSize = tiles.size
        if (leftwards) {
            val firstColumn = tiles.map { it.first() }.toTypedArray()
            for (i in tiles.indices) {
                System.arraycopy(tiles[i], 1, tiles[i], 0, gridSize - 1)
                tiles[i][gridSize - 1] = firstColumn[i]
            }
        } else {
            val lastColumn = tiles.map { it.last() }.toTypedArray()
            for (i in tiles.indices) {
                System.arraycopy(tiles[i], 0, tiles[i], 1, gridSize - 1)
                tiles[i][0] = lastColumn[i]
            }
        }
    }

    private fun handlePostMazeShift() {
        viewModel.gameState.value.players.forEach {
            if (it.position.isOutOfBoundaries()) {
                // Handle player out of boundaries scenario
            }
        }
        viewModel.gameState.value.treasures.forEach {
            if (it.position.isOutOfBoundaries()) {
                // Handle treasure out of boundaries scenario
            }
        }
    }

    private fun Point.isOutOfBoundaries(): Boolean {
        val size = viewModel.gameState.value.tiles.size
        return x !in 0 until size || y !in 0 until size
    }
}

private fun IntRange.dropLast(i: Int): IntRange {
    return if (this.first <= this.last - i) {
        this.first until this.last - i
    } else {
        IntRange.EMPTY
    }
}
