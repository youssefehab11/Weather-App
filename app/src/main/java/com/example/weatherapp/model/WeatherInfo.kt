package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.weatherapp.data.CheckValue

data class WeatherInfo(
    @DrawableRes val icon: Int,
    @StringRes val value: Int,
    val checkValue: CheckValue
)
