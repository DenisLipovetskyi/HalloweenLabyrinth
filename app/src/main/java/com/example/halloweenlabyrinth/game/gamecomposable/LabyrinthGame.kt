// Imports related to Jetpack Compose UI
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.halloweenlabyrinth.R
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.halloweenlabyrinth.game.data.GameState
import com.example.halloweenlabyrinth.game.gamecomposable.GameViewModel
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
fun SampleFixedTile() {
    val image: Painter = painterResource(id = R.drawable.fixed_tile_image)
    Image(painter = image, contentDescription = null)
}

@Composable
fun SampleMovableTile(rotation: Int) {
    val image: Painter = painterResource(id = R.drawable.movable_tile_image)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier.rotate(rotation.toFloat())
    )
}

@Composable
fun SampleTreasureTile(treasureType: String) {
    val imageResId = when (treasureType) {
        "gold" -> R.drawable.gold_treasure_image
        "diamond" -> R.drawable.diamond_treasure_image
        else -> R.drawable.default_treasure_image
    }
    val image: Painter = painterResource(id = imageResId)
    Image(painter = image, contentDescription = null)
}

@Composable
fun GameBoardUI(gameState: GameState, gameLogic: GameLogic) {
    Box(modifier = Modifier.fillMaxSize()) {
        DisplayTiles(gameState)
        DisplayTreasures(gameState)
        DisplayPlayers(gameState, gameLogic)
    }
}

@Composable
private fun DisplayTiles(gameState: GameState) {
    // Calculate the size of each tile based on the screen width and the grid size
    val tileSize = LocalContext.current.resources.displayMetrics.widthPixels / 7

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        gameState.tiles.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                row.forEach { tile ->
                    Box(modifier = Modifier.size(tileSize.dp)) {
                        TileComposable(tile) {
                            // TODO: Handle tile click if needed
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TileComposable(tile: Tile, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        when (tile.type) {
            TileType.FIXED -> SampleFixedTile()
            TileType.MOVABLE -> SampleMovableTile(tile.rotation)
            TileType.TREASURE -> SampleTreasureTile(tile.treasure?.type ?: "")
            else -> {}
        }
    }
}


@Composable
fun SampleGameBoardUI(gameState: GameState, gameLogic: GameLogic) {
    Box(modifier = Modifier.fillMaxSize()) {
        gameState.tiles.forEach { row ->
            Row {
                row.forEach { tile ->
                    when (tile.type) {
                        TileType.FIXED -> SampleFixedTile()
                        TileType.MOVABLE -> SampleMovableTile(tile.rotation)
                        TileType.TREASURE -> SampleTreasureTile(tile.treasure?.type ?: "")
                        else -> {}
                    }
                }
            }
        }
    }
}

// Keep your data classes and enums as they are, they seem structured and clear.
