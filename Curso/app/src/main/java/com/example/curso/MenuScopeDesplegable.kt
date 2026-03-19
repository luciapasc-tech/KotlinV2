package com.example.curso


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.curso.ui.theme.CursoTheme
import kotlinx.coroutines.launch


class MenuScopeDesplegable : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                // 2. Estado del Drawer en Material 3
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                // 3. El Drawer ahora envuelve al Scaffold
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        // Contenido del menú lateral
                        ModalDrawerSheet {
                            DrawerHeader()
                            DrawerBody(
                                items = listOf(
                                    MenuItem("home", "Home", "Go home", Icons.Default.Home),
                                    MenuItem("settings", "Settings", "Settings", Icons.Default.Settings),
                                    MenuItem("help", "Help", "Get help", Icons.Default.Info),
                                ),
                                onItemClick = { item ->
                                    println("Clicked on ${item.title}")
                                    scope.launch { drawerState.close() }
                                }
                            )
                        }
                    }
                ) {
                    // 4. Estructura principal de la pantalla
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text("Mi App") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch { drawerState.open() }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                                    }
                                }
                            )
                        }
                    ) { padding ->
                        Box(modifier = Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Contenido principal aquí")
                        }
                    }
                }
            }
        }
    }
}

