package com.aadamovic.sportshub.data.local

import android.content.Context
import com.aadamovic.sportshub.data.model.SportsFeed
import com.google.gson.Gson

class SportsFeedLoader(private val context: Context) {

    fun loadFeed(): SportsFeed {
        val json = context.assets.open("raw/sports_feed.json")
            .bufferedReader()
            .use { it.readText() }

        return Gson().fromJson(json, SportsFeed::class.java)
    }
}