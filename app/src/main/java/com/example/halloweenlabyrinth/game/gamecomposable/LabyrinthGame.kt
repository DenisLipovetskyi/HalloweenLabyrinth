// Imports related to Jetpack Compose UI
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.halloweenlabyrinth.game.data.GameState
import com.example.halloweenlabyrinth.game.data.Direction
import androidx.compose.ui.unit.dp

// Imports related to game logic and data
import com.example.halloweenlabyrinth.game.GameLogic
import com.example.halloweenlabyrinth.game.data.*

// Game Composable UI Elements
import com.example.halloweenlabyrinth.game.gamecomposable.*

@Composable
fun LabyrinthGame(viewModel: GameViewModel, gameLogic: GameLogic) {
    val gameState by viewModel.gameState

    when {
        viewModel.isGameOver && !viewModel.hasWon -> Text("Game Over!")
        viewModel.hasWon -> Text("You Won!")
        else -> GameBoardUI(gameState, gameLogic)
    }
}

@Composable
private fun GameBoardUI(gameState: GameState, gameLogic: GameLogic) {
    Box(modifier = Modifier.fillMaxSize()) {
        DisplayTiles(gameState)
        DisplayTreasures(gameState)
        DisplayPlayers(gameState, gameLogic)
    }
}

@Composable
private fun DisplayTiles(gameState: GameState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        gameState.tiles.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                row.forEach { tile ->
                    TileComposable(tile) {
                        // TODO: Handle tile click if needed
                    }
                }
            }
        }
    }
}

@Composable
private fun DisplayTreasures(gameState: GameState) {
    gameState.treasures.forEach { treasure ->
        Box(modifier = Modifier.offsetFromGrid(treasure.position)) {
            TreasureComposable(treasure)
        }
    }
}

@Composable
private fun DisplayPlayers(gameState: GameState, gameLogic: GameLogic) {
    gameState.players.forEach { player ->
        Box(modifier = Modifier.offsetFromGrid(player.position)) {
            PlayerComposable(player) { direction: Direction ->
                gameLogic.movePlayer(direction)
            }
        }
    }
}

@Composable
fun PlayerComposable(player: Player, onDirectionSelected: (Direction) -> Unit) {
    // Implementation for PlayerComposable
    // Invoke onDirectionSelected(Direction) when required
}

// Keep your data classes and enums as they are, they seem structured and clear.
