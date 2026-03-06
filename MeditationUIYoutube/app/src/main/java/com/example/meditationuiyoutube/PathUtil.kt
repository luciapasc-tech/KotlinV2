package com.example.meditationuiyoutube

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standartQuadTo(from: Offset, to: Offset){
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f ,
        abs(from.y + to.y) / 2f
    )
}