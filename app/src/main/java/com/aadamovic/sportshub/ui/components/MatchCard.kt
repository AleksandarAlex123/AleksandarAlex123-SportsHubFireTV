package com.aadamovic.sportshub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.aadamovic.sportshub.data.model.MatchItem
import com.aadamovic.sportshub.data.model.MatchStatus

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MatchCard(
    match: MatchItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .width(260.dp)
            .height(140.dp),
        scale = CardDefaults.scale(focusedScale = 1.05f),
        colors = CardDefaults.colors(
            containerColor = Color(0xFF222222)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            androidx.compose.material3.Text(
                text = match.title,
                color = Color.White,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            androidx.compose.material3.Text(
                text = match.time,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            androidx.compose.material3.Text(
                text = match.status.name,
                color = when (match.status) {
                    MatchStatus.LIVE -> Color.Red
                    MatchStatus.UPCOMING -> Color.Yellow
                    MatchStatus.REPLAY -> Color.Cyan
                }
            )
        }
    }
}