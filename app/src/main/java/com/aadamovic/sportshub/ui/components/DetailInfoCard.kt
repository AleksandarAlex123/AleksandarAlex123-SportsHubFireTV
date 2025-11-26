package com.aadamovic.sportshub.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DetailInfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    var focused by remember { mutableStateOf(false) }

    Card(
        onClick = {},
        modifier = modifier
            .onFocusChanged { focused = it.isFocused }
            .padding(8.dp)
            .height(120.dp)
            .width(260.dp),

        colors = CardDefaults.colors(
            containerColor = if (focused) Color(0xFF333333) else Color(0xFF2A2A2A)
        ),

        scale = CardDefaults.scale(
            focusedScale = 1.05f
        ),

        border = CardDefaults.border(
            border = Border.None,
            focusedBorder = Border(
                border = BorderStroke(3.dp, Color(0xFF57A5FF)),
                shape = RoundedCornerShape(8.dp)
            )
        )
    ) {
        Column(Modifier.padding(16.dp)) {

            Text(
                text = title.uppercase(),
                color = Color(0xFF57A5FF),
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = value,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
