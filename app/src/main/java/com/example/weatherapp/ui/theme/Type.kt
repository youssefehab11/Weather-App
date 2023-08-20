package com.example.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val mohrRounded = FontFamily(
    Font(R.font.mohr_rounded_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.W100,
        fontSize = 36.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.White
    ),
    bodyMedium = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.W100,
        fontSize = 28.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.White
    ),
    bodySmall = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color(0xffBDBCE1)
    ),
    titleLarge = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        color = Color(0xff171717)
    ),
    titleSmall = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color(0xff171717)
    ),
    labelSmall = TextStyle(
        fontFamily = mohrRounded,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = Color(0xff171717)
    )

)