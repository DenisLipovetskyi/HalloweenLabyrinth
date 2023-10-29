package com.example.halloweenlabyrinth.game

import com.example.halloweenlabyrinth.game.data.Direction
import com.example.halloweenlabyrinth.game.data.Player
import com.example.halloweenlabyrinth.game.data.Point
import com.example.halloweenlabyrinth.game.data.moveFrom

class GameLogic(private val viewModel: GameView) {


    private fun playerCollision(newPosition: Point): Boolean {
        return viewModel.gameState.value.players.any { it.position == newPosition }
    }

    fun movePlayer(direction: Direction) {
        val newPosition = direction.moveFrom(viewModel.currentPlayer.position)
        if (isValidMove(newPosition)) {
            viewModel.currentPlayer.position = newPosition
            viewModel.findTreasure(viewModel.currentPlayer)
        }
    }

    private fun isValidMove(point: Point): Boolean {
        if (point.x !in 0 until viewModel.gridSize || point.y !in 0 until viewModel.gridSize) {
            return false
        }
        val tile = viewModel.gameState.value.tiles[point.y][point.x]
        return (tile.type == "P" || tile.isMovable) && !playerCollision(point)
    }

    fun checkEndGame(player: Player): Boolean {
        return player.cards.isEmpty() && player.position == player.startingPosition
    }
}
