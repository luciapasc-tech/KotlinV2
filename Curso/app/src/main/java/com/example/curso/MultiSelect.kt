package com.example.curso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.curso.ui.theme.CursoTheme

// 1. Definimos el modelo fuera de la clase con un nombre que no choque
data class ItemModel(
    val title: String,
    val isSelected: Boolean
)

class MultiSelect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                // 2. Estado de la lista
                var items by remember {
                    mutableStateOf(
                        (1..20).map {
                            ItemModel(title = "Item $it", isSelected = false)
                        }
                    )
                }

                // Usamos padding para que el contenido no quede debajo de la barra de estado
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    // 3. Mejor usar itemsIndexed para listas dinámicas
                    itemsIndexed(items) { index, item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // 4. Lógica de selección corregida
                                    items = items.mapIndexed { j, currentItem ->
                                        if (index == j) {
                                            currentItem.copy(isSelected = !currentItem.isSelected)
                                        } else currentItem
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = item.title)

//                            if (item.isSelected) {
//                                Icon(
//                                   // imageVector = Icons.Default.Check,
//                                    contentDescription = "Selected", // Corregido: minúscula
//                                    tint = Color.Green,
//                                    modifier = Modifier.size(20.dp)
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }
}