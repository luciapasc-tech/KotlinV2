package com.example.mobentisui.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// --- Colores Base ---
val MobentisRed = Color(0xFFF05A5E)
val MobentisOrange = Color(0xFFF58262)
val MobentisYellow = Color(0xFFF9D45E)
val MobentisPurple = Color(0xFF912A5E)

// --- Colores de Interfaz ---
val InputBackground = Color(0xFFFFFFFF).copy(alpha = 0.85f)
val TextDark = Color(0xFF333333)
val TextLight = Color(0xFFFFFFFF)
val IconGray = Color(0xFF757575)
// --- Degradados (Gradients) ---

val LoginButtonGradient = Brush.horizontalGradient(
    colors = listOf(
        MobentisRed,
        MobentisOrange
    )
)

// Degradado para el fondo (si no usas imagen y quieres el estilo geométrico)
val BackgroundOverlay = Color(0xFF2B2B2B).copy(alpha = 0.4f)

// --- Colores Estándar del Tema Material3 ---
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)