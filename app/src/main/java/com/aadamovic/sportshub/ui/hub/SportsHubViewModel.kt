package com.aadamovic.sportshub.ui.hub

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aadamovic.sportshub.data.model.MatchStatus
import com.aadamovic.sportshub.data.model.SportsFeed
import com.aadamovic.sportshub.data.repository.SportsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportsHubViewModel(
    private val repository: SportsRepository
) : ViewModel() {

    // FEED
    private val _feed = MutableStateFlow<SportsFeed?>(null)
    val feed: StateFlow<SportsFeed?> = _feed

    init {
        viewModelScope.launch {
            _feed.value = repository.getSportsFeed()
        }
    }

    // FOCUS INDEX PER SECTION
    private val _liveIndex = MutableStateFlow(0)
    val liveIndex: StateFlow<Int> = _liveIndex

    private val _upcomingIndex = MutableStateFlow(0)
    val upcomingIndex: StateFlow<Int> = _upcomingIndex

    private val _replaysIndex = MutableStateFlow(0)
    val replaysIndex: StateFlow<Int> = _replaysIndex

    fun updateLiveIndex(index: Int) {
        _liveIndex.value = index
    }

    fun updateUpcomingIndex(index: Int) {
        _upcomingIndex.value = index
    }

    fun updateReplaysIndex(index: Int) {
        _replaysIndex.value = index
    }

    // LAST SECTION
    private val _lastSection = MutableStateFlow<MatchStatus?>(null)
    val lastSection: StateFlow<MatchStatus?> = _lastSection

    fun updateLastSection(section: MatchStatus) {
        _lastSection.value = section
    }

    // ONE-TIME RESTORE FLAG
    var restoreFocus by mutableStateOf(false)
        private set

    fun requestFocusRestore() {
        restoreFocus = true
    }

    fun consumeFocusRestore() {
        restoreFocus = false
    }
}