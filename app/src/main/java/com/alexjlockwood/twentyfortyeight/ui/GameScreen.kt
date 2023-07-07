package com.alexjlockwood.twentyfortyeight.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.alexjlockwood.twentyfortyeight.R
import com.alexjlockwood.twentyfortyeight.viewmodel.GameViewModel
import com.moriatsushi.koject.compose.viewmodel.injectViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = injectViewModel()
) {
    AppTheme {
        var shouldShowNewGameDialog by remember { mutableStateOf(false) }
        val orientation = LocalConfiguration.current.orientation
        GameUi(
            modifier = Modifier.fillMaxSize(),
            gridTileMovements = gameViewModel.gridTileMovements,
            currentScore = gameViewModel.currentScore,
            bestScore = gameViewModel.bestScore,
            moveCount = gameViewModel.moveCount,
            isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT,
            onSwipeListener = { direction -> gameViewModel.move(direction) },
            onNewGameRequested = { shouldShowNewGameDialog = true },
            onRestoreGameRequested = { gameViewModel.restore() },
        )

        if (gameViewModel.isGameOver) {
            GameDialog(
                title = stringResource(R.string.msg_game_over),
                message = stringResource(R.string.msg_game_over_body),
                onConfirmListener = { gameViewModel.startNewGame() },
                onDismissListener = { gameViewModel.restore() },
            )
        } else if (shouldShowNewGameDialog) {
            GameDialog(
                title = stringResource(R.string.msg_start_new_game),
                message = stringResource(R.string.msg_start_new_game_body),
                onConfirmListener = {
                    gameViewModel.startNewGame()
                    shouldShowNewGameDialog = false
                },
                onDismissListener = { shouldShowNewGameDialog = false },
            )
        }
    }
}
