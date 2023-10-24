package com.example.halloweenlabyrinth.composable.gamelogiccomposable

import com.example.halloweenlabyrinth.model.Treasure

data class Player(
    var currentPosition: Pair<Int, Int>,
    var startPosition: Pair<Int, Int>,
    var treasureCards: MutableList<Treasure> = mutableListOf()
)
