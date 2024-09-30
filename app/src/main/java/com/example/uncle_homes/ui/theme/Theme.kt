package com.example.uncle_homes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

// Define color palette with your custom colors
private val LightColors = lightColors(
    primary = Color(0xFFDD0004),           // glossy_red
    primaryVariant = Color(0xFFF158),       // glossy_yellow
    secondary = Color(0xFF85C285),          // glossy_green
    background = Color(0xFFCCC2B2),         // background
    surface = Color(0xFFCCC2B2),            // background for surfaces if needed
    onPrimary = Color.White,                // Text/Icon color on top of primary color
    onSecondary = Color.White,              // Text/Icon color on top of secondary color
    onBackground = Color.Black,             // Text/Icon color on top of background
    onSurface = Color.Black                 // Text/Icon color on surfaces
)

private val DarkColors = darkColors(
    primary = Color(0xFFDD0004),           // glossy_red
    primaryVariant = Color(0xFFF158),       // glossy_yellow
    secondary = Color(0xFF85C285),          // glossy_green
    background = Color(0xFFCCC2B2),         // background
    surface = Color(0xFFCCC2B2),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        //shapes = Shapes,
        content = content
    )
}


// Define typography
val Typography = Typography(
    body1 = TextStyle(
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontSize = 20.sp
    )
)

// Define shapes
//val Shapes = Shapes(
   // small = MaterialTheme.shapes.small,
    //large = MaterialTheme.shapes.large
//)
