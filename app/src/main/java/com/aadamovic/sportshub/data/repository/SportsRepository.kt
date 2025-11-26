package com.aadamovic.sportshub.data.repository

import android.content.Context
import com.aadamovic.sportshub.data.local.SportsFeedLoader
import com.aadamovic.sportshub.data.model.SportsFeed

class SportsRepository(context: Context) {

    private val loader = SportsFeedLoader(context)

    fun getSportsFeed(): SportsFeed {
        return loader.loadFeed()
    }
}