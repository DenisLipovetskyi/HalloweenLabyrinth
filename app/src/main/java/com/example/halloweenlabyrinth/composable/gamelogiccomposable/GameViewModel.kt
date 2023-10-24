package com.example.halloweenlabyrinth.composable.gamelogiccomposable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic
import com.example.halloweenlabyrinth.model.Tile


class GameViewModel : ViewModel() {
    private val _gameState = MutableLiveData<Array<Array<Tile>>>()
    val gameState: LiveData<Array<Array<Tile>>> = _gameState

    private val _players = MutableLiveData<List<LabyrinthGameLogic.Player>>()
    val players: LiveData<List<LabyrinthGameLogic.Player>> = _players

    init {
        _gameState.value = LabyrinthGameLogic.getInstance().getBoard().map { row ->
            row.map { tile ->
                convertToModelTile(tile)
            }.toTypedArray()
        }.toTypedArray()

        _players.value = LabyrinthGameLogic.getInstance().getPlayers()
    }

    fun updateGameState() {
        _gameState.value = LabyrinthGameLogic.getInstance().getBoard().map { row ->
            row.map { tile ->
                convertToModelTile(tile)
            }.toTypedArray()
        }.toTypedArray()
    }

    private fun convertToModelTile(logicTile: LabyrinthGameLogic.Tile): Tile {
        return Tile(
            logicTile.type,
            logicTile.treasure,
            logicTile.player
        )
    }

    // Other functions to handle game logic
}
