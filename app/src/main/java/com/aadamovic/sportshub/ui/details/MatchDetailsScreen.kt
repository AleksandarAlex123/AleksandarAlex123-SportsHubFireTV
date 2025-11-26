package com.aadamovic.sportshub.ui.details

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.aadamovic.sportshub.R
import com.aadamovic.sportshub.data.model.MatchItem
import com.aadamovic.sportshub.ui.components.DetailInfoCard
import com.aadamovic.sportshub.ui.components.TvActionButton

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MatchDetailsScreen(
    match: MatchItem,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    BackHandler {
        onBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        // ==== TOP CONTENT ====
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 48.dp, top = 24.dp, end = 48.dp)
        ) {

            // Smaller hero — creates more vertical room
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF444444), Color(0xFF111111))
                        )
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TITLE
            Text(
                text = match.title,
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            // LEAGUE
            Text(
                text = match.league,
                color = Color(0xFF57A5FF),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // INFO CARDS — reduced spacing so everything fits
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                DetailInfoCard(stringResource(R.string.status), match.status.name)
                DetailInfoCard(stringResource(R.string.time), match.time)
                DetailInfoCard(stringResource(R.string.league), match.league)
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TvActionButton(
                text = stringResource(R.string.watch),
                onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.watch_clicked), Toast.LENGTH_SHORT
                    ).show()
                }
            )

            TvActionButton(
                text = stringResource(R.string.join_quiz),
                onClick = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.quiz_started), Toast.LENGTH_SHORT
                    ).show()
                }
            )
            TvActionButton(stringResource(R.string.back), isSecondary = true, onClick = onBack)
        }
    }
}