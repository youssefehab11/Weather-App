package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.weatherapp.data.Weather


data class CityCard(
    val temperature: Int,
    @StringRes val city: Int,
    @DrawableRes val icon: Int,
    val weather: Weather
)


