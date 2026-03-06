package com.example.mobentisui.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobentisui.R
//import androidx.compose.material.icons.filled.Visibility

@Composable
fun LoginScreen() {
    // Estados para los inputs
    var usuario by remember { mutableStateOf("David Anton") }
    var password by remember {mutableStateOf("12345")}

    // Estados para controlar los errores
    var isErrorUser by remember { mutableStateOf(false) }
    var isErrorPass by remember { mutableStateOf(false) }

    // Variables para personalizar el mensaje de error (campo vacio)
    var userErrorMessage by remember { mutableStateOf("") }
    var passErrorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Imagen de Fondo, lo ponemos en gris (aqui iria el jpg)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2E3135)) // Color gris oscuro sólido temporal
        )

        // Capa de oscurecimiento sutil para que los textos resalten
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //ESPACIO SUPERIOR FIJO PARA EL LOGO
            Spacer(modifier = Modifier.height(60.dp))

            // 2. Logo Superior (La M de colores)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MobentisRed, shape = CircleShape), // Un círculo rojo temporal
                contentAlignment = Alignment.Center
            ) {
                Text("M", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
            }

            //spacer que empuja hacia abajo los elementos debajo del logo
            Spacer(modifier = Modifier.weight(1f))

            // --- BLOQUE CENTRAL (Usuario, Pass, Botón) ---
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f), // Estrecha el ancho proporcionalmente
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp) // Espaciado proporcional entre ellos
            ) {
                //3.usuario
                CustomLoginInput(
                    label = "Usuario",
                    value = usuario,
                    onValueChange = {
                        usuario = it
                        isErrorUser = false
                    },
                    icon = Icons.Default.Person,
                    isError = isErrorUser,
                    errorMessage = userErrorMessage
                )

                //4.contraseña
                CustomLoginInput(
                    label = "Contraseña",
                    value = password,
                    onValueChange = {
                        password = it
                        isErrorPass = false
                    },
                    icon = Icons.Default.Lock,
                    isPassword = true,
                    isError = isErrorPass,
                    errorMessage = passErrorMessage
                )

                Spacer(modifier = Modifier.height(8.dp))

                //5.boton
                Button(
                    onClick = {
                        // 1. Validar Usuario vacío
                        if (usuario.isEmpty()) {
                            isErrorUser = true
                            userErrorMessage = "El campo no puede estar vacío"
                        } else if (usuario != "admin") {
                            isErrorUser = true
                            userErrorMessage = "Usuario no válido"
                        } else {
                            isErrorUser = false
                        }

                        // 2. Validar Contraseña vacía
                        if (password.isEmpty()) {
                            isErrorPass = true
                            passErrorMessage = "El campo no puede estar vacío"
                        } else if (password != "123456") {
                            isErrorPass = true
                            passErrorMessage = "Contraseña no válida"
                        } else {
                            isErrorPass = false
                        }

                        // 3. Si ambos son correctos, proceder al Login
                        if (!isErrorUser && !isErrorPass) {
                            // Navegar o entrar a la app
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(55.dp).background(LoginButtonGradient, CircleShape),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues()
                ) {
                    Text("LOGIN", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 6. Texto Inferior "MOBENTIS COMERCIAL"
            Text(
                text = "MOBENTIS",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )
            Text(
                text = "COMERCIAL",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 6.sp // Espaciado ancho como en la imagen
            )

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLoginInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isPassword: Boolean = false,
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp) },
        leadingIcon = { Icon(icon, null, modifier = Modifier.size(20.dp)) },
        isError = isError,
        // El supportingText ahora tiene un padding superior pequeño para no pegarse al cuadro
        supportingText = {
            if (isError) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 12.dp) // Alineado con el texto interno
                )
            }
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        //Quitamos el .clip() del modifier externo y usamos 'shape' dentro del TextField
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.9f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
            errorContainerColor = Color.White.copy(alpha = 0.9f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = MobentisRed
        ),
        singleLine = true
    )
}


@Composable
fun LoginInputField(label: String, value: String, icon: ImageVector, isPassword: Boolean = false) {
    TextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        trailingIcon = { if(isPassword) Icon(Icons.Default.Visibility, null) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = InputBackground,
            unfocusedContainerColor = InputBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}