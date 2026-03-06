package com.example.curso

import android.os.Bundle
import androidx.compose.material3.NavigationBarItem
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.curso.ui.theme.CursoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

class BottomBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                val navController = rememberNavController()
                // En Material 3, el Scaffold pasa un "padding" que DEBES usar
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
//                                BottomNavItem("Home", "home", Icons.Default.Home),
//                                BottomNavItem("Chat", "chat", Icons.Default.Notifications, 23),
//                                BottomNavItem("Settings", "settings", Icons.Default.Settings, 214)
                            ),
                            navController = navController,
                            onItemClick = { item ->
                                navController.navigate(item.route) {
                                    // Evita acumular pantallas en el stack
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    // Aplicamos el padding para que el contenido no quede debajo de las barras
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation3(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    androidx.compose.material3.NavigationBar(
        // Fondo de la barra: Gris oscuro
        containerColor = Color(0xFF212121),
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                // Personalización de colores de los iconos y etiquetas
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,      // Icono cuando está seleccionado
                    unselectedIconColor = Color.LightGray, // Icono cuando NO está seleccionado (tu gris)
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color(0xFF373737)    // Color del "óvalo" de selección (un gris intermedio)
                ),
                label = { Text(text = item.name, fontSize = 12.sp) },
                icon = {
                    androidx.compose.material3.BadgedBox(
                        badge = {
                            if (item.badgeCount > 0) {
                                androidx.compose.material3.Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation3(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            PantallaInicio()
        }
        composable("chat"){
            ChatScreen()
        }
        composable("settings"){
            SettingsScreen()
        }
    }

}

@Composable
fun PantallaInicio(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "home")
    }
}

@Composable
fun ChatScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "home")
    }
}

@Composable
fun SettingsScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "home")
    }
}

