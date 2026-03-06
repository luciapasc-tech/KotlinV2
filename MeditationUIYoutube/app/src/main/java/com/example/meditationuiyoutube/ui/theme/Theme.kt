package com.example.meditationuiyoutube.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
// Eliminamos el import de com.plcoding...

@Composable
fun MeditationUIYoutubeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        // Si no tienes un archivo Shapes.kt, puedes borrar esta línea
        // o usar las por defecto de Material3:
        // shapes = MaterialTheme.shapes,
        content = content
    )
}