package com.example.weatherapp.model

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Space {
    @Composable
    fun SpaceHeight(value: Int){
        Spacer(modifier = Modifier.height(value.dp))
    }
    @Composable
    fun SpaceWidth(value: Int){
        Spacer(modifier = Modifier.width(value.dp))
    }
}