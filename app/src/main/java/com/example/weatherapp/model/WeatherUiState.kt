package com.example.weatherapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.weatherapp.R

data class WeatherUiState(
    @StringRes var city: Int = R.string.london,
    var temperature: Int = 13,
    @DrawableRes var icon: Int = R.drawable.rain_cloud_sun
)