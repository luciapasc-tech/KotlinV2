package com.example.curso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.curso.ui.theme.CursoTheme

class LazyGrid : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                val state = rememberLazyListState(
                    initialFirstVisibleItemIndex = 99
                )
                //state.animateScrollToItem(99)

                // En Material 3, usamos 'columns' en lugar de 'cells'
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp), // Ajustado a 100.dp para que sea legible
                    modifier = Modifier.padding(top = 32.dp), // Para no chocar con el notch
                    //state = state
                ) {

                    // El bloque de items se define directamente aquí
                    items(100) { i ->
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .aspectRatio(1f) // Mantiene el cuadrado perfecto
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Green),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Item $i")
                        }
                    }
                }
            }
        }
    }
}

