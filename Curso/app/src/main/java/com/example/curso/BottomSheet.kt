package com.example.curso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.curso.ui.theme.CursoTheme
import kotlinx.coroutines.launch

class BottomSheet : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                // 1. Configuración del estado del Sheet (Material 3)
                val sheetState = rememberStandardBottomSheetState(
                    initialValue = SheetValue.PartiallyExpanded
                )
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContainerColor = Color.Green, // En M3 es sheetContainerColor
                    sheetPeekHeight = 100.dp,          // Altura cuando está "cerrado"
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bottom sheet",
                                fontSize = 30.sp
                            )
                        }
                    }
                ) { paddingValues ->
                    // Contenido principal de la pantalla
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            scope.launch {
                                // 2. Lógica para expandir o contraer
                                if (sheetState.currentValue == SheetValue.Expanded) {
                                    sheetState.partialExpand()
                                } else {
                                    sheetState.expand()
                                }
                            }
                        }) {
                            // En M3, el progreso se accede de forma distinta,
                            // pero para un texto simple usamos el estado:
                            Text(text = "Alternar Bottom Sheet")
                        }
                    }
                }
            }
        }
    }
}