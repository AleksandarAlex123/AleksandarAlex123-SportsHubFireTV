package com.aadamovic.sportshub.data.model

data class SportsFeed(
    val live_now: List<MatchItem>,
    val upcoming: List<MatchItem>,
    val replays: List<MatchItem>
)