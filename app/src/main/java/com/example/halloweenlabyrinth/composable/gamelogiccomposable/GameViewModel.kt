package com.example.halloweenlabyrinth.composable.gamelogiccomposable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic

class GameViewModel : ViewModel() {

    val gameLogic = LabyrinthGameLogic.getInstance()  // Make this public so that onTileClick can access

    private val _gameState = MutableLiveData(gameLogic.getBoard())
    val gameState: LiveData<Array<Array<LabyrinthGameLogic.Tile>>> get() = _gameState

    private val _currentPlayer = MutableLiveData(gameLogic.getCurrentPlayer())
    val currentPlayer: LiveData<LabyrinthGameLogic.Player> get() = _currentPlayer

    private val _currentTreasure = MutableLiveData(_currentPlayer.value?.let {
        gameLogic.getCurrentTreasure(
            it
        )
    })
    val currentTreasure: LiveData<String?> get() = _currentTreasure

    // Add this method to handle updating the tile's treasure in the game state
    fun updateTileTreasure(row: Int, col: Int, treasure: String?) {
        gameLogic.setTreasureAtPosition(Pair(row, col), treasure)
        _gameState.value = gameLogic.getBoard()
    }

    // This method triggers the toast for invalid move
    fun showInvalidMove() {
        _showInvalidMoveToast.value = Event(Unit)
    }

    // This method updates the game state LiveData
    fun updateGameState(board: Array<Array<LabyrinthGameLogic.Tile>>) {
        _gameState.value = board
    }

    private val _showInvalidMoveToast = MutableLiveData<Event<Unit>>()
    val showInvalidMoveToast: LiveData<Event<Unit>> get() = _showInvalidMoveToast

}
