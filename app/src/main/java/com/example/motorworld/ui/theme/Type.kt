package com.example.motorworld.ui.theme

import com.example.motorworld.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font

// Set of Material typography styles to start with

val Rajdhani = FontFamily(
    Font(
        R.font.rajdhani_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.rajdhani_semibold,
        FontWeight.SemiBold
    ),
    Font(
        R.font.rajdhani_bold,
        FontWeight.Bold
    )
)
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )

)