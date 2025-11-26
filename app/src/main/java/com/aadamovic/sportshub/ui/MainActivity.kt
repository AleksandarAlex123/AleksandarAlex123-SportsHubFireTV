package com.aadamovic.sportshub.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aadamovic.sportshub.data.repository.SportsRepository
import com.aadamovic.sportshub.navigation.AppNavHost
import com.aadamovic.sportshub.ui.theme.SportsHubFireTVTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = SportsRepository(this)

        setContent {
            SportsHubFireTVTheme {
                AppNavHost(repository)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Handle BACK from remote
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
            return super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event)
    }
}

