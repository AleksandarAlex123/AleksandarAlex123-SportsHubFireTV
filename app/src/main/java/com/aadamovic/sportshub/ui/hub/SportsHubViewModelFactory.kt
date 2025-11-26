package com.aadamovic.sportshub.ui.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aadamovic.sportshub.data.repository.SportsRepository

class SportsHubViewModelFactory(
    private val repository: SportsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SportsHubViewModel(repository) as T
    }
}