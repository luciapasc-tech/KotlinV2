package com.example.curso

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            // Asegúrate de que R.string.app_name esté definido en res/values/strings.xml
            Text(text = stringResource(id = R.string.app_name))
        },
        // En Material 3 usamos colors = TopAppBarDefaults.topAppBarColors(...)
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // El fondo
            titleContentColor = MaterialTheme.colorScheme.onPrimary, // El texto
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // El icono
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                // Corregido: Es Icons.Default.Menu
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer"
                )
            }
        }
    )
}