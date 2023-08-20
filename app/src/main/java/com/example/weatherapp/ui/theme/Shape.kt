package com.example.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(15.dp),
    medium = RoundedCornerShape(bottomStart = 50.dp, topEnd = 50.dp,topStart = 5.dp, bottomEnd = 5.dp),
    large = RoundedCornerShape(100.dp)
)