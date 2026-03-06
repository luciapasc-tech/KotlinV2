package com.example.meditationuiyoutube.ui.theme

import android.annotation.SuppressLint
import android.text.Highlights
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationuiyoutube.Feature
import com.example.meditationuiyoutube.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.contentValuesOf
import com.example.meditationuiyoutube.BottomMenuContent
import com.example.meditationuiyoutube.standartQuadTo
import java.nio.file.WatchEvent

/**
 * Función de ayuda para dibujar curvas de Bezier cuadráticas suavizadas.
 * Debe ir fuera de la función FeatureItem.
 * Sin esta funcion los standardQuadFromTo no funcionan
 */
fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        (from.x + to.x) / 2f,
        (from.y + to.y) / 2f
    )
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .background(DeepBlue) // DeepBlue existe en Color.kt
        .fillMaxSize()
        .safeDrawingPadding()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }

        //se establecen los iconos del menu
        BottonMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}

//botones abajo del todo pequeños
@Composable
fun BottonMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue, // al clicar el icono de casa se pone azul
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedIndex: Int = 0
){
    var selectedItemIndex by remember{
        mutableStateOf(initialSelectedIndex)
    }

    //hace que haya un espacio entre cada icono del menu de abajo
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor

            ){
                selectedItemIndex = index
            }

        }
    }
}

//box que "guarda" el icono al seleccionarlo
@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable{
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ){
            Icon(
                painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor, //cuando no esté seleccinado el color es diferente
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}


@Composable
fun GreetingSection(name: String = "David") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // Cambiado a SpaceBetween para separar texto e icono
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good morning, $name",
                style = MaterialTheme.typography.headlineMedium, // El que antes era h2
                color = TextWhite //TextWhite existe en Color.kt
            )
            Text(
                text = "We wish you a good day!",
                style = MaterialTheme.typography.bodyLarge, // El que antes era body1
                color = AquaBlue //AquaBlue existe en Color.kt
            )
        }

        // CORRECCIÓN AQUÍ: R.drawable.nombre_de_tu_icono
        // ic_search = icono guardado en res/drawable
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = TextWhite,
            modifier = Modifier.size(24.dp)
        )
    }
}

//funcion q determina los 3 botones seguidos debajo del texto de bienvenida
@Composable
fun ChipSection(chips: List<String>){
    var selectedChipIndex by remember{
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center ,
                modifier = Modifier
                .padding(15.dp, top = 15.dp, bottom = 15.dp)
                .clickable{
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if(selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp) //separacion entre texto interior del boton y el boton

            ){
                Text(chips[it], color = TextWhite)
            }
        }
    }
}

//funcion donde se muestra el boton grande debajo de la anterior funcion
//Reproduciria el sonido
@Composable
fun CurrentMeditation(
    color: Color = LightRed){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .padding(horizontal = 15.dp , vertical = 20.dp)
            .fillMaxWidth()

    ) {
        Column {
            Text(
                text = "Daily thought",
                style = MaterialTheme.typography.headlineMedium)

            Text(
                text = "Meditation * 3-10 min ",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ){
            Icon(painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp))
        }
    }
}

//son los 4 apartados cuadrados con un pequeño diseño dentro y son clicables
@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineLarge, // Ajustado a M3
            modifier = Modifier.padding(15.dp),
            color = TextWhite
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // ANTES ERA 'cells', AHORA ES 'columns'
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            // Usamos features.size o directamente items(features)
            items(features.size) { index ->
                // LLama la función que dibuja cada tarjeta (FeatureItem)
                FeatureItem(feature = features[index])
            }
        }
    }
}

//dibuja las 4 tarjetas
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FeatureItem(
    feature : Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path, las olas coloreadas dentro de los cuadrados
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.07f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)

            //dibuja la forma para que el color rellene la parte superior
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f + 100f, height.toFloat() + 100f)
            close()

//            quadraticTo(mediumColoredPoint2.x, mediumColoredPoint2.y,
//                        (mediumColoredPoint1.x + mediumColoredPoint2.x) /2f,
//                        (mediumColoredPoint1.y + mediumColoredPoint2.y) /2f
//            )
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                color = TextWhite, // Añadimos color aquí
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                // Cambiado a BottomStart para que no tape el texto de arriba
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite, // Usamos 'color' en lugar de 'style'
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Acción al hacer clic
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}