package com.aadamovic.sportshub.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvActionButton(
    text: String,
    isSecondary: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        colors =
            if (!isSecondary)
                ButtonDefaults.colors(
                    containerColor = Color(0xFF2D7DFF),
                    contentColor = Color.White,
                    focusedContainerColor = Color(0xFF539AFF),
                    focusedContentColor = Color.White
                )
            else
                ButtonDefaults.colors(
                    containerColor = Color(0xFF333333),
                    contentColor = Color.White,
                    focusedContainerColor = Color(0xFF555555),
                    focusedContentColor = Color.White
                ),
        scale = ButtonDefaults.scale(focusedScale = 1.1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}