package com.example.curso

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoTheme {
                Navigation()
            }
        }
    }
}
 */

@Composable
fun Navigation1() {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen1(navController = navController)
        }
        composable(
            route = Screen.MainScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Philip"
                    nullable = true
                }
            )
        ){entry ->
            DetailScreen1(name = entry.arguments?.getString("name"))
        }
    }

}

@Composable
fun MainScreen1(navController: NavController){
    var text by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(text))
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "to detail screen")
            }
        }
    }
}

@Composable
fun DetailScreen1(name: String?) {
    Box(
        contentAlignment = Alignment.Center ,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Hello, $name")
    }
}