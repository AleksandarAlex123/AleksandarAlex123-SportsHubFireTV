package com.aadamovic.sportshub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aadamovic.sportshub.data.model.MatchStatus
import com.aadamovic.sportshub.data.repository.SportsRepository
import com.aadamovic.sportshub.ui.details.MatchDetailsScreen
import com.aadamovic.sportshub.ui.hub.SportsHubScreen
import com.aadamovic.sportshub.ui.hub.SportsHubViewModel
import com.aadamovic.sportshub.ui.hub.SportsHubViewModelFactory

@Composable
fun AppNavHost(repository: SportsRepository) {

    val viewModel: SportsHubViewModel = viewModel(
        factory = SportsHubViewModelFactory(repository)
    )

    var screen by remember { mutableStateOf<Screen>(Screen.Hub) }

    val feed by viewModel.feed.collectAsState()

    val liveIndex by viewModel.liveIndex.collectAsState()
    val upcomingIndex by viewModel.upcomingIndex.collectAsState()
    val replayIndex by viewModel.replaysIndex.collectAsState()

    val lastSection by viewModel.lastSection.collectAsState()

    val restoreFocus = viewModel.restoreFocus

    when (val current = screen) {

        is Screen.Hub -> {

            SportsHubScreen(
                feed = feed,
                lastFocusedLive = liveIndex,
                lastFocusedUpcoming = upcomingIndex,
                lastFocusedReplays = replayIndex,
                restoreFocus = restoreFocus,
                restoreSection = lastSection,
                onFocusRestored = {
                    viewModel.consumeFocusRestore()
                },

                onMatchSelected = { match, index, status ->
                    viewModel.updateLastSection(status)
                    when (status) {
                        MatchStatus.LIVE -> viewModel.updateLiveIndex(index)
                        MatchStatus.UPCOMING -> viewModel.updateUpcomingIndex(index)
                        MatchStatus.REPLAY -> viewModel.updateReplaysIndex(index)
                    }
                    viewModel.requestFocusRestore()
                    screen = Screen.Details(match)
                }
            )
        }

        is Screen.Details -> {
            MatchDetailsScreen(
                match = current.match,
                onBack = { screen = Screen.Hub }
            )
        }
    }
}
