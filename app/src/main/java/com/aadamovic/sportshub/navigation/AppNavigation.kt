package com.aadamovic.sportshub.navigation

import com.aadamovic.sportshub.data.model.MatchItem

sealed class Screen {
    data object Hub : Screen()
    data class Details(val match: MatchItem) : Screen()
}