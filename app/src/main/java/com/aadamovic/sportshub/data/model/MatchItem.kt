package com.aadamovic.sportshub.data.model

data class MatchItem(
    val title: String,
    val time: String,
    val status: MatchStatus,
    val league: String
)