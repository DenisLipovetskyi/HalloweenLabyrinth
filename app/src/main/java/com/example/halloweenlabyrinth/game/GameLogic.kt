package com.example.halloweenlabyrinth.game

import com.example.halloweenlabyrinth.game.data.Direction
import com.example.halloweenlabyrinth.game.data.Player
import com.example.halloweenlabyrinth.game.data.Point
import com.example.halloweenlabyrinth.game.data.moveFrom

class GameLogic(private val viewModel: GameView) {
    fun movePlayer(direction: Direction) {
        val newPosition = direction.moveFrom(viewModel.currentPlayer.position)
        if (isValidMove(newPosition)) {
            viewModel.currentPlayer.position = newPosition
            viewModel.findTreasure(viewModel.currentPlayer)
        }
    }


    fun isValidMove(point: Point): Boolean {
        if (point.x !in 0 until viewModel.gridSize || point.y !in 0 until viewModel.gridSize) {
            return false
        }
        val tile = viewModel.gameState.value.tiles[point.y * viewModel.gridSize + point.x]
        return tile.type == "P" || tile.isMovable
    }


    fun checkEndGame(player: Player): Boolean {
        // For simplicity: Check if player found all treasures and is back to starting position
        return player.cards.isEmpty() && player.position == player.startingPosition
    }

}
