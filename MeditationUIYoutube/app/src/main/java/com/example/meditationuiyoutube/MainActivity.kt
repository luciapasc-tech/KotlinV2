package com.example.meditationuiyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.meditationuiyoutube.ui.theme.HomeScreen
import com.example.meditationuiyoutube.ui.theme.MeditationUIYoutubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeditationUIYoutubeTheme {
                HomeScreen()
            }
        }
    }
}
