package com.example.halloweenlabyrinth.game

import com.example.halloweenlabyrinth.game.data.Direction
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

    private fun isValidMove(point: Point) = point.x in 0 until viewModel.gridSize && point.y in 0 until viewModel.gridSize
}
