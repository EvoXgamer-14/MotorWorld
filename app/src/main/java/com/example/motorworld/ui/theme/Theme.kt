package com.example.motorworld.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(

    primary = AzulTecnologico,
    secondary = VerdeSalida,
    tertiary = AmarilloAdvertencia,

    background = NegroCarbono,
    surface = GrisAsfalto,

    onBackground = Blanco,
    onSurface = Blanco
)

private val LightColorScheme = lightColorScheme(

    primary = AzulTecnologico,
    secondary = VerdeSalida,
    tertiary = AmarilloAdvertencia,

    background = Blanco,
    surface = GrisMetalico,

    onBackground = NegroCarbono,
    onSurface = NegroCarbono
)
@Composable
fun MotorWorldTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}