package com.aadamovic.sportshub.ui.hub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import com.aadamovic.sportshub.R
import com.aadamovic.sportshub.data.model.MatchItem
import com.aadamovic.sportshub.data.model.MatchStatus
import com.aadamovic.sportshub.data.model.SportsFeed
import com.aadamovic.sportshub.ui.components.MatchCard
import kotlinx.coroutines.delay

@Composable
fun SportsHubScreen(
    feed: SportsFeed?,
    lastFocusedLive: Int,
    lastFocusedUpcoming: Int,
    lastFocusedReplays: Int,
    restoreFocus: Boolean,
    restoreSection: MatchStatus?,
    onMatchSelected: (MatchItem, Int, MatchStatus) -> Unit,
    onFocusRestored: () -> Unit
) {
    if (feed == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.loading), color = Color.White)
        }
        return
    }

    // ⭐ STATE FOR VERTICAL COLUMN
    val columnState = rememberTvLazyListState()

    // ⭐ SCROLL TO CORRECT SECTION BEFORE RESTORING FOCUS
    LaunchedEffect(restoreFocus, restoreSection) {
        if (restoreFocus && restoreSection != null) {
            val targetIndex = when (restoreSection) {
                MatchStatus.LIVE -> 1
                MatchStatus.UPCOMING -> 3
                MatchStatus.REPLAY -> 5
            }

            columnState.scrollToItem(targetIndex)
        }
    }

    TvLazyColumn(
        state = columnState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp, vertical = 32.dp)
    ) {

        item {
            Text(
                stringResource(R.string.sports_hub),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(40.dp))
        }

        // LIVE
        item {
            MatchRow(
                title = stringResource(R.string.live_now),
                items = feed.live_now,
                initialIndex = lastFocusedLive,
                restoreFocus = restoreFocus && restoreSection == MatchStatus.LIVE,
                sectionStatus = MatchStatus.LIVE,
                onMatchSelected = onMatchSelected,
                onFocusRestored = onFocusRestored
            )
            Spacer(Modifier.height(32.dp))
        }

        // UPCOMING
        item {
            MatchRow(
                title = stringResource(R.string.upcoming),
                items = feed.upcoming,
                initialIndex = lastFocusedUpcoming,
                restoreFocus = restoreFocus && restoreSection == MatchStatus.UPCOMING,
                sectionStatus = MatchStatus.UPCOMING,
                onMatchSelected = onMatchSelected,
                onFocusRestored = onFocusRestored
            )
            Spacer(Modifier.height(32.dp))
        }

        // REPLAY
        item {
            MatchRow(
                title = stringResource(R.string.replays),
                items = feed.replays,
                initialIndex = lastFocusedReplays,
                restoreFocus = restoreFocus && restoreSection == MatchStatus.REPLAY,
                sectionStatus = MatchStatus.REPLAY,
                onMatchSelected = onMatchSelected,
                onFocusRestored = onFocusRestored
            )
        }
    }
}

@Composable
fun MatchRow(
    title: String,
    items: List<MatchItem>,
    initialIndex: Int,
    restoreFocus: Boolean,
    sectionStatus: MatchStatus,
    onMatchSelected: (MatchItem, Int, MatchStatus) -> Unit,
    onFocusRestored: () -> Unit
) {
    Column {

        Text(
            title,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        val listState = rememberTvLazyListState()
        val focusRequesters = remember { List(items.size) { FocusRequester() } }

        // ------------ FOCUS RESTORE ------------
        LaunchedEffect(restoreFocus) {
            if (restoreFocus && items.isNotEmpty()) {

                // 1. scroll to correct row index
                listState.scrollToItem(initialIndex)

                // 2. wait for layout to stabilize
                delay(150)

                // 3. request focus for the correct cell
                focusRequesters.getOrNull(initialIndex)?.requestFocus()

                // 4. consume restore flag
                onFocusRestored()
            }
        }

        TvLazyRow(
            state = listState,
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            pivotOffsets = PivotOffsets(parentFraction = 0.20f)
        ) {

            items(items.size) { index ->

                Box(
                    modifier = Modifier
                        .focusRequester(focusRequesters[index])
                ) {
                    MatchCard(match = items[index]) {
                        onMatchSelected(items[index], index, sectionStatus)
                    }
                }
            }
        }
    }
}
